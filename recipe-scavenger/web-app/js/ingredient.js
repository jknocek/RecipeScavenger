$(document).ready(function (){
	var addIngredient = function() {
		var self = $(this);
		
		var id = self.data("id");
		var name = self.data("name");
		var uomBase = self.data("uombase");
		
		// Insert relevant data into form
		$("#ingredient-id").val(id);
		$("#ingredient-name").html(name);
		
		if(uomBase == 'u') {
			$("#uomUnit").show()
			$("#uomSelect").hide()
		} else {
			$("#uomUnit").hide()
			$("#uomSelect").show()
			
			if(uomBase == 'm') {
				$("#uomSelect").html($("#massUnits").html());
			}
			if(uomBase == 'v') {
				$("#uomSelect").html($("#volumeUnits").html());
			}
		}
		
		// Show
		$("#add-ingredient-dialog").dialog('open');
	}
	
	var deleteIngredient = function() {
		var id = $(this).data("id");
		alert("id = " + id);
	}
	
	
	$("a.addLink").click(addIngredient);
	$("a.removeLink").click(deleteIngredient);
	$("#add-ingredient-dialog").dialog({ 
		autoOpen: false, 
		modal:true, 
		height: 170,
		width: 400,
		resizable: false
	});
});