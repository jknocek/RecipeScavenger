package com.rec.recipe

import com.rec.ingredient.IngredientType

class RecipeContent {
	
	Recipe recipe
	IngredientType ingredient
	double quantity
	double baseQuantity
	String uom
	
	static mapping = {
		id generator: 'increment'
		version false
		table 'recipe_content'
		recipe column: 'recipe_id'
		ingredient column: 'ingredient_id'
		baseQuantity column: 'base_quantity'
	}

    static constraints = {
		recipe blank: false, nullable: false
		ingredient blank: false, nullable: false
		quantity blank: false, nullable: false
		baseQuantity blank: false, nullable: false
    }
}
