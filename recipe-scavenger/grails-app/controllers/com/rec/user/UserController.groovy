package com.rec.user

import com.rec.user.User;

class UserController {
	def static scope = "session"
	def errorMessage
	boolean loginFailed = false
	

	
	def login() {
		if(!loginFailed) {
			errorMessage = ""
		}
		
		loginFailed = false
	}
	
	def doLogin = {
		def user
		def curEmail = params.email
		def curPassword = params.password
		
		user = User.findWhere(email:curEmail, password:curPassword)
		
		session.user = user
		
		
		
		if (user) {
			errorMessage = ""
			redirect(controller:'plant',action:'list')
		} else {
			errorMessage = "Invalid email or password."
			loginFailed = true
			redirect(action:'login')
		}
	}
}
