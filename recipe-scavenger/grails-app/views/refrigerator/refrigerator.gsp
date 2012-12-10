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
									<g:actionSubmit value="Search" controller="refrigerator" action="refrigerator"/>
								</td>
							</tr>
						</table>
					</g:form>
					<table class="ingredient-list" cellpadding=2 >
						<thead>
							<tr>
								<th><b>Ingredients</b></th>
								<th style="text-align: right;"><b>Quantity</b></th>
								<th></th>
								<th></th>
							</tr>
							<tr>
								<td colspan=4>
									<hr/>
								</td>
							</tr>
						</thead>
						<tbody>
							<g:each in="${ session.refrigeratorContent }" var="item">
								<tr class="hightlight-row">
									<td>${ item?.ingredient.name }</td>
									<td style="text-align: right;"><g:textField name="amount" value="${ item?.ingredientAmount }" 
										style="text-align: right; width: 80px; text-overflow: ellipsis;" disabled="disabled"/>
									</td>
									<td>${ item?.uomName }</td>
									<td>
										<g:link class="siberbar-highlight" controller="refrigerator" action="editIngredient" params="[id: item?.id]">[Edit]</g:link>
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