package com.rec.validation

import com.rec.recipe.*

class RecipeValidation {

	public static ValidationResult validateRecipe(String recipeTitle, String recipeDescription, def recipeContents, def recipeSteps) {
		def result
		def errors = []
		def recipeId
		
		result = new ValidationResult()
		result.success = true
		result.errorMessage = ""
		
		recipeTitle = recipeTitle.trim()
		if(recipeTitle?.size() < 1) {
			result.success = false
			errors.add("Invalid title, every recipe must have a title")
		}
		
		recipeDescription = recipeDescription.trim()
		if(recipeDescription?.size() < 1) {
			result.success = false
			errors.add("Invalid description, please include a description")
		}
		
		
		for(rc in recipeContents) {
			if(rc.quantity <= 0) {
				result.success = false
				errors.add(rc.ingredient.name + " must have a total quanity")
			} else {
				double usedSum = 0
				for(rs in recipeSteps) {
					if(rs.ingredient?.name == rc.ingredient.name) {
						usedSum += rs.quantity
					}
				}
				
				if(usedSum != rc.quantity) {
					result.success = false
					errors.add("You specified " + rc.quantity + " " + rc.uom + " of " + rc.ingredient.name + " but only used " + usedSum + " "+ rc.uom + " in your instructions")
				}
			}
		}
		
		for(rs in recipeSteps) {
			if(rs.instruction?.size() < 1) {
				result.success = false
				errors.add("Step " + rs.step + " is missing an instruction")
			}
			
			if(rs.ingredient) {
				if(rs.quantity <= 0) {
					result.success = false
					errors.add("Step " + rs.step + " includes an ingredient but no quantity")
				}
			}
		}
		
		if(errors.size() > 0) {
			if(errors.size() == 1) {
				result.errorMessage = errors.get(0)
				return result
			}
			
			for(error in errors) {
				if(error == errors.get(errors.size() - 1)) {
					result.errorMessage += error + "."
				} else {
					result.errorMessage += " " + error + ". "
				}
			}
		}
		
		return result
	}
}
