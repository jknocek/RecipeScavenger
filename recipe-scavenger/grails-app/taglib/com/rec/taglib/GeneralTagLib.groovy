package com.rec.taglib

class GeneralTagLib {
	def selectHeader = {attrs,body ->
		out<<render(template:"/header/header")
	}
}