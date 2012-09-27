package com.rec.validation

import com.rec.user.User

class UserValidation {

	public static ValidationResult validateAccountInfo(def userName, def email, def password, def confPassword) { 
		def user
		def result
		
		result = new ValidationResult()
		
		user = User.findWhere(email:email)
		if(user) {
			result.success = false
			result.errorMessage = "This email already has an account with Recipe Scavenger!"
			return result
		} else if(email?.size() < 1) {
			result.success = false
			result.errorMessage = "Invalid Email!"
			return result
		}
		
		user = User.findWhere(username:userName)
		if(user) {
			result.success = false
			result.errorMessage = "This Username has already been selected, please try again."
			return result
		}else if(userName?.size() < 1) {
			result.success = false
			result.errorMessage = "Invalid Username!"
			return result
		}
		
		if(password == null || password?.size() < 1) {
			result.success = false
			result.errorMessage = "Invalid Password"
			return result
		}
		
		if(password != confPassword) {
			result.success = false
			result.errorMessage = "Confirmation Password does not match!"
			return result
		}
		
		result.success = true
		
		return result
	}
}
