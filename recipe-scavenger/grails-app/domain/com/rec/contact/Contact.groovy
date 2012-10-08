package com.rec.contact

import com.rec.user.User

class Contact {
	
	String email
	String description
	User user

    static mapping = {
		id generator: 'increment'
		version false
		table 'contact'
		user column: 'user_id'
		email column: 'contact_email'
	}
	
	static constraints = {
		email blank:false, nullable: false
		description blank: false, nullable: false
		user blank: true, nullable: true
	}
}