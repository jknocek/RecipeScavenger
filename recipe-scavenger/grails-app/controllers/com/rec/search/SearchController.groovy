package com.rec.search

import grails.converters.*;
import com.rec.recipe.*;
import com.rec.ingredient.*;

class SearchController {
	static scope = "session"
	
    def quick() {
		return []
	}
	
	
	def ingredientSearchJson() {
		def searchText = "%" + params.name + "%"
		def displayResults = []
		
		def c = IngredientType.createCriteria()
		
		def ingredients = c.list {
			   ilike('name', searchText)
			   maxResults(10)
			   order("name", "asc")
		}
		ingredients.each {
			displayResults.add([
				id: it.id,
				name: it.name,
				uom: it.baseUomType.toString()
			])
		}
		
		render displayResults as JSON
	}
	
	
	def byCategories() {
		if(params.tags == null) {
			response.status = 404
			return
		}
		
		return []
	}
	
}
