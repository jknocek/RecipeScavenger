<div id="left-side-bar" class="stickbox">
	<div id="left-side-bar-content">
		<div class="openAccordion" style="width: 180px;">
			<h3 style="text-align: center;"><a href="#">General</a></h3>
			<div style="width: 130px; margin-left: 0;">
				<table>
					<tr>
						<td>
							<g:link controller="home" action="home" class="sidebar-highlight">Home</g:link>
						</td>
					</tr>
					<tr>
						<td>
							<g:link class="sidebar-highlight">Find Recipes</g:link>
						</td>
					</tr>
					<tr>
						<td>
							<g:link class="sidebar-highlight" controller="recipe" action="topRecipes">Top Recipes</g:link>
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
				<h3 style="text-align: center;"><a href="#">Account</a></h3>
				<div style="width: 130px; margin-left: 0;">
					<table>
						<tr>
							<td style="padding-left:10px;">
								<table>
									<tr>
										<td>
											My Refrigerator
										</td>
									</tr>
									<tr>
										<td>
											My Recipes
										</td>
									</tr>
									<tr>
										<td>
											Add Ingredients
										</td>
									</tr>
									<tr>
										<td>
											Add Recipes 
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</g:if>
		<g:if test="${session.user?.admin == true}">
			<div class="openAccordion" style="width: 180px;">
				<h3 style="text-align: center;"><a href="#">Admin</a></h3>
				<div style="width: 130px; margin-left: 0;">
					<table>
						<tr>
							<td style="padding-left:10px;">
								<table>
									<tr>
										<td>
											<g:link controller="news" action="addNews" class="sidebar-highlight">Add News Article</g:link>
										</td>
									</tr>
									<tr>
										<td>
											<g:link controller="ingredient" action="" class="sidebar-highlight">Manage Ingredients</g:link>
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</div>
			</div>
		</g:if>
		<g:if test="${session.user}">
			<div class="openAccordion" style="width: 180px;">
				<h3 style="text-align: center;"><a href="#">Refrigerator Content</a></h3>
				<div style="width: 130px; margin-left: 0;">
					<table>
						
					</table>
				</div>
			</div>
		</g:if>
		<g:else>
			<div class="accordion" style="width: 180px;">
				<h3 style="text-align: center;"><a href="#">Refrigerator Content</a></h3>
				<div style="width: 130px; margin-left: 0;">
					<table>
						
					</table>
				</div>
			</div>
		</g:else>
	</div>
</div>