package com.rec.ingredient

class IngredientType {

	String name;
	String baseUomName;
	
	static mapping = {
		table 'ingredient_type'
		version false
		id generator: 'increment'
		name column: 'name'
		baseUomName column: 'base_uom'
	}
	
    static constraints = {
		name blank:false, nullable: false
		baseUomName blank: false, nullable: false
    }
}
