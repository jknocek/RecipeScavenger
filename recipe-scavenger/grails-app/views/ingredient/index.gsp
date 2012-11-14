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
					
					<div class="paginationWrapper">
						<div class="pagination"><g:paginate controller="ingredient" action="index" total="${ ingredientCount }" /></div>
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
										<g:actionSubmit value="Search" controller="ingredient" action="index"/>
									</td>
								</tr>
							</table>
						</g:form>
						<g:form controller="refrigerator" action="doAddIngredient">
							<table class="ingredient-list">
								<thead>
									<tr>
										<th><b>Name</b></th>
										<th><b>Usually measured by</b></th>
										<th style="text-align: center;"><b>In Refrigerator</b></th>
										<th></th>
									</tr>
								</thead>
								<tbody>
								<g:hiddenField name="ingredientPage" value="${ingredientList}"/>
								<g:hiddenField name="offset" value="${offset}"/>
								<g:each in="${ ingredients }" var="ingredient">
										<tr>
											<td>${ ingredient?.name }</td>
											<td>${ ingredient?.baseUomType }</td>
											<td style="text-align: center;">
												<g:if test="${session.user}">
													<g:checkBox name="ing" value="${ingredient?.name}" onchange="submit()" checked="${ingredient.ingredientInFrige}"/>
												</g:if>
											</td>
											<td><g:link controller="ingredient" action="edit" params="[id: ingredient?.id]" class="sidebar-highlight">[edit]</g:link> <g:link controller="ingredient" action="delete" params="[id: ingredient?.id]" class="sidebar-highlight">[delete]</g:link></td>
										</tr>
									
								</g:each>
								</tbody>
							</table>
						</g:form>
						<div class="pagination"><g:paginate controller="ingredient" max="10" action="index" total="${ ingredientCount }" /></div>
					</div>
					<div><g:link controller="ingredient" action="add">Add Ingredient</g:link></div>
					<g:form controller="refrigerator">
						<g:actionSubmit value="My Refrigerator" style="margin-left: 85%;" class="button" controller="refrigerator" action="refrigerator"/>
					</g:form>
				</div>
			</div>
		</div>
	</body>
</html>