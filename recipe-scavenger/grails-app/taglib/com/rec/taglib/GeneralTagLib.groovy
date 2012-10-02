package com.rec.taglib

class GeneralTagLib {
	def selectHeader = {attrs,body ->
		out<<render(template:"/header/header")
	}
	
	def selectCreateAccountForm = {attrs,body ->
		out<<render(template:"/user/createAccountForm")
	}
	
	def selectAccountSettingsForm = {attrs,body ->
		out<<render(template:"/user/accountSettingsForm")
	}
	
	def selectLeftSideBar = {attrs,body ->
		out<<render(template:"/home/leftSideBar")
	}
	
	def selectLogin = {attrs,body ->
		out<<render(template:"/user/login")
	}
	
	def selectTerms = {attrs,body ->
		out<<render(template:"/user/termsAndConditions")
	}
	
	def errorDisplay = {attrs,body ->
		out<<render(template:"/error/userError")
	}
}