<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'news.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'contact.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css/jquery-te', file: 'jquery-te-Style.css')}" type="text/css">
		<title>Recipe Scavenger - Ingredient Database</title>
	</head>
	<body>
		<div class="content">
			<g:errorDisplay/>
			<span>Ingredients:</span>
			<div><g:link controller="ingredient" action="add">Add Ingredient</g:link></div>
			
			<table style="width:100%; table-layout: fixed;">
				<thead>
					<tr>
						<th>Name</th>
						<th>Usually measured by</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${ ingredients }" var="ingredient">
					<tr>
						<td>${ ingredient?.name }</td>
						<td>${ ingredient?.baseUomType }</td>
						<td><g:link controller="ingredient" action="edit" params="[id: ingredient?.id]" class="sidebar-highlight">[edit]</g:link> <g:link controller="ingredient" action="delete" params="[id: ingredient?.id]" class="sidebar-highlight">[delete]</g:link></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			
			<div><g:link controller="ingredient" action="add">Add Ingredient</g:link></div>
		</div>
	</body>
</html>