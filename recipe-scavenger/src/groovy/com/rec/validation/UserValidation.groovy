package com.rec.validation

import com.rec.user.User

class UserValidation {

	public static ValidationResult validateAccountInfo(def userName, def email, def password, def confPassword) { 
		def user
		def result
		def errors = []
		
		result = new ValidationResult()
		result.success = true
		result.errorMessage = ""
		
		user = User.findWhere(email:email)
		if(user) {
			result.success = false
			result.errorMessage = "This email already has an account with Recipe Scavenger!"
			return result
		} else if(email?.size() < 1) {
			result.success = false
			errors.add("Invalid Email, an email is required.")
		}
		
		user = User.findWhere(username:userName)
		if(user) {
			result.success = false
			result.errorMessage = "This Username has already been selected, please try again."
			return result
		}else if(userName?.size() < 1) {
			result.success = false
			errors.add("Invalid Username, a Username is required.")
		}
		
		if(password == null || password?.size() < 1) {
			result.success = false
			errors.add("Invalid Password")
		}
		
		if(password != confPassword) {
			result.success = false
			result.errorMessage = "Confirmation Password does not match!"
			return result
		}
		
		if(errors.size() > 0) {
			if(errors.size() == 1) {
				result.errorMessage = errors.get(0)
				return result
			}
			
			for(error in errors) {
				if(error == errors.get(errors.size() - 1)) {
					result.errorMessage += " " + error + "."
				} else {
					result.errorMessage += " " + error + ","
				}
			}
		}
		
		return result
	}
	
	public static ValidationResult validateUpdatedAccountInfo(def oldUsername, def oldEmail, def newUsername, def newEmail, def password, def confPassword) {
		def user
		def result
		def errors = []
		
		result = new ValidationResult()
		result.success = true
		result.errorMessage = ""
		
		
		
		if(!newEmail.equalsIgnoreCase(oldEmail)) {
			user = User.findWhere(username:newEmail)
			if(user) {
				result.success = false
				result.errorMessage = "This Email has already been selected, please try a new one."
				return result
			}else if(newEmail?.size() < 1) {
				result.success = false
				errors.add("Invalid Email, a Email is required.")
			}
		}
		
		if(newUsername != oldUsername) {
			user = User.findWhere(username:newUsername)
			if(user) {
				result.success = false
				result.errorMessage = "This Username has already been selected, please try a new one."
				return result
			}else if(newUsername?.size() < 1) {
				result.success = false
				errors.add("Invalid Username, a Username is required.")
			}
		}
		
		if(password?.size() > 0) {
			if(confPassword != password) {
				result.success = false
				errors.add("Your password and confirmations do not match.")
			}
		}
		
		if(errors.size() > 0) {
			if(errors.size() == 1) {
				result.errorMessage = errors.get(0)
				return result
			}
			
			for(error in errors) {
				if(error == errors.get(errors.size() - 1)) {
					result.errorMessage += " " + error + "."
				} else {
					result.errorMessage += " " + error + ","
				}
			}
		}
		
		return result
	}
}
