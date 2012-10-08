package com.rec.contact

class ContactTypeSelection {
	
	Contact contact
	ContactType contactType

    static mapping = {
		id generator: 'increment'
		version false
		table 'contact_type_selection'
		contact column: 'contact'
		contactType column: 'contact_type'
	}
	
	static constraints = {
		contact blank:false, nullable: false
		contactType blank: false, nullable: false
	}
}

