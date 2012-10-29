<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'news.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'contact.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css/jquery-te', file: 'jquery-te-Style.css')}" type="text/css">
		<title>Recipe Scavenger - Add New Ingredient</title>
	</head>
	<body>
		<g:selectLeftSideBar/>
		<div class="content">
			<g:errorDisplay/>
			<span>Add a new ingredient:</span>
			<form action="add" method="POST">
				<g:render template="ingredientForm" model="['name': name, 'uomType': uomType, 'errors': errors]"></g:render>
				
				<input type="submit" value="Add" />
			</form>
		</div>
	</body>
</html>