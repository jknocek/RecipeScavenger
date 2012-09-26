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
	
	def createAccount() {	
		errorMessage = ""
	}
	
	def accountSettings() {
		errorMessage = ""
	}
	
	def doLogin = {
		def user
		def name = params.emailOrUsername
		def curPassword = params.password
		
		user = User.findWhere(email:name, password:curPassword)
		
		if(!user) {
			user = User.findWhere(username:name, password:curPassword)
		}
		
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
	
	def doCreateAccount = {
		def newUser = new User();
		
		newUser.username = params.username
		newUser.email = params.email
		newUser.password = params.password

		if (!newUser.save(flush:true)) {
			newUser.errors.each {
				errorMessage = it
			}
			redirect(action:'createAccount')
		} else {
			redirect(action:'login')
		}
		
	}
}
