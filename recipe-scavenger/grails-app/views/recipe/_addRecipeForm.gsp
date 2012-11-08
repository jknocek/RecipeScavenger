<%@ page import="com.rec.recipe.RecipeController" %>
<div>
	<table class="recipeContentTable" style="width:100%;">
		<tr>
			<td style="width:100px;">
				<b>Title:</b>
			</td>
			<td>
				<g:form controller="recipe" action="updateTitle">
					<g:textField id="title" name='title' value="${newRecipeTitle}" onchange="submit()"/>
				</g:form>
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
				<g:form controller="recipe" action="updateDescription">
					<g:textArea name="description" value="${newRecipeDescription}" style="height: 100px; width: 100%; resize: none; margin: 0 auto 0 auto;" maxlength="512" onchange="submit()"></g:textArea>
				</g:form>
			</td>
		</tr>
	</table>
	<br/>
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
							${it.ingredient.name}
						</td>
						<td>
							<g:form controller="recipe" action="updateIngredientQuantities">
								<g:textField name="${it.ingredient.name}" value="${it.quantity}" style="text-align: right; width: 40px; margin-left: 40%;" onchange="submit()" />
							</g:form>
						</td>
						<td>
							Drop down to unit of measure...
						</td>
						<td>
							<g:link controller="recipe" action="deleteRecipeContent" params="[recipieContentId: it.ingredient?.id]">[delete]</g:link>
						</td>
					</tr>
				</g:each>
			</tbody>
		</table>
	</div>
	<div class="button" id="ingredients-dialog-button" style="cursor:default; width:80px; font-size:1em;">Add Ingredient</div>
	<g:selectIngredient/>
	<br/>
	<table>
		<g:each in="${newRecipeSteps}">
			<tr>
				<td>
					<g:textArea name="instruction" style="height: 50px; width: 600px; resize: none; margin: 0 auto 0 auto;" value="${it.instruction}"></g:textArea>
				</td>
				<td>
					${it.ingredient?.name}
				</td>
				<td>
					<g:form controller="recipe" action="updateStepQuantities">
						<g:textField name="${it.step}" value="${it.quantity}" style="text-align: right; width: 40px;" onchange="submit()" />
					</g:form>
				</td>
				<td>
					unit of measure specified above...
				</td>
				<td>
					<g:link controller="recipe" action="deleteRecipeStep" params="[recipieStep: it.step]">[delete]</g:link>
				</td>
			</tr>
		</g:each>
	</table>
	<g:form controller="recipe">
		<g:actionSubmit value="Add Step" class="button" controller="Recipe" action="addStep"/>
	</g:form>
	<br/>
	<g:form controller="recipe">
		<g:actionSubmit value="Create" class="button" controller="Recipe" action="doAddRecipe"/>
		<g:actionSubmit value="Back" class="button" controller="Recipe" action="recipeList"/>
	</g:form>
</div>