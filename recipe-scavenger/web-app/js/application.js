$(document).ready(function() {
	
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
	
    // terms dialog
    $("#ingredients-dialog").dialog({ autoOpen: false, 
								modal:true, 
								height: 600,
								width: 800,
								resizable: true
	});
	
	// Ingredients dialog link
	$("#ingredients-dialog-button").click(
		function () {
			$("#ingredients-dialog").dialog('open');
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
	
	// Datepicker
	$( ".datepicker" ).datepicker({
	    showOn: "button",
	    buttonImage: "images/calendar.png",
	    buttonImageOnly: true,
	    dateFormat:'mm-dd-yy',
	    yearRange: "c-120:c",
	    changeYear: true,
	    changeMonth: true,
	    showMonthAfterYear: true, //this is what you are looking for
	    maxDate:0
	});
	
	// Sticky sidebar
	$('.stickbox').stickySidebar({speed: 400, constrain: true, padding: 73});
});