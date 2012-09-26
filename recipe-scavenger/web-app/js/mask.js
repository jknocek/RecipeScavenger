$(document).ready(function() {
	$(".phone").mask("999-999-9999");
	$(".faxNumber").mask("999-999-9999");
	$(".cardNumber").mask("9999-9999-9999-9999");
	$(".date").mask("99/99/9999");
	
	$('[placeholder]').focus(function() {
		  var input = $(this);
		  if (input.val() == input.attr('placeholder')) {
		    input.val('');
		    input.removeClass('placeholder');
		  }
		}).blur(function() {
		  var input = $(this);
		  if (input.val() == '' || input.val() == input.attr('placeholder')) {
		    input.addClass('placeholder');
		    input.val(input.attr('placeholder'));
		  }
		}).blur();
	
	$('[placeholder]').parents('form').submit(function() {
		  $(this).find('[placeholder]').each(function() {
		    var input = $(this);
		    if (input.val() == input.attr('placeholder')) {
		      input.val('');
		    }
		  })
		});
});