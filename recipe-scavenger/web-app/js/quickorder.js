$(document).ready(function() {
	// Treat this like a module. Only things inside this function have access to the 
	// things defined in this function.
	
	// List of ingredients to search by
	var ingredients = [];
	
	var isLoading = false;
	var throttler = null;

	// Member functions
	var UpdateIngFormState = function UpdateIngFormState() {
		var hasIngredients = ingredients.length > 0;
		var canSearch = hasIngredients;
		
		$("#ingredient-table").toggle(hasIngredients)
		$("#please-add-ingredient").toggle(!hasIngredients)
		$("#ingredient-search-button").button({disabled: !canSearch})
		/*if(canSearch)
			$("#ingredient-search-button").removeAttr('disabled')
		else
			$("#ingredient-search-button").attr('disabled','disabled')*/
	}
	
	
	var ReadQuantites = function() {
		$(".quantity").each(function(index, el) {
			ingredients[index].quantity = $(el).val();
		});
	}
	
	
	var RemoveIngredient = function(e) {
		var id = $(this).data('id');
		
		for(var i = 0; i < ingredients.length; i++) {
			if(id == ingredients[i].id)
				break;
		}
		
		if(i >= ingredients.length)
			return;
		
		ReadQuantites();
		ingredients.splice(i, 1);
		
		RenderIngredients();
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
			var uom = ingr.uom == "v" ? "litres" : ingr.uom == "m" ? "killograms" : "";
			
			selector.append(template.format(ingr.name, uom, ingr.id, ingr.quantity));
			selector.find(".removelink").click(RemoveIngredient);
		}
		
		UpdateIngFormState();
	}
	
	var IngredientAdded = function IngredientAdded(ingredient) {
		var present = false;
		
		for(var i = 0; i < ingredients.length; i++) {
			if(ingredient.id == ingredients[i].id)
				present = true;
		}
		
		if(!present) {
			ReadQuantites();
			ingredients.push({
				id: ingredient.id,
				name: ingredient.name,
				uom: ingredient.uom,
				quantity: 0.0
			});
		}
		
		RenderIngredients();
	}
	
	var dialog = new IngredientSearchDialog({ element: $("#add-ingredient-dialog"), callback: IngredientAdded});

	// Setup and bootstrapping code
	$("#add-ingredient-anchor").click(function() {
		dialog.open();
	});
	
	$(".button").button();
	
	UpdateIngFormState();
});