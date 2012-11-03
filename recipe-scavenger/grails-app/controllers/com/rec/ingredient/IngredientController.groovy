package com.rec.ingredient
import com.rec.validation.*

class IngredientController {
	static scope="session"
	static allowedMethods = [
		listIngredients: 'GET',
		addIngredient: ['GET', 'POST']		
	]
	
	def errorDictionary
		
	
	def IngredientController() {
		
	}
	
	private def IsUserAdmin() {
		return session.user?.admin
	}

	
	def index() {
		if(!IsUserAdmin()) {
			redirect(controller: 'home', action: 'home')
		}

		def ingredients = IngredientType.list(sort: "name", order: "asc")
		def displayIngredients = []
		
		ingredients.each {
			displayIngredients.add([name: it.name, baseUomType: IngredientTypeValidator.getUserFriendlyUomType(it.baseUomType), id: it.id])
		}
		
		return [ingredients: displayIngredients]
	}
	
	
	def add() {
		if(!IsUserAdmin()) {
			redirect(controller: 'home', action: 'home')
		}
		
		def errors = errorDictionary
		errorDictionary = null 
		return [
			name: null,
			uomType: null,
			errors: [errors]
		]
	}
	
	def doAdd() {
		def validationResult = IngredientTypeValidator.Validate(params.name, params.uomType)
			
		if(!validationResult.success) {
			errorDictionary = validationResult.errorDictionary
			redirect(action: 'add', params: [name: params.name, uomType: params.uomType, errors: errorDictionary])
		} else {
			IngredientType newType = new IngredientType(name : params.name, baseUomType: params.uomType )
			newType.save()
			
			redirect(controller: 'ingredient', action: '')
		}
	}
	
	
	def edit() {
		if(!IsUserAdmin()) {
			redirect(controller: 'home', action: 'home')
		}

		def type = IngredientType.findWhere(id: params.id.toLong())
		
		if(type == null) {
			response.status = 404
			return
		}
		
		if(request.method == 'GET') {
			return [
				id: type.id,
				name: type.name,
				uomType: type.baseUomType,
				errors: [:]
			]
		}
		else if(request.method == 'POST') {
			def validationResult = IngredientTypeValidator.Validate(params.name, params.uomType)
				
			if(!validationResult.success) {
				return [
					id: params.id,
					name: params.name,
					uomType: params.baseUomType,
					errors: validationResult.errorDictionary
				]
			}
			
			type.name = params.name
			type.baseUomType = params.uomType
			type.save()
			
			redirect(controller: 'ingredient', action: 'index')
		}
	}
	
	
	def delete() {
		if(!IsUserAdmin()) {
			redirect(controller: 'home', action: 'home')
		}
		
		def type = IngredientType.findWhere(id: params.id.toLong())
		
		if(type == null) {
			response.status = 404
			return
		}
		
		type.delete()
		redirect(controller: 'ingredient', action: 'index')
	}
}
