package com.rec.user

class User {
	String username
	String email
	String password
	boolean admin = false
	
	String toString() { 
		"$email" 
	}
	
	static mapping = {
		id generator: 'increment'
		version false
		table 'user_account'
		password column: '`password`'
	}

	static constraints = {
		username blank: false, nullable: false, unique: true
		email blank: false, nullable: false, unique: true
		password blank: false, nullable: false
	}

}