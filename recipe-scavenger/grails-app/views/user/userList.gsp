<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>User List</title>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'recipe.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'ingredient.css')}" type="text/css" />
	</head>
	<body>
		<div class="content">
			<div id="tabs">
				<ul>
					<li><h3><a href="#tabs-1">User Accounts</a></h3></li>
				</ul>
				<div id="tabs-1">
					<g:link controller="recipe" action="toAddRecipe">Add User</g:link>
					<table class="ingredient-list" cellpadding=2 >
						<thead>
							<tr>
								<th><b>Name</b></th>
								<th><b>Email</b></th>
								<th><b>Admin</b></th>
							</tr>
						</thead>
						<tbody>
						<g:each in="${ userList }" var="user">
							<g:if test="${user.active != false}">
								<tr  class="hightlight-row">
									<td style="text-align: center;">${ user?.username }</td>
									<td style="text-align: center;">${user?.email}</td>
									<td style="text-align: center;">${user?.admin}</td>
									<td>
										<g:link controller="user" action="editAccount" params="[id: user?.id]">[edit]</g:link>
										<g:link controller="user" action="doDeleteUser" params="[id: user?.id]">[delete]</g:link>
									</td>
								</tr>
							</g:if>
						</g:each>
						</tbody>
					</table>
					<g:link controller="recipe" action="toAddRecipe">Add User</g:link>
				</div>
			</div>
		</div>
	</body>
</html>