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
		<g:selectLeftSideBar/>
		<div id="content">
			<g:errorDisplay/>
			<span>Ingredients:</span>
			<table style="width:100%">
				<thead>
					<tr>
						<th>Name</th>
						<th>Base UOM</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${ ingredients }" var="ingredient">
					<tr>
						<td>${ ingredient?.name }</td>
						<td>${ ingredient?.baseUomName }</td>
					</tr>
				</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>