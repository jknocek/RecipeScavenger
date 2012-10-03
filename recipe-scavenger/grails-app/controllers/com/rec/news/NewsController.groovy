package com.rec.news

import com.rec.user.User;

class NewsController {

    def addNews() {
		if(session.user) {
			if(session.foundError?.size() > 0) {
				
				session.error = session.foundError
				session.foundError = ""
			} else {
				session.error = ""
			}
		} else {
			redirect(controller: 'home', action: 'home')
		}
	}
	
	def doAddNews = {
		def title = params.newArticleTitle
		def text = params.newArticleText
		
		def news = new News()
		
		news.title = title
		news.text = text
		news.author = session.user
		
		if(title && text) {
			if (!news.save(flush:true)) {
				news.errors.each {
					session.foundError = it
				}
				redirect(action:'addNews')
			} else {
				session.foundError = ""
				redirect(controller:'home', action:'home')
			}
		} else {
			session.foundError = "Missing information!"
			redirect(action:'addNews')
		}
	} 
}
