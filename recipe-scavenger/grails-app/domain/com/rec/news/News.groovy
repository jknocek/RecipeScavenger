package com.rec.news

import com.rec.user.User

class News {
	String title
	String text
	User author
	Date createdDate = new Date()
	
	static mapping = {
		id generator: 'increment'
		version false
		table 'news'
		author column: 'author'
		createdDate column: 'created_date'
	}
	
    static constraints = {
		title blank:false, nullable: false
		text blank: false, nullable: false
		author blank: true, nullable: true
		createdDate blank: false, nullable: false
    }
}