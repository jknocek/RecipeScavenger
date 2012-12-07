$(document).ready(function() {
	// Treat this like a module. Only things inside this function have access to the 
	// things defined in this function.
	
	// List of ingredients to search by
	var ingredients = [];
	
	var isLoading = false;
	var throttler = null;

	// Member functions
	var GetIngredientForId = function(id) {
		for(var i = 0; i < ingredients.length; i++) {
			if(id == ingredients[i].id)
				return ingredients[i];
		}
	
		return undefined;
	}
	
	var UpdateIngFormState = function UpdateIngFormState() {
		var hasIngredients = ingredients.length > 0;
		var canSearch = hasIngredients;
		
		$("#ingredient-table").toggle(hasIngredients)
		$("#please-add-ingredient").toggle(!hasIngredients)
		$("#ingredient-search-button").button({disabled: !canSearch})
	}
		
	
	var RemoveIngredient = function(e) {
		var id = $(this).data('id');
		var i;
		
		for(i = 0; i < ingredients.length; i++) {
			if(id == ingredients[i].id)
				break;
		}
		
		if(i >= ingredients.length)
			return

		var success = function(data, textStatus, jqXHR) {
			if(!data.success)
				return;
			
			ingredients.splice(i, 1);
			RenderIngredients();
		}
		
		$.ajax({
			url: "/recipe-scavenger/search/removeSearchIngredientJson",
			data: { ingredientId: id },
			dataType: 'json',
			success: success
		})
	}
	
	
	var QuantityChange = function(e) {
		var id = $(this).data('id')
		var ingredient = GetIngredientForId(id)
		
		if(ingredient === undefined)
			return;
		
		// Validate the value
		var newValue = /\d*\.?\d{0,9}/.exec($(this).val())[0]
		
		$(this).val(newValue)
		
		ingredient.quantity = newValue
		
		$.ajax({
			url: "/recipe-scavenger/search/setSearchIngredientQuantityJson",
			data: { ingredientId: ingredient.id, quantity: ingredient.quantity},
			dataType: 'json'
		})
	}
	
	var RenderIngredients = function() {
		var selector = $("#ingredient-body");
		selector.html("");
		
		var selectTemplate = '<select name="[{1}].uom">{0}</select>'
		var template = 
		'<tr>' +
			'<td>{0}</td>'+
			'<td><input name="[{2}].quantity" max="20" data-id="{2}" class="quantity" value="{3}"/> {1}</td>'+
			'<td><a href="javascript: void(0)" data-id="{2}" class="removelink">remove</td>'+
		'</tr>';
		
		for(var i = 0; i < ingredients.length; i++) {
			var ingr = ingredients[i];
			var uoms;
			
			if(ingr.baseUomType == "v")
				uoms = selectTemplate.format($("#volumeUnits").html(), ingr.id)
			else if(ingr.baseUomType == "m")
				uoms = selectTemplate.format($("#massUnits").html(), ingr.id)
			else
				uoms = "units";
			
			selector.append(template.format(ingr.name, uoms, ingr.id, ingr.quantity));
		}
		
		selector.find(".removelink").click(RemoveIngredient);
		selector.find(".quantity").change(QuantityChange);
		
		UpdateIngFormState();
	}
	
	var IngredientAdded = function IngredientAdded(ingredient) {
		if(GetIngredientForId(ingredient.id) !== undefined) {
			dialog.close()
			return;
		}
		
		$.ajax({
			url: "/recipe-scavenger/search/addSearchIngredientJson",
			data: { ingredientId: ingredient.id },
			dataType: 'json',
			success: function(data, textStatus, jqXHR) {
				if(!data.success)
					return;
				
				ingredients.push({
					id: ingredient.id,
					name: ingredient.name,
					baseUomType: ingredient.baseUomType,
					quantity: 0.0
				});

				RenderIngredients();
			}
		})
	}
	
	var dialog = new IngredientSearchDialog({ callback: IngredientAdded });

	// Setup and bootstrapping code
	$("#add-ingredient-anchor").click(function() {
		dialog.open();
	});
	
	$(".button").button();
	
	$.ajax({
		url: "/recipe-scavenger/search/getExistingIngredientsJson",
		dataType: 'json',
		success: function(data) {
			ingredients = data
			RenderIngredients()
		}
	})
	
	UpdateIngFormState();
});