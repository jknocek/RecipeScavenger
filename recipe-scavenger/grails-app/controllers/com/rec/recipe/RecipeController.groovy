package com.rec.recipe

import com.rec.ingredient.IngredientType
import com.rec.uom.UOM

class RecipeController {
	static scope = "session"
	
	def newRecipeIngredients = []
	def newRecipeIngredientNames = []
	
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
		newRecipeIngredientNames = []
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
	
	def selectIngredient() {
		if(session.foundError?.size() > 0) {
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}

		saveNewRecipeValuesNoRedirect()
		
		ingredients = IngredientType.findAll()
	}
	
	def addRecipeContent() {
		boolean valid = true
		def selectedIngredient = params.selectedIngredient
		IngredientType newIngredient = IngredientType.findWhere(id: selectedIngredient?.toLong())
		
		if(newIngredient) {
			for(rc in newRecipeIngredients) {
				if(rc.ingredient.id == newIngredient.id) {
					session.foundError = newIngredient.name + " was already added to this recipe!"
					valid = false
				}
			}
			
			RecipeContent recipeContent = new RecipeContent()
			recipeContent.ingredient = newIngredient
			recipeContent.quantity = 0
			recipeContent.uom = UOM.getBaseUom(newIngredient.baseUomType)
			
			if(valid) {
				newRecipeIngredients.add(recipeContent)
				newRecipeIngredientNames.add(recipeContent.ingredient.name)
				
			}
		}
		redirect(action: 'addRecipe')
	}
	
	def deleteRecipeContent() {
		saveNewRecipeValuesNoRedirect()
		
		for(rc in newRecipeIngredients) {
			if(rc.ingredient.id == params.recipieContentId.toLong()) {
				newRecipeIngredients.remove(rc)
				break
			}
		}
		
		redirect(action: 'addRecipe')
	}
	
	def addStep() {
		saveNewRecipeValuesNoRedirect()
		
		RecipeStep recipeStep = new RecipeStep()
		recipeStep.instruction = ""
		newRecipeSteps.add(recipeStep)
		recipeStep.step = newRecipeSteps.size()
		redirect(action: 'addRecipe')
	}
	
	def deleteRecipeStep() {
		saveNewRecipeValuesNoRedirect()
		
		for(rs in newRecipeSteps) {
			if(rs.step == params.recipieStep.toLong()) {
				newRecipeSteps.remove(rs)
				break
			}
		}
		
		int index = 1
		for(rs in newRecipeSteps) {
			rs.step = index
			index ++
		}
		
		redirect(action: 'addRecipe')
	}
	
	def deleteRecipe() {
		def id = params.id
		def recipeId = id.toLong()
		Recipe recipe = Recipe.findWhere(id: recipeId)
		def steps = RecipeStep.findWhere(recipe: recipe)
		
		saveNewRecipeValuesNoRedirect()
		
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
	
	def getIngredientUom() {
		for(rc in newRecipeIngredients) {
			if(rc.ingredient.name == name) {
				return rc.uom
			}
		}
	}
	
	def saveNewRecipeValuesNoRedirect() {
		newRecipeTitle = params.title
		newRecipeDescription = params.description
		updateIngredientUoms(params.ingredientUom)
		updateIngredientQuantities()
		updateStepQuanity()
		updateStepInstruction()
	}
	
	def saveNewRecipeValues() {
		def temp = params
		
		newRecipeTitle = params.title
		newRecipeDescription = params.description
		updateIngredientUoms(params.ingredientUom)
		updateIngredientQuantities()
		updateStepIngredients()
		updateStepQuanity()
		updateStepInstruction()
		redirect(action: 'addRecipe')
	}
	
	private def updateIngredientUoms(def uoms) {
		String uomsClass = uoms.getClass()
		
		int index = 0
		for(rc in newRecipeIngredients) {
			if(rc.ingredient.baseUomType != 'u') {
				if(uomsClass == "class java.lang.String")
					rc.uom = UOM.getUomName(rc.ingredient.baseUomType, uoms)
				else
					rc.uom = UOM.getUomName(rc.ingredient.baseUomType, uoms.getAt(index))
				index += 1
			}
		}
	}
	
	private def updateIngredientQuantities() {
		def temp = params
		for(ri in newRecipeIngredients) {
			if(params.get(ri.ingredient.name))	{
				ri.quantity = params.get(ri.ingredient.name)?.toDouble()
			}
		}
	}
	
	private def updateStepIngredients() {
		for(recipeStep in newRecipeSteps) {
			String ingredientName = params.get("step" + recipeStep.step + "PossibleIngredients")
			if(ingredientName) {
				recipeStep.ingredient = IngredientType.findWhere(name: ingredientName)
				
				for(rc in newRecipeIngredients) {
					if(rc.ingredient.name == ingredientName) {
						recipeStep.uom = rc.uom
					}
				}
			} else {
				recipeStep.ingredient = null
				recipeStep.uom = ''
			}
		}
	}
	
	private def updateStepQuanity() {
		for(recipeStep in newRecipeSteps) {
			String quantity = params.get("step" + recipeStep.step + "Quantity")
			if(quantity) {
				try {
					recipeStep.quantity = Double.parseDouble(quantity)
				} catch(Exception e) {
					session.foundInstruction = "Quantities must be numeric!"
				}
			}
		}
	}
	
	private def updateStepInstruction() {
		for(recipeStep in newRecipeSteps) { 
			String instruction = params.get("step" + recipeStep.step + "Instruction")
			
			if(instruction) {
				recipeStep.instruction = instruction
			}
		}
		
	}
}
