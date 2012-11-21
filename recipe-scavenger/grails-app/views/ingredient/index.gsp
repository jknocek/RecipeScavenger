<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'ingredient.css')}" type="text/css" />
		<g:javascript src="ingredient.js"/>
		<title>Recipe Scavenger - Ingredient Database</title>
	</head>
	<body>
		<div id="add-ingredient-dialog" >
			<g:form controller="refrigerator" action="doAddIngredient">
				<div class="userForm">
					<br/>
					<table style="text-align:left; width:300px;">
						<tr>
							<td colspan="3">
								<p>Add '<span id="ingredient-name"></span>' to refrigerator</p>
								<input type="hidden" id="ingredient-id" name="id" />
							</td>
						</tr>
						<tr>
							<td>
								<br/>
							</td>
						</tr>
						<tr> 
							<td>
								<label for='amount'>Amount:</label>
							</td>
							<td>
								<input 	id="ingredient-amount" type='text' name='amount'/>
							</td>
							<td>
								<select id="uomSelect" name='uomType'></select>
								<p id="uomUnit">units</p>
							</td>
						</tr>
					</table>
				</div>
				<br/>
				<div style="float: right;">
					<g:actionSubmit value="Add" class="button" controller="refrigerator" action="doAddIngredient" />
				</div>
			</g:form>
			<span style="visible: false" id="volumeUnits">
				<g:each status="i" in="${volumeUoms}" var="uom">
					<g:if test="${i == 0}"><option selected="selected" value="${uom}">${uom.getName()}</option></g:if>
					<g:if test="${i != 0}"><option value="${uom}">${uom.getName()}</option></g:if>
				</g:each>
			</span>
			<span style="visible: false" id="massUnits">
				<g:each status="i" in="${massUoms}" var="uom">
					<g:if test="${i == 0}"><option selected="selected" value="${uom}">${uom.getName()}</option></g:if>
					<g:if test="${i != 0}"><option value="${uom}">${uom.getName()}</option></g:if>
				</g:each>
			</span>
		</div>
		<div class="content">
			<div id="tabs">
				<ul>
					<li><h3><a href="#tabs-1">Ingredients</a></h3></li>
				</ul>
				<div id="tabs-1">
					<div><g:link controller="ingredient" action="add">Create ingredient type</g:link></div>
					
					<div class="paginationWrapper">
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
						<div class="pagination"><g:paginate controller="ingredient" action="index" total="${ ingredientCount }" max="${pagemax}" offset="${pageoffset }" /></div>
						<g:hiddenField name="ingredientPage" value="${ingredientList}"/>
						<g:hiddenField name="offset" value="${offset}"/>
						<table class="ingredient-list">
							<thead>
								<tr>
									<th><b>Name</b></th>
									<th><b>Usually measured by</b></th>
									<th style="text-align: center;"><b>My Refrigerator</b></th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<g:each in="${ ingredients }" var="ingredient">
									<tr class="hightlight-row">
										<td>${ ingredient?.name }</td>
										<td>${ ingredient?.baseUomName }</td>
										<td style="text-align: center;">
											<g:if test="${session.user}">
												<g:if test="${ingredient.ingredientInFrige }">
													<g:link controller="refrigerator" action="doRemoveIngredient" params="[typeid: ingredient?.id, fromingredient: true]">Remove from refrigerator</g:link>
												</g:if>
												<g:if test="${!ingredient.ingredientInFrige && session.user}">
													<a class="addLink" data-id="${ingredient?.id}" data-name="${ingredient?.name}" data-uombase="${ingredient?.baseUomType}" href="javascript: void(0);">Add to refrigerator</a>
												</g:if>
											</g:if>
										</td>
										<td>
											<g:if test="${session.user?.admin}">
												<g:link controller="ingredient" action="edit" params="[id: ingredient?.id]" class="sidebar-highlight">[edit]</g:link>
												<g:link controller="ingredient" action="delete" params="[id: ingredient?.id]" class="sidebar-highlight">[delete]</g:link>
											</g:if>
										</td>
									</tr>
								</g:each>
							</tbody>
						</table>
						<div class="pagination"><g:paginate controller="ingredient" action="index" total="${ ingredientCount }" max="${pagemax}" offset="${pageoffset }" /></div>
					</div>
					<div><g:link controller="ingredient" action="add">Create ingredient type</g:link></div>
					<g:form controller="refrigerator">
						<g:actionSubmit value="My Refrigerator" style="margin-left: 85%;" class="button" controller="refrigerator" action="refrigerator"/>
					</g:form>
				</div>
			</div>
		</div>
	</body>
</html>