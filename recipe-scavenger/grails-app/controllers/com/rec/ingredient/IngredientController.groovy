package com.rec.ingredient

class IngredientController {
	static scope="session"

	def IngredientController() {
		
	}

	
    def listIngredients() {
		def ingredients = IngredientType.list(sort: "name", order: "asc")
		
		return [ingredients: ingredients]
	}
}
