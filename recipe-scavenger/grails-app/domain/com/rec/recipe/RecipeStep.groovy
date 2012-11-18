package com.rec.recipe

import com.rec.ingredient.IngredientType

class RecipeStep {
	
	Recipe recipe
	int step
	String instruction
	IngredientType ingredient
	double quantity
	String uom
	
	static mapping = {
		id generator: 'increment'
		version false
		table 'recipe_step'
		recipe column: 'recipe_id'
	}

    static constraints = {
		step blank: false, nullable: false
		instruction blank: false, nullable: false
		recipe blank: false, nullable: false
		ingredient nullable: true
		quantity nullable: true
		uom nullable: true
    }
}