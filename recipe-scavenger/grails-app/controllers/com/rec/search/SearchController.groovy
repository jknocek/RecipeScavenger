package com.rec.search

import org.apache.jasper.compiler.Node.ParamsAction;

import grails.converters.*;
import com.rec.recipe.*;
import com.rec.ingredient.*;
import com.rec.uom.UOM;
import java.util.regex.Pattern;

class SearchController {
	static scope = "session"
	
	def currentIngredients = []
	
	def quick() {
		return [
			volumeUoms: UOM.getPossibleUoms('v' as char),
			massUoms: UOM.getPossibleUoms('m' as char),
		]
	}
	
	def getExistingIngredientsJson() {
		render currentIngredients as JSON
	}
	
	def ingredientSearchJson() {
		def searchText = "%" + params.name + "%"
		def displayResults = []
		
		def c = IngredientType.createCriteria()
		
		def ingredients = c.list {
			ilike('name', searchText)
			maxResults(6)
			order("name", "asc")
		}
		ingredients.each {
			displayResults.add([
				id: it.id,
				name: it.name,
				baseUomType: it.baseUomType.toString()
			])
		}
		
		render displayResults as JSON
	}
	
	
	def addSearchIngredientJson() {
		def results = [success: false]
		if(params.ingredientId != null) {
			def type = IngredientType.findWhere(id: params.ingredientId.toLong())
			
			if(type != null) {
				def found = false
				for(def i in currentIngredients) {
					if(i.id == type.id)
						found = true
				}

				if(!found) {
					def ing = [
						id: type.id,
						baseUomType: type.baseUomType.toString(),
						name: type.name,
						quantity: 0.0
					]
					
					currentIngredients.add(ing)
				}
				
				results.success = true
				render results as JSON
				return
			}
		}
		
		render results as JSON
	}
	
	def removeSearchIngredientJson() {
		def results = [success: false]
		if(params.ingredientId != null) {
			def id = params.ingredientId.toLong()
			
			currentIngredients.removeAll { it ->
				it.id == id
			}
			
			results.success = true
			render results as JSON
			return
		}
		
		render results as JSON
	}
	
	
	def setSearchIngredientQuantityJson() {
		def results = [success: false]
		if(params.ingredientId != null && params.quantity != null) {
			def id = params.ingredientId.toLong()
			def quantity = params.quantity.toDouble()
			
			currentIngredients.each { it ->
				if(it.id == id)
					it.quantity = params.quantity.toDouble()
			}
			
			results.success = true
			render results as JSON
			return
		}
		
		render results as JSON
	}
	
	
	def byIngredients() {
		def searchIngredients = []
		currentIngredients.each { item ->
			def ingredient = new RecipeIngredient()
			def id = item.id
			
			ingredient.ingredientId = item.id
			ingredient.baseUomType = item.baseUomType[0]
			
			if(params["[$id].quantity"] != null)
				ingredient.quantity = params["[$id].quantity"].toDouble()
			else
				ingredient.quantity = item.quantity
				
			if(params["[$id].uom"] != null)
				ingredient.uom = params["[$id].uom"]
			else 
				ingredient.uom = ""
				
			searchIngredients.add(ingredient)
		}
		
		def recipes = RecipeUtil.findRecipesByIngredients(searchIngredients)
		
		if(!recipes) {
			recipes = [0]
		}

		// Remove all old ingredients
		currentIngredients = []

		redirect(controller: 'recipe', action:'recipeList', params: [recipes: recipes])
	}
	
	def byCategory() {
		def tags = params.tags ?: ""
		
		def pattern = Pattern.compile(/(,|, )/)
		def tagArray = pattern.split(tags)
		
		def results = RecipeUtil.findRecipesByTags(tagArray)
		
		redirect(controller: 'recipe', action:'recipeList', params: [recipes: results])
	}
	
}
