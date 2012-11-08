<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Recipe Scavenger - Recipe Database</title>
	</head>
	<body>
		<div class="content">
			<div id="tabs">
				<ul>
					<li><h3><a href="#tabs-1">Recipes</a></h3></li>
				</ul>
				<div id="tabs-1">
					<g:link controller="recipe" action="toAddRecipe">Add Recipe</g:link>
					<table class="recipe-list">
						<thead>
							<tr>
								<th>Name</th>
								<th>Description</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
						<g:each in="${ recipeList }" var="recipe">
							<tr>
								<td>${ recipe?.name }</td>
								<td>${recipe?.description}</td>
								<td><g:link controller="ingredient" action="edit" params="[id: recipe?.id]">[edit]</g:link> <g:link controller="recipe" action="deleteRecipe" params="[id: recipe?.id]">[delete]</g:link></td>
							</tr>
						</g:each>
						</tbody>
					</table>
					<g:link controller="recipe" action="addRecipe">Add Recipe</g:link>
				</div>
			</div>
		</div>
	</body>
</html>