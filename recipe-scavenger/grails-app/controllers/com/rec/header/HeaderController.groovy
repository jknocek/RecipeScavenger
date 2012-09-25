package com.rec.header

class HeaderController {
	static scope = "session"
	
	def logout() {
		session.user = null
		redirect(controller: "Home", action:"home")
	}
}
