package com.rec.refrigerator

import com.rec.ingredient.IngredientType
import com.rec.uom.UOM
import com.rec.user.User;
import com.rec.validation.IngredientTypeValidator
import groovy.json.JsonSlurper
import org.codehaus.groovy.grails.web.json.JSONObject

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
		
		session.refrigeratorContent = Refrigerator.findAll("from Refrigerator as r where r.user=? order by ingredient.name", [session.user])
		
		def temp = ""
	}

	
	def doAddIngredient() {
		if(!isLoggedIn()) {
			redirect(controller: 'home', action: 'home')
		}
		
		session.refrigeratorContent =  Refrigerator.findAll("from Refrigerator as r where r.user=? order by ingredient.name", [session.user]) // List of all ingredients in the user's refrigerator

		if(!params.id || !params.amount) {
			response.status = 404
			return
		}
		
		def ingredientType = IngredientType.findWhere(id: params.id.toLong());
		def amount = params.amount.toDouble();
		
		if(!isInFrige(ingredientType)) {
			Refrigerator frig = new Refrigerator()
			frig.ingredient = ingredientType
			frig.ingredientAmount = amount
			frig.user = session.user
			frig.baseUomType = ingredientType.baseUomType
			frig.uomDisplay = UOM.getBaseUomDisplay(ingredientType.baseUomType)
			frig.uomName = UOM.getBaseUom(ingredientType.baseUomType)
			frig.save(flush: true)
			session.refrigeratorContent.add(frig)
		}
		
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

		if(params.typeid)
		{
			def ingredientType = IngredientType.findWhere(id: params.typeid.toLong())
			ingredient = Refrigerator.findWhere(ingredient: ingredientType)
		}
		else
			ingredient = Refrigerator.findWhere(id: params.id.toLong())
			
		if(ingredient == null) {
			response.status = 404
			return
		}
		
		ingredient.delete()
		
		if(params.fromingredient)
			redirect(controller: 'ingredient', action: 'index')
		else
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
