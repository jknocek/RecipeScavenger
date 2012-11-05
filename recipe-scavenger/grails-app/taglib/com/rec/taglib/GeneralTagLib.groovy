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
	
	def selectHome = {attrs,body ->
		out<<render(template:"/home/home")
	}
	
	def selectRatedRecipes = {attrs,body ->
		out<<render(template:"/recipe/ratedRecipes")
	}
	
	def selectRatedRecipesMonth = {attrs,body ->
		out<<render(template:"/recipe/ratedRecipesMonth")
	}
	
	def selectRatedRecipesWeek = {attrs,body ->
		out<<render(template:"/recipe/ratedRecipesWeek")
	}
	
	def selectNews = {attrs,body ->
		out<<render(template:"/home/news")
	}
	
	def selectContact = {attrs,body ->
		out<<render(template:"/home/contact")
	}
	
	def selectAbout = {attrs,body ->
		out<<render(template:"/home/about")
	}
	
	def addRecipe = {attrs,body ->
		out<<render(template:"/recipe/addRecipeForm")
	}
	
	def selectIngredient = {attrs,body ->
		out<<render(template:"/recipe/selectIngredient")
	}
}