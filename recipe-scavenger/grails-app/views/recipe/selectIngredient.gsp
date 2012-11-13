<%@ page import="com.rec.recipe.RecipeController" %>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'recipe.css')}" type="text/css">
		<title>Recipe Scavenger - Add New Recipe</title>
	</head>
	<body>
		<div class="content">
			<div id="tabs">
				<ul>
					<li><h3><a href="#tabs-1">Select Ingredient</a></h3></li>
				</ul>
				<div id="tabs-1">
						<g:form controller="recipe">
							<table class="ingredient-list">
								<thead>
									<tr>
										<th>Name</th>
										<th>Usually measured by</th>
										<th>
									</tr>
								</thead>
								<tbody>
									<g:each in="${ ingredients }" var="ingredient">
										<tr>
											<td>${ ingredient?.name }</td>
											<td>${ ingredient?.baseUomType }</td>
											<td><g:radio name="selectedIngredient" value="${ingredient?.id}"/></td>
										</tr>
									</g:each>
								</tbody>
							</table>
							<g:actionSubmit value="Select" class="button" controller="Recipe" action="addRecipeContent"/>
						</g:form>
				</div>
			</div>
		</div>
	</body>
</html>