package com.rec.refrigerator
import com.rec.ingredient.IngredientType

class RefrigeratorController {
	def static scope = "session"
	
	def refrigeratorContent = []
	def contents = []
	
	private def isLoggedIn() {
		return session?.user
	}
	
	def refrigerator() {
		if(!isLoggedIn()) {
			redirect(controller: 'home', action: 'home')
		}
		
		refrigeratorContent = Refrigerator.findWhere(user: session.user)
		
		def temp = ""
	}

	
	def doAddIngredient() {
		if(!isLoggedIn()) {
			redirect(controller: 'home', action: 'home')
		}
		
		Refrigerator frig = new Refrigerator()
		
		def ingredientList = params.ing
		
		frig.ingredient = IngredientType.findWhere(name: params.ing)
		frig.ingredientAmount = 0
		frig.user = session.user
		
		/*
		for(ingredient in ingredientList) {
			if(ingredient not in frig, att to frig)
		}

		for(ingredient in frige) {
			if(ingredient not in ingredientList, remove from frig)
		}
		*/
		frig.save()
		
		redirect(controller: 'ingredient', action: 'index')
	}
		
	def doRemoveIngredient() {
		if(!isLoggedIn()) {
			redirect(controller: 'home', action: 'home')
		}
		
		Refrigerator ingredient = new Refrigerator()
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
