package com.rec.recipe

import com.rec.ingredient.IngredientType
import com.rec.uom.UOM
import com.rec.validation.*
//import com.rec.validation.ValidationResult
//import com.rec.validation.RecipeValidation
import com.rec.refrigerator.Refrigerator
import org.hibernate.Session
import org.hibernate.SessionFactory

class RecipeController {
	static scope = "session"
	
	boolean newRecipe
	def currentRecipe
	
	def newRecipeIngredients = []
	def newRecipeIngredientNames = []
	def newRecipeTags = []
	
	def oldRecipeIngredients = []
	def oldRecipeSteps = []
	def oldRecipeTags = []
	
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
		
		def recipes = params.recipes
		def recipeIds = []
		
		if(recipes) {
			boolean first = true
			String query = "from Recipe as r where"
			
			for(recipeId in recipes) {
				if(first) {
					query += " r.id=?"
					first = false
				} else {
					query += " OR r.id=?"
				}
				
				recipeIds.add(Long.parseLong(recipeId))
			}
			
			recipeList = Recipe.findAll(query, recipeIds)	
		} else {
			recipeList = Recipe.findAll()
		}
		
		return recipeList
	}
	
	def viewRecipe() {
		Recipe recipe
		def recipeContents
		def recipeSteps
		def recipeTags
		
		recipe = Recipe.findWhere(id: params.id.toLong())
		
		if(recipe) {
			recipeContents = RecipeContent.findAllWhere(recipe: recipe)
			recipeSteps = RecipeStep.findAllWhere(recipe: recipe)
			recipeTags = RecipeTag.findAllWhere(recipe: recipe)
		}
		
		return 	[recipe : recipe,
				 recipeContents : recipeContents,
				 recipeSteps : recipeSteps,
				 recipeTags: recipeTags
				]
	}
	
	def editRecipe() {
		def recipe
		
		recipe = Recipe.findWhere(id: params.id.toLong())
		
		currentRecipe = recipe
		
		if(recipe) {
			oldRecipeSteps = []
			oldRecipeIngredients = []
			newRecipeIngredients = RecipeContent.findAllWhere(recipe: recipe)
			newRecipeSteps = RecipeStep.findAllWhere(recipe: recipe)
			newRecipeTags = RecipeTag.findAllWhere(recipe: recipe)
			
			newRecipeTitle = recipe.name
			newRecipeDescription = recipe.description
			newRecipeIngredientNames = []
			for(rc in newRecipeIngredients) {
				newRecipeIngredientNames.add(rc.ingredient.name)
			}
			
			newRecipe = false
			
			redirect(action: 'addRecipe')
		} else {
			redirect(action: 'recipeList')
		}
	}
	
	def findByRefrigerator() {
		def recipeIngredients = []
		
		session.refrigeratorContent = Refrigerator.findAll("from Refrigerator as r where r.user=? order by ingredient.name", [session.user])
		
		for(content in session.refrigeratorContent) {
			RecipeIngredient ingredient = new RecipeIngredient()
			
			ingredient.ingredientId = content.ingredient.id
			ingredient.quantity = content.ingredientAmount
			ingredient.baseUomType = content.ingredient.baseUomType
			ingredient.uom = content.uomName
			
			recipeIngredients.add(ingredient)
		}
		
		def recipes = RecipeUtil.findRecipesByIngredients(recipeIngredients)
		
		if(!recipes) {
			recipes.add(0)
		}
		
		redirect(action:'recipeList', params: [recipes: recipes])
	}
	
	def doUpdateRecipe() {
		ValidationResult result
		
		saveNewRecipeValuesNoRedirect()
		
		currentRecipe.name = newRecipeTitle
		currentRecipe.description = newRecipeDescription
		
		result = RecipeValidation.validateRecipe(newRecipeTitle, newRecipeDescription, newRecipeIngredients, newRecipeSteps)
		
		if(result.success && session.user) {
			currentRecipe.save(flush: true)
			
			for(rc in newRecipeIngredients) {
				rc.recipe = currentRecipe
				rc.save(flush: true)
			}
			
			for(rs in newRecipeSteps) {
				rs.recipe = currentRecipe
				rs.save(flush: true)
			}
			
			for(tag in newRecipeTags) {
				tag.recipe = currentRecipe
				tag.save(flush: true)
			}
			
			for(tag in oldRecipeTags) {
				tag.delete(flush: true)
			}
			
			for(rc in oldRecipeIngredients) {
				rc.delete(flush: true)
			}
			
			oldRecipeIngredients = []
			
			for(rs in oldRecipeSteps) {
				rs.delete(flush: true)
			}
			
			oldRecipeSteps = []
			
			redirect(action: 'recipeList')
		} else {
			if(result.errorMessage)
				session.foundError = result.errorMessage
			else
				session.foundError = "Must be logged in to create a recipe"
				
			redirect(action: 'addRecipe')
		}
	}
	
	def toAddRecipe() {
		newRecipeIngredients = []
		newRecipeIngredientNames = []
		newRecipeSteps = []
		newRecipeTags = []
		newRecipeTitle = ""
		newRecipeDescription = ""
		newRecipe = true
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
		ValidationResult result
		
		saveNewRecipeValuesNoRedirect()
		
		result = RecipeValidation.validateRecipe(newRecipeTitle, newRecipeDescription, newRecipeIngredients, newRecipeSteps)
		
		if(result.success && session.user) {
			Recipe recipe = new Recipe()
			recipe.name = newRecipeTitle
			recipe.description = newRecipeDescription
			recipe.creator = session.user
			
			recipe.save(flush: true)
			
			for(rc in newRecipeIngredients) {
				rc.recipe = recipe
				rc.save(flush: true)
			}
			
			for(rs in newRecipeSteps) {
				rs.recipe = recipe
				rs.save(flush: true)
			}
			
			for(tag in newRecipeTags) {
				tag.recipe = recipe
				tag.save(flush: true)
			}
			
			redirect(action: 'recipeList')
		} else {
			if(result.errorMessage)
				session.foundError = result.errorMessage
			else
				session.foundError = "Must be logged in to create a recipe"
				
			redirect(action: 'addRecipe')
		}
	}
	
	def selectIngredient() {
		if(session.foundError?.size() > 0) {
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}

		saveNewRecipeValuesNoRedirect()
		
		//ingredients = IngredientType.findAll()
		
		def tmpIngredients = []
		
		ingredients = IngredientType.findAll()
		
		ingredients.each {
			tmpIngredients.add([
				name: it.name,
				baseUomType: it.baseUomType,
				baseUomName: IngredientTypeValidator.getUserFriendlyUomType(it.baseUomType),
				id: it.id,
			])
		}
		
		ingredients = tmpIngredients
	}
	
	def addRecipeContent() {
		for(rc in oldRecipeIngredients) {
			if(rc.ingredient.name  ==  selectedIngredient?.toLong()) {
				oldRecipeIngredients.remove(rc)
			}
		}
		
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
	
	def addTag() {
		String newTag = params.newTag
		
		saveNewRecipeValuesNoRedirect()
		
		RecipeTag tag = new RecipeTag()
		tag.name = newTag
		
		newRecipeTags.add(tag)
		
		redirect(action: 'addRecipe')
	}
	
	def removeTag() {
		String name = params?.removeTagName
		
		saveNewRecipeValuesNoRedirect()
		
		for(tag in newRecipeTags) {
			if(tag?.name == name) {
				newRecipeTags.remove(tag)
				
				if(tag.id)
					oldRecipeTags.add(tag)
				break
			}
		}
		
		redirect(action: 'addRecipe')
	}
	
	def deleteRecipeContent() {
		saveNewRecipeValuesNoRedirect()
		
		for(rc in newRecipeIngredients) {
			if(rc.ingredient.id == params.recipieContentId.toLong()) {
				newRecipeIngredients.remove(rc)
				newRecipeIngredientNames.remove(rc.ingredient.name)
				if(rc.id)
					oldRecipeIngredients.add(rc)
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
		redirect(action: 'addRecipe', fragment: 'step'+recipeStep.step)
	}
	
	def deleteRecipeStep() {
		saveNewRecipeValuesNoRedirect()
		
		for(rs in newRecipeSteps) {
			if(rs.step == params.recipieStep.toLong()) {
				newRecipeSteps.remove(rs)
				if(rs.id)
					oldRecipeSteps.add(rs)
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
		def steps = RecipeStep.findAllWhere(recipe: recipe)
		def tags = RecipeTag.findAllWhere(recipe: recipe)
		
		for(s in steps) {
			s.delete(flush:true)
		}
		
		for(tag in tags) {
			tag.delete(flush: true)
		}
		
		def contents = RecipeContent.findAllWhere(recipe: recipe)
		
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
		if(params.title)
			newRecipeTitle = params.title
		if(params.description)
			newRecipeDescription = params.description
		updateIngredientUoms(params.ingredientUom)
		updateIngredientQuantities()
		updateStepQuanity()
		updateStepInstruction()
	}
	
	def saveNewRecipeValues() {
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
		
		if(uoms) {
			int index = 0
			for(rc in newRecipeIngredients) {
				if(rc.ingredient.baseUomType != 'u') {
					if(uomsClass == "class java.lang.String")
						rc.uom = uoms
					else
						rc.uom = uoms.getAt(index)
						
					for(rs in newRecipeSteps) {
						if(rs.ingredient?.name == rc.ingredient?.name)
							rs.uom = rc.uom
					}
						
					index += 1
				}
			}
		}
	}
	
	private def updateIngredientQuantities() {
		def temp = params
		for(ri in newRecipeIngredients) {
			if(params.get(ri.ingredient.name))	{
				ri.quantity = params.get(ri.ingredient.name)?.toDouble()
				def baseUomType = ri.ingredient.baseUomType
				def currentUom = UOM.getCurrentUom(baseUomType, ri.uom).toString()
				if(baseUomType == 'v')
					ri.baseQuantity = UOM.getDisplayAmount(baseUomType, currentUom, "LITERS", ri.quantity)
				else if(baseUomType == 'm')
					ri.baseQuantity = UOM.getDisplayAmount(baseUomType, currentUom, "GRAMS", ri.quantity)
				else
					ri.baseQuantity = ri.quantity
				
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
