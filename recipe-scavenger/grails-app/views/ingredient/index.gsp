<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'news.css')}" type="text/css" />
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'contact.css')}" type="text/css" />
		<link rel="stylesheet" href="${resource(dir: 'css/jquery-te', file: 'jquery-te-Style.css')}" type="text/css" />
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'ingredient.css')}" type="text/css" />
		<title>Recipe Scavenger - Ingredient Database</title>
	</head>
	<body>
		<div class="content">
			<div id="tabs">
				<ul>
					<li><h3><a href="#tabs-1">Ingredients</a></h3></li>
				</ul>
				<div id="tabs-1">
					<div><g:link controller="ingredient" action="add">Add Ingredient</g:link></div>
					<g:form controller="refrigerator" action="doAddIngredient">
						<table class="ingredient-list">
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
										<td>
											<g:if test="${session.user}">
												<g:checkBox name="ing" value="${ingredient?.name}" onchange="submit()" checked="${ingredient in frige }"/> <!-- checked="${ingredient in frige }" -->
											</g:if>
										</td>
										<td><g:link controller="ingredient" action="edit" params="[id: ingredient?.id]" class="sidebar-highlight">[edit]</g:link> <g:link controller="ingredient" action="delete" params="[id: ingredient?.id]" class="sidebar-highlight">[delete]</g:link></td>
									</tr>
								
							</g:each>
							</tbody>
						</table>
					</g:form>
					<div><g:link controller="ingredient" action="add">Add Ingredient</g:link></div>
				</div>
			</div>
		</div>
	</body>
</html>