package com.rec.recipe

import com.rec.ingredient.IngredientType

class RecipeController {
	static scope = "session"
	
	def newRecipeIngredients = []
	def newRecipeSteps = []
	def ingredients
	
	def topRecipes = [4]
	
	def RecipeController() {
		ingredients = IngredientType.findAll()
	}

    def topRecipes() {
		if(session.foundError?.size() > 0) {
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}
		topRecipes[0] = "Sandwich"
		topRecipes[1] = "Pancakes" 
		topRecipes[2] = "Bunch o' crap"
		topRecipes[3] = "More crap"
	}
	
	def recipeList() {
		def list
		
		list = Recipe.findAll()
		
		return list
	}
	
	def toAddRecipe() {
		newRecipeIngredients = []
		newRecipeSteps = []
		redirect(action: 'addRecipe')
	}
	
	def addRecipe() {
		if(session.foundError?.size() > 0) {
			
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}
	}
	
	def addRecipeContent() {
		boolean valid = true
		def selectedIngredient = params.selectedIngredient
		long ingredientId = selectedIngredient?.toLong()
		
		if(ingredientId) {
			RecipeContent recipeContent = new RecipeContent()
			recipeContent.ingredient = IngredientType.findWhere(id: ingredientId)
			recipeContent.quantity = 0
			
			for(rc in newRecipeIngredients) {
				if(rc.ingredient.id == recipeContent.ingredient.id) {
					session.foundError = recipeContent.ingredient.name + " was already added to this recipe!"
					valid = false
				}
			}
			
			if(valid)
				newRecipeIngredients.add(recipeContent)
		}
		redirect(action: 'addRecipe')
	}
	
	def deleteRecipeContent() {
		for(rc in newRecipeIngredients) {
			if(rc.ingredient.id == params.recipieContentId.toLong()) {
				newRecipeIngredients.remove(rc)
				break
			}
		}
		
		redirect(action: 'addRecipe')
	}
	
	def addStep() {
		RecipeStep recipeStep = new RecipeStep()
		newRecipeSteps.add(recipeStep)
		recipeStep.step = newRecipeSteps.size()
		redirect(action: 'addRecipe')
	}
	
	def deleteRecipeStep() {
		for(rs in newRecipeSteps) {
			if(rs.step == params.recipieStep.toLong()) {
				newRecipeSteps.remove(rs)
				break
			}
		}
		
		redirect(action: 'addRecipe')
	}
	
	def updateIngredientQuantities() {
		for(ri in newRecipeIngredients) {
			if(params.get(ri.ingredient.name))	{
				ri.quantity = params.get(ri.ingredient.name)?.toLong()
				break
			}
		}
		
		redirect(action: 'addRecipe')
	}
}
