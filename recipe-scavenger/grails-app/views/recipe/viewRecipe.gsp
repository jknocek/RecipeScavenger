<%@ page import="com.rec.recipe.RecipeController" %>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>${recipe.name}</title>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'recipe.css')}" type="text/css">
	</head>
	<body>
		<div class="content">
			<div id="tabs">
				<ul>
					<li><h3><a href="#tabs-1">${recipe.name}</a></h3></li>
				</ul>
				<div id="tabs-1">
					<div class="openAccordion">
						<h3 style="text-align: center;"><a href="#">Recipe</a></h3>
						<div>
							<table class="recipeContentTable" style="width:100%;">
								<tr>
									<td style="width:100px;">
										<b>Title:</b>
									</td>
									<td>
										${recipe.name}
									</td>
								</tr>
								<tr>
									<td colspan="2">
										<br/>
									</td>
								</tr>
								<tr>
									<td style="width:100px;" valign="top">
										<b>Description:</b>
									</td>
									<td>
										${recipe.description}
									</td>
								</tr>
							</table>
						</div>
					</div>
					<br/>
					<div class="openAccordion">
						<h3 style="text-align: center;"><a href="#">Ingredients</a></h3>
						<div>
							<table>
								<g:each in="${recipeContents}">
									<tr>
										<td style="padding-right: 10px;">
											${it.ingredient.name}
										</td>
										<td>
											${it.quantity}
										</td>
										<td>
											${it.uom}
										</td>
									</tr>
								</g:each>
							</table>
						</div>
					</div>
					<br/>
					<div class="openAccordion">
						<h3 style="text-align: center;"><a href="#">Directions</a></h3>
						<div >
							<table>
								<tr>
									<td>
									</td>
									<td style="text-decoration:underline;">
										<b>Instruction(s)</b>
									</td>
									<td style="text-decoration:underline;">
										<b>Ingredient(s)</b>
									</td>
								</tr>
								<tr>
									<td>
										<br/>
									</td>
								</tr>
								<g:each in="${recipeSteps}">
									<tr>
										<td>
											<b>Step ${it.step}:</b>
										</td>
										<td style="width: 250px;">
											${it.instruction}
										</td>
										<td style="text-align: right;">
											${it.ingredient?.name}
										</td>
										<g:if test="${it.ingredient}">
											<td style="padding-left: 15px; text-align: right;">
												${it.quantity}
											</td>
											<td>
												${it.uom}
											</td>
										</g:if>
									</tr>
									<tr>
										<td>
											<br/>
										</td>
									</tr>
								</g:each>
							</table>
						</div>
					</div>
				</div>
				<g:form style="margin: 10px;">
					<g:actionSubmit value="Back" class="button" controller="Recipe" action="recipeList"/>
				</g:form>
			</div>
		</div>
	</body>
</html>