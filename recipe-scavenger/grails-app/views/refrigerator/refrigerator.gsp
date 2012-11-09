<%@ page import="com.rec.refrigerator.RefrigeratorController" %>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'news.css')}" type="text/css" />
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'contact.css')}" type="text/css" />
		<link rel="stylesheet" href="${resource(dir: 'css/jquery-te', file: 'jquery-te-Style.css')}" type="text/css" />
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'ingredient.css')}" type="text/css" />
		<title>Recipe Scavenger - Refrigerator Contents</title>
	</head>
	<body>
		<div class="content">
			<div id="tabs">
				<ul>
					<li><h3><a href="#tabs-1">Refrigerator</a></h3></li>
				</ul>
				<div id="tabs-1">
					<div><g:link controller="ingredient" action="index">Add Item</g:link></div>
					<table class="ingredient-list">
						<thread>
							<tr>
								<th>Name</th>
								<th>Amount</th>
								<th>	
							</tr>
						</thread>
						<tbody>
							<g:each in="${ session.refrigeratorContent }" var="item">
								<tr>
									<td>${ item?.ingredient.name }</td>
									<td>${ item?.ingredientAmount }</td>
									<td>
										<g:link class="siberbar-highlight" controller="refrigerator" action="editIngredient" params="[id: item?.id]">[Change Amount]</g:link>
										<g:link class="siberbar-highlight" controller="refrigerator" action="doRemoveIngredient" params="[id: item?.id]">[Remove]</g:link> 
									</td>
								</tr>
							</g:each>
						</tbody>
					</table>
					<div><g:link controller="ingredient" action="index">Add Item</g:link></div>
				</div>
			</div>
		</div>
	</body>
</html>