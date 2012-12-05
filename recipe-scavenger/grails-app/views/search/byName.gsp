<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'ingredient.css')}" type="text/css" />
		<g:javascript src="ingredient.js"/>
		<title>Recipe Scavenger - Search Results</title>
	</head>
	<body>
		<div class="content">
			<div id="tabs">
				<ul>
					<li><h3><a href="#tabs-1">Recipe Search Results</a></h3></li>
				</ul>
				<div id="tabs-1">
					<table class="recipe-list">
						<thead>
							<tr>
								<th><b>Name</b></th>
								<th><b>Description</b></th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<g:each in="${ recipeList }" var="recipe">
							<tr>
								<td>${ recipe?.name }</td>
								<td>${recipe?.description}</td>
								<td></td>
							</tr>
						</g:each>
						</tbody>
					</table>
					
				</div>
			</div>
		</div>
	</body>
</html>