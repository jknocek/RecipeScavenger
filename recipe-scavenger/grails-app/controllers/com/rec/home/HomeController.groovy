package com.rec.home

import com.rec.contact.Contact
import com.rec.contact.ContactType
import com.rec.contact.ContactTypeSelection
import com.rec.news.News
import com.rec.util.DateUtil
import com.rec.refrigerator.Refrigerator
import java.text.SimpleDateFormat

class HomeController {
	static scope = "session"
	
	def news = []
	def newsPostedToday = []
	def newsPostedThisWeek = []
	def newsRemaining = []
	
	def contactOptions = []
	
	SimpleDateFormat dateFormat
	
	def HomeController() {
		dateFormat = new SimpleDateFormat("MM/dd/yyyy")
		

		contactOptions = [
			[parameter: 'General Question', value: 'generalQuestion'],
			[parameter: 'General Concern', value: 'generalConcern'],
			[parameter: 'Feedback', value: 'feedback'],
			[parameter: 'Report Misuse', value: 'reportMisuse']
		]
		
	}
	
	def home() {
		if(session.foundError?.size() > 0) {
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}
		
		news = News.findAll("from News as n order by n.createdDate desc");
		newsPostedToday = []
		newsPostedThisWeek = []
		newsRemaining = []
		
		for(article in news) {
			if(DateUtil.sameDay(article.createdDate, new Date())) {
				newsPostedToday.add(article)
			} else if(DateUtil.getDaysBetween(article.createdDate, new Date()) < 7) {
				newsPostedThisWeek.add(article)
			} else {
				newsRemaining.add(article)
			}
		}
		
		if(session.user) {
			session.refrigeratorContent = Refrigerator.findAllWhere(user: session.user)
		}
	}
	
	def doContact = {
		boolean valid = false
		def generalQuestion = params.generalQuestion
		def generalConcern = params.generalConcern
		def feedback = params.feedback
		def reportMisuse = params.reportMisuse
		
		Contact contact = new Contact()
		def contactTypes = []
		
		if(generalQuestion == "on") {
			contactTypes.add(ContactType.findWhere(type: "General Question"))
			valid = true
		}
			
		if(generalConcern == "on") {
			contactTypes.add(ContactType.findWhere(type: "General Concern"))
			valid = true
		}
			
		if(feedback == "on") {
			contactTypes.add(ContactType.findWhere(type: "Feedback"))
			valid = true
		}
			
		if(reportMisuse == "on") {
			contactTypes.add(ContactType.findWhere(type: "Report Misuse"))
			valid = true
		}
		
		contact.email = params.contactEmail
		contact.description = params.contactQuestion
		contact.user = session?.user
		
		if(!contact.email) {
			valid = false
		}
		
		if(!contact.description) {
			valid = false
		}
		
		if(valid) {
			contact.save()
			
			for(type in contactTypes) {
				ContactTypeSelection sel = new ContactTypeSelection()
				sel.contact = contact
				sel.contactType = type
				sel.save()
			}
			
			sendMail {
				to contact.email
				subject "Recipe Scavenger - Contact"
				body 'Thank you for you contacting Recipe Scavenger. We will review your comment, and get back to you (if necessary) as soon as possible.'
			}
			
		} else {
			session.foundError = "Sumtin went wrong"
		}
		
		redirect(action: 'home')
	}
}
