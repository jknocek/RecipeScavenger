package com.rec.recipe

import com.rec.ingredient.IngredientType

class RecipeContent {
	
	Recipe recipe
	IngredientType ingredient
	double quantity
	
	static mapping = {
		id generator: 'increment'
		version false
		table 'recipe_content'
		recipe column: 'recipe_id'
		ingredient column: 'ingredient_id'
	}

    static constraints = {
		recipe blank: false, nullable: false
    }
}
