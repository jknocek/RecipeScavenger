<%@ page import="com.rec.recipe.RecipeController" %>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'ingredient.css')}" type="text/css">
		<g:javascript src="ingredient.js"/>
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
								<table>
									<tr>
										<td>
											<p>Search by Name:&nbsp</p>
										</td>
										<td>
											<g:textField name="searchBox"/>
										</td>
										<td>
											<g:actionSubmit value="Search" controller="recipe" action="selectIngredient"/>
										</td>
									</tr>
								</table>
							<table class="ingredient-list">
								<thead>
									<tr>
										<th><b>Name</b></th>
										<th><b>Usually measured by</b></th>
										<th>
									</tr>
								</thead>
								<tbody>
									<g:each in="${ ingredients }" var="ingredient">
										<tr class="hightlight-row">
											<td>${ ingredient?.name }</td>
											<td>${ ingredient?.baseUomName }</td>
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