package com.rec.home

class HomeController {
	
	def home() {
		if(session.foundError?.size() > 0) {
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}
		
		
	}
}
