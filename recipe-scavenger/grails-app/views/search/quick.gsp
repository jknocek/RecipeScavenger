<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'ingredient.css')}" type="text/css" />
		<g:javascript src="replace.js" />
		<g:javascript src="ingredient-search-dialog.js" />
		<g:javascript src="quickorder.js"/>
		<title>Recipe Scavenger - Recipe Quick Search</title>
	</head>
	<body>
		<g:render template="ingredientSearchDialog" />
		<span style="display: none" id="volumeUnits">
			<g:each status="i" in="${volumeUoms}" var="uom">
				<g:if test="${i == 0}"><option selected="selected" value="${uom.getName()}">${uom.getName()}</option></g:if>
				<g:if test="${i != 0}"><option value="${uom}">${uom.getName()}</option></g:if>
			</g:each>
		</span>
		<span style="display: none" id="massUnits">
			<g:each status="i" in="${massUoms}" var="uom">
				<g:if test="${i == 0}"><option selected="selected" value="${uom.getName()}">${uom.getName()}</option></g:if>
				<g:if test="${i != 0}"><option value="${uom}">${uom.getName()}</option></g:if>
			</g:each>
		</span>
		<div class="content">
			<div id="tabs">
				<ul>
					<li><h3><a href="#tabs-1">By Ingredients</a></h3></li>
					<li><h3><a href="#tabs-2">By Categories</a></h3></li>
				</ul>
				<div id="tabs-1">
					<g:form controller="search" action="byIngredients">
						<div>Search by ingredients:</div><br />
						
						<div id="please-add-ingredient">Please add an ingredient to search</div>
						<table style="width: 100%" id="ingredient-table">
							<thead>
								<tr>
									<th width="33%">Name</th>
									<th width="33%">Quantity</th>
									<th width="33%"></th>
								</tr>
							</thead>
							<tbody id="ingredient-body">
							</tbody>
						</table>
						<div><a id="add-ingredient-anchor" href="javascript: void(0);">Add Ingredient</a></div>

						<g:actionSubmit id="ingredient-search-button" value="Search" style="margin-top: 10px" class="button" controller="search" action="byIngredients"/>
					</g:form>
				</div>
				<div id="tabs-2">
					<g:form controller="search">
						<label for="tags">Search by recipe categories:</label><br />
						<input class="ingredient-form" style="width: 350px" type="text" name="tags" /><br />

						<g:actionSubmit value="Search" style="margin-top: 10px" class="button" controller="search" action="byCategory"/>
					</g:form>
				</div>
			</div>
		</div>
	</body>
</html>