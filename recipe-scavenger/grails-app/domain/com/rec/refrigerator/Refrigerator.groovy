package com.rec.refrigerator

import com.rec.user.User
import com.rec.ingredient.IngredientType

class Refrigerator {
	
	User user
	IngredientType ingredient
	double ingredientAmount

	static mapping = {
		id generator: 'increment'
		version false
		table 'refrigerator'
		user column: 'user_id'
		ingredient column: 'ingredient_id'
		ingredientAmount column: 'ingredient_amount'
	}
	
	static constraints = {
		user blank: false, nullable: false
		ingredient blank: false, nullable: false
		ingredientAmount blank: false, nullable:false
	}
}