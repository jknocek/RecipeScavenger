package com.rec.home

import com.rec.news.News

class HomeController {
	static scope = "session"
	
	def news 
	
	def home() {
		if(session.foundError?.size() > 0) {
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}
		
		news = News.findAll();
	}
}
