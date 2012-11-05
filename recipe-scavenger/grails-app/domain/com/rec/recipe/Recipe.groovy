package com.rec.recipe

import com.rec.user.User

class Recipe {
	static hasMany = [recipeContent: RecipeContent, recipeStep: RecipeStep]
	static mappedBy = [recipeContent: "recipe", recipeStep: "recipe"]
	
	User creator
	String name
	String description
	
	static mapping = {
		id generator: 'increment'
		version false
		table 'recipe'
		creator column: 'creator'
	}
	
    static constraints = {
		name blank: false, nullable: false
		description blank: false, nullable: false
    }
}