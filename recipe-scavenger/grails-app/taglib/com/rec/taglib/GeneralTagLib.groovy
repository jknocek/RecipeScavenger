package com.rec.taglib

class GeneralTagLib {
	def selectHeader = {attrs,body ->
		out<<render(template:"/header/header")
	}
	
	def selectCreateAccountForm = {attrs,body ->
		out<<render(template:"/user/createAccountForm")
	}
	
	def selecAccountSettingsForm = {attrs,body ->
		out<<render(template:"/user/accountSettingsForm")
	}
}