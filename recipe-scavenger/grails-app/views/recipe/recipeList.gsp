<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Recipe List</title>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'recipe.css')}" type="text/css">
	</head>
	<body>
		<div class="content">
			<div id="tabs">
				<ul>
					<li><h3><a href="#tabs-1">Recipes</a></h3></li>
				</ul>
				<div id="tabs-1">
					<g:if test="${session.user}">
						<g:link controller="recipe" action="toAddRecipe">Add Recipe</g:link>
					</g:if>
					<g:form>
						<table>
							<tr>
								<td>
									<p>Search by Name:&nbsp</p>
								</td>
								<td>
									<g:textField name="searchBox"/>
								</td>
								<td>
									<g:actionSubmit value="Search" controller="recipe" action="recipeList"/>
								</td>
							</tr>
						</table>
					</g:form>
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
							<tr class="hightlight-row">
								<td><g:link controller="recipe" action="viewRecipe" params="[id: recipe?.id]">${ recipe?.name }</g:link></td>
								<td>${recipe?.description}</td>
								<td>
									<g:link controller="recipe" action="viewRecipe" params="[id: recipe?.id]">[View]</g:link>
									<g:if test="${session.user}">
										<g:link controller="recipe" action="editRecipe" params="[id: recipe?.id]">[edit]</g:link>
									</g:if>
									<g:if test="${session.user?.admin}">
										<g:link controller="recipe" action="deleteRecipe" params="[id: recipe?.id]">[delete]</g:link>
									</g:if>
								</td>
							</tr>
						</g:each>
						</tbody>
					</table>
					<g:if test="${session.user}">
						<g:link controller="recipe" action="toAddRecipe">Add Recipe</g:link>
					</g:if>
				</div>
			</div>
		</div>
	</body>
</html>