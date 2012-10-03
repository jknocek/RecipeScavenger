package com.rec.home

import com.rec.news.News
import java.text.SimpleDateFormat;

class HomeController {
	static scope = "session"
	
	def news 
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
	}
}
