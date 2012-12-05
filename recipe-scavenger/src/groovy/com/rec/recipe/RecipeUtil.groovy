package com.rec.recipe

import com.rec.uom.*

class RecipeUtil {
	
	static def findRecipesByIngredients(ArrayList<RecipeIngredient> ingredientList) {
		
		String query
		def queryList = []
		boolean firstIngredient = true
		
		if(ingredientList) {
			query = "select recipe.id, count(*) from RecipeContent as r where"
		} else {
			query = null
		}
			
		if(query) {
			for(ingredient in ingredientList) {
				double quantity
				
				if(ingredient.baseUomType == 'v') {
					quantity = UOM.getDisplayAmount('v'.charAt(0), UOM.getCurrentUom('v'.charAt(0), ingredient.uom).toString(), "LITERS", ingredient.quantity)
				} else if(ingredient.baseUomType == 'm') {
					quantity = UOM.getDisplayAmount('m'.charAt(0), UOM.getCurrentUom('m'.charAt(0), ingredient.uom).toString(), "GRAMS", ingredient.quantity)
				} else {
					quantity = ingredient.quantity
				}
				
				if(firstIngredient) {
					query += ' (r.ingredient.id=? AND r.baseQuantity <=?)'
					firstIngredient = false
				} else {
					query += ' OR (r.ingredient.id=? AND r.baseQuantity <=?)'
				}
				
				queryList.add(ingredient.ingredientId)
				queryList.add(quantity)
			}
			
			query += ' group by recipe'
			
			def recipeContentGroups = RecipeContent.executeQuery(query, queryList)
			def recipes = []
			def closeRecipes = []
			
			for(row in recipeContentGroups) {
				Recipe recipe = Recipe.findWhere(id: row.getAt(0))
				if(recipe.recipeContent?.size() == row.getAt(1)) {
					recipes.add(recipe.id)
				} else {
					closeRecipes.add(recipe.id)
				}
			}
			
			return recipes
		} else {
			return null
		}
		
	}
}
