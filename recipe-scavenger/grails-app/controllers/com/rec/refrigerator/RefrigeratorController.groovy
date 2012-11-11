package com.rec.refrigerator

import com.rec.ingredient.IngredientType
import com.rec.uom.UOM
import com.rec.user.User;
import com.rec.validation.IngredientTypeValidator

class RefrigeratorController {
	static scope = "session"
	
	def contents = []
	
	private def isLoggedIn() {
		return session?.user
	}
	
	def refrigerator() {
		if(session.foundError?.size() > 0) {
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}
		
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
			if(Refrigerator.findWhere(ingredient: ingredient, user: session.user)) {
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
		if(session.foundError?.size() > 0) {
			session.error = session.foundError
			session.foundError = ""
		} else {
			session.error = ""
		}
		
		if(!isLoggedIn()) {
			redirect(controller: 'home', action: 'home')
		}
		
		def refrigeratorId
		def frige
		
		refrigeratorId = params?.id
		frige = Refrigerator.findWhere(id: refrigeratorId.toLong())
		
		double amount
		def currentUom
		
		if(params.uom && params.amount) {
			currentUom = params.uom
			amount = Double.parseDouble(params.amount)
		} else {
			amount = frige.ingredientAmount
			currentUom = UOM.getCurrentUom(frige.baseUomType, frige.uomName)
		}

		def ingredientName = frige.ingredient.name
		def baseUomTypeDescription = UOM.getBaseUomDescription(frige.baseUomType)
		def possibleUoms = UOM.getPossibleUoms(frige.baseUomType)		
		
		return [
			id: frige.id,
			name: ingredientName,
			baseUomType: frige.baseUomType,
			uomType: baseUomTypeDescription,
			currentUom: currentUom,
			possibleUoms: possibleUoms,
			amount: amount
		]
	}
	
	def updateAmount() {
		def frigId = params.id.toLong()
		
		if(IngredientTypeValidator.validateQuantity(params.amount)) {
			def amount = Double.parseDouble(params.amount)
			def newUom = params.uom
			def origUom = params.origUom
			def baseUomType = params.baseUomType.charAt(0)
			
			redirect(action: 'editIngredient', params: [id: frigId, uom: newUom, amount: UOM.getDisplayAmount(baseUomType, origUom, newUom, amount)])
		} else {
			session.foundError = "Error: Amount must be numerical!"
			redirect(action: 'editIngredient', params: [id: frigId])
		}
	}
	
	def doEditIngredient() {
		if(!isLoggedIn()) {
			redirect(controller: 'home', action: 'home')
		}
		
		def refrigeratorID = params.id.toLong()
		
		if(IngredientTypeValidator.validateQuantity(params.amount)) {
			
			Refrigerator frig = Refrigerator.findWhere(id: refrigeratorID)
			
			frig.ingredientAmount = Double.parseDouble(params.amount)
			frig.uomName = UOM.getUomName(params.baseUomType.charAt(0), params.uom)
			frig.uomDisplay = UOM.getUomDisplay(params.baseUomType.charAt(0), params.uom)
	
			frig.save(flush:true)
			
			redirect(controller: 'refrigerator', action: 'refrigerator')
		} else {
			session.foundError = "Error: Amount must be numerical!"
			redirect(action: 'editIngredient', params: [id: refrigeratorID])
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
