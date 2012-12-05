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

		ingredients.splice(i, 1);
		
		RenderIngredients();
	}
	
	
	var QuantityChange = function(e) {
		var id = $(this).data('id')
		var ingredient = GetIngredientForId(id)
		
		if(ingredient === undefined)
			return;
		
		// Validate the value
		var newValue = /\d*\.?\d{0,9}/.exec($(this).val())
		
		$(this).val(newValue)
		
		ingredient.quantity = newValue
	}
	
	
	var RenderIngredients = function() {
		var selector = $("#ingredient-body");
		selector.html("");
		
		var template = 
		'<tr>' +
			'<td><input type="hidden" name="{2}.id" value="{2}" />{0}</td>'+
			'<td><input name="{2}.quantity" type="number" max="20" data-id="{2}" class="quantity" value="{3}"/> {1}</td>'+
			'<td><a href="javascript: void(0)" data-id="{2}" class="removelink">remove</td>'+
		'</tr>';
		
		for(var i = 0; i < ingredients.length; i++) {
			var ingr = ingredients[i];
			var uom = ingr.uom == "v" ? "litres" : ingr.uom == "m" ? "grams" : "";
			
			selector.append(template.format(ingr.name, uom, ingr.id, ingr.quantity));
			selector.find(".removelink").click(RemoveIngredient);
			selector.find(".quantity").change(QuantityChange);
		}
		
		UpdateIngFormState();
	}
	
	var IngredientAdded = function IngredientAdded(ingredient) {
		if(GetIngredientForId(ingredient.id) !== undefined)
			return;
		
		ingredients.push({
			id: ingredient.id,
			name: ingredient.name,
			uom: ingredient.uom,
			quantity: 0.0
		});
		
		RenderIngredients();
	}
	
	var dialog = new IngredientSearchDialog({ callback: IngredientAdded});

	// Setup and bootstrapping code
	$("#add-ingredient-anchor").click(function() {
		dialog.open();
	});
	
	$(".button").button();
	
	UpdateIngFormState();
});