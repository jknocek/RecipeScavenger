package com.rec.user

class User {
	long id
	String email
	String password
	
	String toString() { 
		"$email" 
	}
	
	static mapping = {
		id generator: 'identity', column:'id'
		version false
		table 'user_account'
		password column: '`password`'
	}


}