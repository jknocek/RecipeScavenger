(function(window) {
	window.IngredientSearchDialog = function(params) {
		var dialogElement = $(params.element);
		var searchBoxElement = $("#ingredient-search-box");
		var spinnerElement = $("#img-dialog-spinner");
		var resultsElement = $("#ing-results");
		
		var showSpinner = false;
		var throttler = null;
		
		var IngredientClick = function(ev) {
			if(params.callback != undefined && typeof params.callback === "function")
				params.callback({
					id: $(this).data('id'),
					name: $(this).data('name'),
					uom: $(this).data('uom')
				});
			dialogElement.dialog('close');
		}
		
		var ProcessIngredients = function (data, textStatus, jqXHR) {
			resultsElement.html("");
			for(var x = 0; x < data.length; x++) {
				resultsElement
					.append('<tr><td>{0}</td><td>{1}</td><td><a data-name="{0}" data-uom="{1}" data-id="{2}" href="javascript: void(0)">select</a></td></tr>'
						.format(data[x].name, data[x].uom, data[x].id));
			}
			
			resultsElement.find("a").click(IngredientClick);
			showSpinner = false;
			spinnerElement.toggle(showSpinner);
		} 
		
		var IngredientRequest = function() {
			$.ajax({
				url: "/recipe-scavenger/search/ingredientSearchJson",
				data: { name: searchBoxElement.val() },
				dataType: 'json',
				success: ProcessIngredients
			});
		}
		
		var ThrottleRequest = function() {
			if(throttler != null)
				clearTimeout(throttler)
			
			throttler = setTimeout(IngredientRequest, 500);
			showSpinner = true;
			
			spinnerElement.toggle(showSpinner)
		}
		
		
		searchBoxElement.keyup(function(ev) {
			ThrottleRequest();
		});
		
		
		dialogElement.dialog({ 
			autoOpen: false, 
			modal:true, 
			height: 170,
			width: 400,
			resizable: false
		});
		
		spinnerElement.toggle(showSpinner);
		
		return {
			open: function () {
				searchBoxElement.val("");
				resultsElement.html("");
				dialogElement.dialog('open');
			}
		}
	};
})(window);