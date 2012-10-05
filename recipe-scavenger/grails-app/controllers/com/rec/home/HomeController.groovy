package com.rec.home

import com.rec.news.News
import com.rec.util.DateUtil
import java.text.SimpleDateFormat

class HomeController {
	static scope = "session"
	
	def news = []
	def newsPostedToday = []
	def newsPostedThisWeek = []
	def newsRemaining = []
	
	SimpleDateFormat dateFormat
	
	def HomeController() {
		dateFormat = new SimpleDateFormat("MM/dd/yyyy")
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
	}
}
