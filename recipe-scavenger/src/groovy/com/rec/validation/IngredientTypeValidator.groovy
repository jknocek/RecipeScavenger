package com.rec.validation

class IngredientTypeValidator {
	public static ValidationResults Validate(def name, def uomType) {
		ValidationResults results = new ValidationResults()
		
		if(name == null || name == "")
			results.errorDictionary.name = "*Please enter an ingredient name"
			
		if(uomType != 'v' && uomType != 'm' && uomType != 'u')
			throw new IllegalArgumentException("UOM type is invalid")
			
		results.success = results.errorDictionary.size() == 0
		return results
	}
	
	public static String getUserFriendlyUomType(def uomType) {
		switch (uomType) {
			case 'v':
				return "Volumetric (e.g. gallons, liters)"
			case 'm':
				return "Mass (e.g. pounds, grams)"
			case 'u':
				return "Individual Units (e.g. an apple)"
			default:
				return "*error*"
		}
	}
}
