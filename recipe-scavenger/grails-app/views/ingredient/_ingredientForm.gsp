<label for="name">Name of the ingredient:</label><br />
<input class="ingredient-form" type="text" name="name" value="${ name ?: "" }"><br />
<div class="validation-error">${ errors.name ?: "&nbsp" }</div>
<label for="uomType">Ingredient is typically measured in bulk by:</label><br />
<select class="ingredient-form" name="uomType">
	<option value="v" ${ uomType && uomType == 'v' ? "selected=\"selected\"" : "" }>Volume (e.g. gallons, liters)</option>
	<option value="m" ${ uomType && uomType == 'm' ? "selected=\"selected\"" : "" }>Mass (e.g. pounds, ounces, grams)</option>
	<option value="u" ${ uomType && uomType == 'u' ? "selected=\"selected\"" : "" }>Units (e.g. an apple, a carrot)</option>
</select><br />