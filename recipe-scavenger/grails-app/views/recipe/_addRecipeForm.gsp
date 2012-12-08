<%@ page import="com.rec.uom.UOM" %>
<%@ page import="com.rec.recipe.RecipeController" %>
<div>
	<g:form controller="recipe" action="saveNewRecipeValues">
		<table class="recipeContentTable" style="width:100%;">
			<tr>
				<td style="width:100px;">
					<b>Title:</b>
				</td>
				<td>
					<g:textField id="title" name='title' value="${newRecipeTitle}"/>
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
					<g:textArea name="description" value="${newRecipeDescription}" 
								style="height: 50px; width: 100%; resize: none; margin: 0 auto 0 auto;" 
								maxlength="255">
					</g:textArea>
				</td>
			</tr>
		</table>
		<br/>
		<g:if test="${newRecipeTags}">
			<b>Categories:</b><br/>
			<table style="margin-left: 40px;">
				<g:each in="${newRecipeTags}">
					<tr>
						<td style="width: 100px;">
							${it.name}<br/>
						</td>
						<td>
							<g:link controller="recipe" action="removeTag" params="[removeTagName: it.name]">Delete</g:link>
						</td>
					</tr>
				</g:each>
			</table>
			<br/>
		</g:if>
		<b>New Category:</b> <g:textField name="newTag"/><br/><br/>
		<g:actionSubmit class="button" value="Add Category" action="addTag"/>
		<br/><br/><hr/><br/>
		<g:if test="${newRecipeIngredients}">
			<b>Ingredients:</b>
			<div class="recipeIngredientBox">
				<table id="recipeIngredientTable">
					<thead>
						<tr>
							<th>
								Ingredient
							</th>
							<th>
								Quantity
							</th>
							<th>
								Unit Of Measure
							</th>
							<th>
							</th>
						</tr>
					</thead>
					<tbody>
						<g:each in="${newRecipeIngredients}">
							<tr>
								<td>
									${it.ingredient?.name}
								</td>
								<td>
									<g:textField 	name="${it.ingredient?.name}" value="${it.quantity}" 
													style="text-align: right; width: 80px;"/>
								</td>
								<td>
									<g:if test="${it.ingredient?.baseUomType != 'u'}">
										<g:select 	name="ingredientUom" 
													from="${UOM.getPossibleUomsByName(it.ingredient?.baseUomType)}" 
													value="${it.uom}"
													onChange="submit()" 
													style="width: 100px; text-align:right;"/>
									</g:if>
								</td>
								<td>
									<g:link controller="recipe" 
											action="deleteRecipeContent" 
											params="[recipieContentId: it.ingredient?.id]">[delete]</g:link>
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>
			</div>
		</g:if>
		<g:actionSubmit value="Add Ingredient" class="button" controller="Recipe" action="selectIngredient"/>
		<br/><br/><hr/><br/>
		<table style="width: 100%;">
			<g:each in="${newRecipeSteps}">
				<tr>
					<td>
						<p id="step${it.step}">Step: ${it.step}</p>
					</td>
				</tr>
				<tr  style="width: 100%;">
					<td style="width: 410px;">
						<g:textArea name="step${it.step}Instruction" 
									style="height: 50px; width: 400px; resize: none; margin: 0 auto 0 auto;" 
									value="${it.instruction}"></g:textArea>
					</td>
					<td style="text-align: right;">
						<g:select 	name="step${it.step}PossibleIngredients" from="${newRecipeIngredientNames}" 
									value="${it.ingredient?.name}" noSelection="['':'-No Ingredient-']"
									onChange="submit()" style="width: 120px;"/>
					</td>
					<g:if test="${it.ingredient?.name}">
						<td style="text-align:right;">
							<g:textField 	name="step${it.step}Quantity" 
											value="${it.quantity}" 
											style="text-align: right; width: 80px;" />
						</td>
						<td style="text-align:left;">
							<p>${it.uom}</p>
						</td>
					</g:if>
					<td>
						<g:link controller="recipe" action="deleteRecipeStep" params="[recipieStep: it.step]">[delete]</g:link>
					</td>
				</tr>
			</g:each>
		</table>
		<g:actionSubmit value="Add Step" class="button" controller="Recipe" action="addStep"/>
		<br/><br/><hr/><br/>
		<g:if test="${newRecipe}">
			<g:actionSubmit value="Create" class="button" controller="Recipe" action="doAddRecipe"/>
		</g:if>
		<g:else>
			<g:actionSubmit value="Update" class="button" controller="Recipe" action="doUpdateRecipe"/>
		</g:else>
		<g:actionSubmit value="Back" class="button" controller="Recipe" action="recipeList"/>
	</g:form>
</div>