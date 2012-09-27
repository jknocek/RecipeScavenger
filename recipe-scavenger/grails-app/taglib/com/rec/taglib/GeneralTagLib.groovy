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
}