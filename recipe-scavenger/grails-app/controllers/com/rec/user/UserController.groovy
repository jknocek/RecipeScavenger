package com.rec.user

import com.rec.user.User
import com.rec.validation.UserValidation

class UserController {
	def static scope = "session"
	def userList = []
	def currentUser
	
	boolean adminEdit = false
	
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
	
	def toAccountSettings() {
		currentUser = session.user
		adminEdit = false
		redirect(action: 'accountSettings')
	}
	
	def editAccount() {
		long userId = Long.parseLong(params.id)
		
		if(userId) {
			currentUser = User.findWhere(id: userId)
			adminEdit = true
			redirect(action: 'accountSettings')
		} else {
			session.foundError = 'Invalid User'
			redirect(action: 'userList')
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
	
	def userList() {
		if(session.foundError?.size() > 0) {
			
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}
		
		userList = User.findAll()
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
	
	def doDeleteUser() {
		long userId = Long.parseLong(params.id)
		
		if(userId) {
			for(user in userList) {
				if(user.id == userId) {
					try {
						user.active = false
						user=user.merge()
						user.save(flush:true)
					} catch(Exception e) {
					
						session.foundError = 'Unknown Error'
					}
					break
				}
			}
		} else {
			session.foundError = "Invalid User"
		}
		
		redirect(action: 'userList')
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
			try {
				sendMail {
					to newUser.email
					subject "Welcome to Recipe Scavenger!"
					html g.render(template:"newAccountMessage")
				  }
				
				session.foundError = ""
				newUser.save(flush:true)
				redirect(controller:'home', action:'home')
			} catch(Exception e) {
				session.foundError = "Not a valid email!"
				redirect(action:'createAccount')
			}
		} else {
			session.foundError = result.errorMessage
			redirect(action:'createAccount')
		}
	}
	
	def doUpdateAccount = {
		def user
		def result
		
		def oldUsername = currentUser?.username
		def oldEmail = currentUser?.email
		
		def confPassword = params.confPassword
		def newUsername = params.username
		def newEmail = params.email
		def password = params.password
		
		result = UserValidation.validateUpdatedAccountInfo(oldUsername, oldEmail, newUsername, newEmail, password, confPassword)
		
		if(result.success) {		
			session.foundError = ""
			currentUser.username = newUsername
			currentUser.email = newEmail
			
			if(password?.size() > 0) {
				currentUser.password = password
			}
			
			try {
				sendMail {
					to currentUser.email
					subject "Recipe Scavenger Account Update"
					body 'Hello  ' + currentUser.username + ', your account detail have been updated'
					body 'Hello  ' + currentUser.username + ', your account details have been updated'
				  }
				
				currentUser = currentUser.merge()
				currentUser.save(flush:true)
				
				if(!adminEdit) {
					session.user = currentUser
					session.user = session.user.merge()
					session.user.save(flush:true)
				}
							
				
			} catch(Exception e) {}
			
			redirect(controller:'home', action:'home')
		} else {
			session.foundError = result.errorMessage
			redirect(action:'accountSettings')
		}
	}
}
