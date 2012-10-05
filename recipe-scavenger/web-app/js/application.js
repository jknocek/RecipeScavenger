$(document).ready(function() {
	
	// Highlight links on hover
	$('.header-highlight').hover( 
			 function(){ $(this).css('background-color','#969696') }, 
			 function(){ $(this).css('background-color','#7F7F7F') } 
	);
	
	$('.sidebar-highlight').hover( 
			 function(){ $(this).css('background-color','#4F4F4F') }, 
			 function(){ $(this).css('background-color','#D0D4D9') } 
	);
	
	// Image slider jquery call
	$('.features').jshowoff({
		speed:6000,
		cssClass: 'thumbFeatures',
		effect: 'slideLeft' 
	});
	
	// login dialog
	$("#login-dialog").dialog({ autoOpen: false, 
								modal:true, 
								height: 150,
								width: 400,
								resizable: false
	});

	// login dialog link
    $("#login-dialog-button").click(
        function () {
            $("#login-dialog").dialog('open');
            return false;
        }
    );
	
    // terms dialog
    $("#terms-dialog").dialog({ autoOpen: false, 
								modal:true, 
								height: 600,
								width: 800,
								resizable: false,
								draggable: false
	});
	
	// login dialog link
	$("#terms-dialog-button").click(
		function () {
			$("#terms-dialog").dialog('open');
			return false;
		}
	);
	
	// Rich Text editor
	$(".editor").jqte();
	
	// Tabs
	$( "#tabs" ).tabs();
	
	// Accordion
	$( ".accordion" ).accordion({
		collapsible: true,
		active: false,
		autoHeight: false
	});
	
	$( ".openAccordion" ).accordion({
		collapsible: true,
		active: 0,
		autoHeight: false
	});
});