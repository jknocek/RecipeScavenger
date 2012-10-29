package com.rec.ingredient

class IngredientType {
	// Name of the ingredient
	String name
	
	// Catagory of the Uom used for this type
	char baseUomType
	
	static mapping = {
		table 'ingredient_type'
		version false
		id generator: 'increment'
		name column: 'name'
		baseUomType column: 'base_uom'
	}
	
	static constraints = {
		name blank:false, nullable: false
		baseUomType blank: false, nullable: false
	}
}
