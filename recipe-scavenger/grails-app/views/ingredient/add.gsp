<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'news.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'contact.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css/jquery-te', file: 'jquery-te-Style.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'ingredient.css')}" type="text/css" />
		<title>Recipe Scavenger - Add New Ingredient</title>
	</head>
	<body>
		<div class="content">
			<div id="tabs">
				<ul>
					<li><h3><a href="#tabs-1">Add Ingredient</a></h3></li>
				</ul>
				<div id="tabs-1">
					<form class="ingredient-form">
						<g:render template="ingredientForm" model="['name': name, 'uomType': uomType, 'errors': errors]"></g:render>
						<br/>
						<g:actionSubmit value="Add" class="button" controller="Ingredient" action="doAdd"/>
						<g:actionSubmit value="Back" class="button" controller="Ingredient" action="index"/>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>