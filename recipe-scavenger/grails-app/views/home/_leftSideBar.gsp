<div id="left-side-bar" class="stickbox">
	<div id="left-side-bar-content">
		<div class="openAccordion" style="width: 180px;">
			<h3 class="sidebar-segment-title"><a href="#">General</a></h3>
			<div class="sidebar-segment-content">
				<table class="sidebar-links">
					<tr>
						<td>
							<g:link controller="home" action="home" class="sidebar-highlight">Home</g:link>
						</td>
					</tr>
					<tr>
						<td>
							<g:link controller="recipe" action="recipeList" class="sidebar-highlight">Recipes</g:link>
						</td>
					</tr>
					<tr>
						<td>
							Quick Search
						</td>
					</tr>
				</table>
			</div>
		</div>
		<g:if test="${session.user}">
			<div class="openAccordion" style="width: 180px;">
				<h3 class="sidebar-segment-title"><a href="#">Account</a></h3>
				<div class="sidebar-segment-content">
					<table class="sidebar-links">
						<tr>
							<td>
								<g:link class="sidebar-highlight" controller="refrigerator" 
										action="refrigerator" class="sidebar-highlight">My Refrigerator</g:link>
							</td>
						</tr>
						<tr>
							<td>
								<g:link controller="ingredient" action="index" class="sidebar-highlight">View Ingredients</g:link>
							</td>
						</tr>
						<tr>
							<td>
								<g:link controller="ingredient" action="add" class="sidebar-highlight">Add Ingredients</g:link>
							</td>
						</tr>
						<tr>
							<td>
								<g:link controller="recipe" action="toAddRecipe" class="sidebar-highlight">Add Recipes</g:link>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</g:if>
		<g:if test="${session.user?.admin == true}">
			<div class="openAccordion" style="width: 180px;">
				<h3 class="sidebar-segment-title"><a href="#">Admin</a></h3>
				<div class="sidebar-segment-content">
					<table class="sidebar-links">
						<tr>
							<td>
								<g:link controller="news" action="addNews" class="sidebar-highlight">Add News Article</g:link>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</g:if>
		<g:if test="${session.user}">
			<div class="openAccordion" style="width: 180px;">
				<h3 style="text-align: center;"><a href="#">Refrigerator Content</a></h3>
				<div class="sidebar-segment-content">
					<table style="width: 100%;">
						<thead>
							<tr>
								<th>
									<b>Ingredient</b>
								</th>
								<th colspan=2 style="text-align: right;">
									<b>Quantity</b>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr><td colspan="3"><hr/></td></tr>
							<g:if test="${session.refrigeratorContent?.size() > 5}">
								<g:each in="${session.refrigeratorContent.subList(0, 5)}">
									<tr>
										<td>
											${it.ingredient.name}
										</td>
										<td style="text-align: right;">
											<g:textField name="amount" style="text-align: right; width: 40px; text-overflow: ellipsis;" 
												value="${it.ingredientAmount}" disabled="disabled"/>
										</td>
										<td>
											${it.uomDisplay}
										</td>
									</tr>
								</g:each>
							</g:if>
							<g:else>
								<g:each in="${session.refrigeratorContent}">
									<tr>
										<td>
											${it.ingredient.name}
										</td>
										<td style="text-align: right;">
											<g:textField name="amount" style="text-align: right; width: 40px; text-overflow: ellipsis;" 
												value="${it.ingredientAmount}" disabled="disabled"/>
										</td>
										<td>
											${it.uomDisplay}
										</td>
									</tr>
								</g:each>
							</g:else>
						</tbody>
					</table>
					<table style="width: 100%;">
						<g:if test="${session.refrigeratorContent?.size() > 5}">
							<tr>
								<td style="text-align: right">
									<g:link controller="refrigerator" action="refrigerator">more...</g:link>
								</td>
							</tr>
						</g:if>
					</table>
				</div>
			</div>
		</g:if>
	</div>
</div>