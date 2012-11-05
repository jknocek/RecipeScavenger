<div id="ingredients-dialog">
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