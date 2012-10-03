package com.rec.recipe

class RecipeController {
	
	def recipes = [4]

    def topRecipes() {
		if(session.foundError?.size() > 0) {
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}
		recipes[0] = "Sandwich"
		recipes[1] = "Pancakes" 
		recipes[2] = "Bunch o' crap"
		recipes[3] = "More crap"
	}
}
