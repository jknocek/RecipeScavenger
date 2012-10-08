package com.rec.contact

class ContactType {
	
	String type
	
	static mapping = {
		id generator: 'increment'
		version false
		table 'contact_type'
	}
 
    static constraints = {
		type blank:false, nullable: false
    }
}