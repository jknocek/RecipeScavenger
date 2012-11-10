package com.rec.refrigerator

import com.rec.ingredient.IngredientType
import com.rec.uom.UOM

class RefrigeratorController {
	static scope = "session"
	
	def contents = []
	
	private def isLoggedIn() {
		return session?.user
	}
	
	def refrigerator() {
		if(!isLoggedIn()) {
			redirect(controller: 'home', action: 'home')
		}
		
		session.refrigeratorContent = Refrigerator.findAllWhere(user: session.user)
		
		def temp = ""
	}

	
	def doAddIngredient() {
		if(!isLoggedIn()) {
			redirect(controller: 'home', action: 'home')
		}
		
		session.refrigeratorContent = Refrigerator.findAllWhere(user: session.user)
		
		def ingredientList = []
		ingredientList = params.ing
		
		def paramClass = params.ing.getClass()
		
		if(paramClass.name == "java.lang.String") {
			IngredientType ingredient = IngredientType.findWhere(name: ingredientList)
			
			if(!isInFrige(ingredient)) {
				Refrigerator frig = new Refrigerator()
				frig.ingredient = ingredient
				frig.ingredientAmount = 0
				frig.user = session.user
				frig.baseUomType = ingredient.baseUomType
				frig.uomDisplay = UOM.getBaseUomDisplay(ingredient.baseUomType)
				frig.uomName = UOM.getBaseUom(ingredient.baseUomType)
				frig.save(flush: true)
				session.refrigeratorContent.add(frig)
			}
			
			for(frige in session.refrigeratorContent) {
				boolean remove = true

				if(frige.ingredient.name == ingredientList) {
					remove = false
				}

				if(remove) {
					frige.delete(flush: true)
				}
			}
		} else {			
			for(ingredientType in ingredientList) {
				IngredientType ingredient = IngredientType.findWhere(name: ingredientType)
				
				if(!isInFrige(ingredient)) {
					Refrigerator frig = new Refrigerator()
					frig.ingredient = ingredient
					frig.ingredientAmount = 0
					frig.user = session.user
					frig.baseUomType = ingredient.baseUomType
					frig.uomDisplay = UOM.getBaseUomDisplay(ingredient.baseUomType)
					frig.uomName = UOM.getBaseUom(ingredient.baseUomType)
					frig.save(flush: true)
					session.refrigeratorContent.add(frig)
				}
			}
			
			for(frige in session.refrigeratorContent) {
				boolean remove = true
				for(ingredientType in ingredientList) {
					if(frige.ingredient.name == ingredientType) {
						remove = false
					}
				}
				
				if(remove) {
					frige.delete(flush: true)
				}
			}
		}
		
		session.refrigeratorContent = Refrigerator.findAllWhere(user: session.user)
		
		redirect(controller: 'ingredient', action: 'index')
	}
	
	boolean isInFrige(IngredientType ingredient) {
		if(session.user){
			if(Refrigerator.findWhere(ingredient: ingredient)) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
		
	def doRemoveIngredient() {
		if(!isLoggedIn()) {
			redirect(controller: 'home', action: 'home')
		}
		
		Refrigerator ingredient;
		ingredient = Refrigerator.findWhere(id: params.id.toLong())
			
		if(ingredient == null) {
			response.status = 404
			return
		}
		
		ingredient.delete()
		
		redirect(controller: 'refrigerator', action: 'refrigerator')
	}

	def editIngredient() {
		if(!isLoggedIn()) {
			redirect(controller: 'home', action: 'home')
		}
	}
	
	def doEditIngredient() {
		if(!isLoggedIn()) {
			redirect(controller: 'home', action: 'home')
		}
	}
	
	def isInRefrigerator() {
		def contents = Refrigerator.list
		def item = new Refrigerator()
		item.ingredient = params.ing
		
		contents.each() {
			if (item.ingredient == contents[].ingredient) {
				return true
			}
			
			return false
		}
		
	}
}
