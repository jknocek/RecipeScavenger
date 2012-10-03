package com.rec.news

import com.rec.user.User

class News {
	String title
	String text
	User author
	
	static mapping = {
		id generator: 'increment'
		version false
		table 'news'
	}
	
    static constraints = {
		title blank:false, nullable: false
		text blank: false, nullable: false
		author blank: true, nullable: true
    }
}