package com.rec.ingredient

import com.rec.validation.*
import com.rec.refrigerator.Refrigerator
import com.rec.recipe.*
import com.rec.uom.*

class IngredientController {
	static scope = "session"
	
	static allowedMethods = [
		index: ['GET', 'POST'],
		add: ['GET', 'POST']		
	]
	
	def errorDictionary
	def offset = null
	def max = null
		
	
	def IngredientController() {
		
	}
	
	private def IsUserAdmin() {
		return session.user?.admin
	}

	
	def index() {
		if(session.foundError?.size() > 0) {
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}
		
		if(!session.user) {
			redirect(controller: 'home', action: 'home')
		}
		
		session.refrigeratorContent = Refrigerator.findAllWhere(user: session.user)

		def keepPage = params?.keepPage
		def searchBox = params?.searchBox
		def temp = params
		
		max = params.max ?: max && keepPage ? max : 10;
		offset = params.offset ?: offset && keepPage ? offset : 0;

		def displayIngredients = []
		def ingredients = []
		def totalCount
		
		
		if (!searchBox) {
			ingredients = IngredientType.list(sort: "name", order: "asc", max: max, offset: offset)
			totalCount = ingredients.getTotalCount()
		} else {
			ingredients = IngredientType.findAll("from IngredientType as i where i.name like ?", ["%"+searchBox+"%"])
			totalCount = ingredients.size()
		}
		
		ingredients.each {
			boolean inFrige
			inFrige = isInFrige(it)
			displayIngredients.add([
				name: it.name, 
				baseUomType: it.baseUomType, 
				baseUomName: IngredientTypeValidator.getUserFriendlyUomType(it.baseUomType), 
				id: it.id, 
				ingredientInFrige: inFrige
			])
		}
		
		return [
			ingredients: displayIngredients,
			ingredientCount: totalCount,
			volumeUoms: UOM.getPossibleUoms('v' as char),
			massUoms: UOM.getPossibleUoms('m' as char),
			pagemax: max,
			pageoffset: offset
		]
	}
	
	
	
	boolean isInFrige(IngredientType ingredient) {
		if(session.user){
			if(Refrigerator.findWhere(ingredient: ingredient, user: session.user)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	
	def add() {
		if(session.foundError?.size() > 0) {
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}
		
		if(!session.user) {
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
			
			redirect(controller: 'ingredient', action: '', params: [keepPage: true])
		}
	}
	
	
	def edit() {
		if(session.foundError?.size() > 0) {
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}
		
		if(!session.user) {
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
			
			redirect(controller: 'ingredient', action: 'index', params: [keepPage: true])
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
		
		try {
			def frigeContents = Refrigerator.findAllWhere(ingredient: type)
			if(frigeContents) {
				for(content in frigeContents) {
					if(content.ingredient.id == type.id)
					{
						content.delete(flush: true)
					}
				}
			} 
			
			def recipeContents = RecipeContent.findWhere(ingredient: type)
			if(recipeContents) {
				session.foundError = "Sorry, " + type.name + " is used in recipe '"+ recipeContents.recipe.name +"'. It can not be removed until '"+ recipeContents.recipe.name +"' is removed."
			}
			else {
				type.delete(flush: true)
			}
		} catch(Exception e) {
			session.foundError = "Error, unable to remove ingredient!"
		}
		
		redirect(controller: 'ingredient', action: 'index', params: [keepPage: true])
	}
}
