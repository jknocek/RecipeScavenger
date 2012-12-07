package com.rec.recipe

class RecipeTag {
	
	Recipe recipe
	String name
	
	static mapping = {
		id generator: 'increment'
		version false
		table 'recipe_tag'
		recipe column: 'recipe_id'
	}

    static constraints = {
		recipe blank: false, nullable: false
		name blank: false, nullable: false
    }
}
