package com.rec.user

import com.rec.user.User
import com.rec.validation.UserValidation

class UserController {
	def static scope = "session"
	
	/*
	 * Redirects the user to the create account page.
	 */
	def createAccount() {
		if(!session.user) {
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
	
	/*
	 * Redirects the user to the account settings page.
	 */
	def accountSettings() {
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
	
	/*
	 * Handles the sign-in process, then redirects the the last visited page. 
	 * Currently all non-persisted user information on that last page will be lost.
	 * 
	 * This function has no corresponding action as its view is contained in a JQuery UI dialog.
	 */
	def doLogin = {
		def user
		def name = params.emailOrUsername
		def curPassword = params.password
		def targetUrl = request.getHeader('referer')
		
		user = User.findWhere(email:name, password:curPassword)
		
		if(!user) {
			user = User.findWhere(username:name, password:curPassword)
		}
		
		session.user = user
		
		if (user) {
			session.foundError = ""
			session.error = ""
		} else {
			session.foundError = "Invalid email or password."
		}
		
		redirect(url: targetUrl)
	}
	
	/*
	 * Handles the create-user process, then redirects the user to the home page.
	 * In the case of an error, the createAccount page is refreshed, and an error is displayed.
	 */
	def doCreateAccount = {
		def newUser = new User();
		def result
		
		def confPassword = params.confPassword
		
		newUser.username = params.newUsername
		newUser.email = params.newEmail
		newUser.password = params.newPassword
		
		result = UserValidation.validateAccountInfo(newUser.username, newUser.email, newUser.password, confPassword)
		
		if(result.success) {
			if (!newUser.save(flush:true)) {
				newUser.errors.each {
					session.foundError = it
				}		
				redirect(action:'createAccount')
			} else {
				session.foundError = ""
				redirect(controller:'home', action:'home')
			}
		} else {
			session.foundError = result.errorMessage
			redirect(action:'createAccount')
		}
	}
}
