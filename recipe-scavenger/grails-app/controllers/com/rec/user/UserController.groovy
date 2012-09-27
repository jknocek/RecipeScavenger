package com.rec.user

import com.rec.user.User
import com.rec.validation.UserValidation

class UserController {
	def static scope = "session"
	def errorMessage
	boolean loginFailed = false
	boolean createFailed = false
	
	def login() {
		if(!loginFailed) {
			errorMessage = ""
		}
		
		loginFailed = false
	}
	
	def createAccount() {	
		if(!createFailed) {
			errorMessage = ""
		}
		
		createFailed = false
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
			redirect(controller:'home',action:'home')
		} else {
			errorMessage = "Invalid email or password."
			loginFailed = true
			redirect(action:'login')
		}
	}
	
	def doCreateAccount = {
		def newUser = new User();
		def result
		
		def confPassword = params.confPassword
		
		newUser.username = params.username
		newUser.email = params.email
		newUser.password = params.password
		
		result = UserValidation.validateAccountInfo(newUser.username, newUser.email, newUser.password, confPassword)
		
		if(result.success) {
			if (!newUser.save(flush:true)) {
				newUser.errors.each {
					errorMessage = it
				}
				createFailed = true				
				redirect(action:'createAccount')
			} else {
				redirect(action:'login')
			}
		} else {
			createFailed = true
			errorMessage = result.errorMessage
			redirect(action:'createAccount')
		}
		
	}
}
