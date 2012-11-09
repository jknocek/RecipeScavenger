package com.rec.recipe

import com.rec.ingredient.IngredientType

class RecipeController {
	static scope = "session"
	
	def newRecipeIngredients = []
	def newRecipeSteps = []
	def newRecipeTitle = ""
	def newRecipeDescription = ""
	def ingredients
	def recipeList = []
	
	def recipeList() {
		if(session.foundError?.size() > 0) {
			
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}
		
		recipeList = Recipe.findAll()
		
		return recipeList
	}
	
	def toAddRecipe() {
		newRecipeIngredients = []
		newRecipeSteps = []
		newRecipeTitle = ""
		newRecipeDescription = ""
		redirect(action: 'addRecipe')
	}
	
	def addRecipe() {
		if(session.foundError?.size() > 0) {
			
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}
		
		ingredients = IngredientType.findAll()
	}
	
	def doAddRecipe() {
		def temp = params
		
		Recipe recipe = new Recipe()
		recipe.name = newRecipeTitle
		recipe.description = newRecipeDescription
		recipe.creator = session.user
		
		recipe.save(flush:true)
		
		for(rc in newRecipeIngredients) {
			rc.recipe = recipe
			rc.save(flush:true)
		}
		
		for(rs in newRecipeIngredients) {
			rs.recipe = recipe
			rs.save(flush:true)
		}
		
		
		redirect(action: 'recipeList')
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
			
			if(valid) {
				recipeContent.uom = "unit"
				newRecipeIngredients.add(recipeContent)
			}
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
		recipeStep.instruction = "bla"
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
				ri.quantity = params.get(ri.ingredient.name)?.toDouble()
				break
			}
		}
		
		redirect(action: 'addRecipe')
	}
	
	def updateTitle = {
		newRecipeTitle = params.title
		redirect(action: 'addRecipe')
	}
	
	def updateDescription() {
		newRecipeDescription = params.description
		redirect(action: 'addRecipe')
	}
	
	def deleteRecipe() {
		def id = params.id
		def recipeId = id.toLong()
		Recipe recipe = Recipe.findWhere(id: recipeId)
		
		def steps = RecipeStep.findWhere(recipe: recipe)
		
		for(s in steps) {
			s.delete(flush:true)
		}
		
		def contents = RecipeContent.findWhere(recipe: recipe)
		
		for(c in contents) {
			c.delete(flush:true)
		}
		
		recipe.delete(flush:true)
		
		redirect(action: 'recipeList')
	}
}
