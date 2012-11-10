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
					<li><h3><a href="#tabs-1">Edit Refrigerator Content</a></h3></li>
				</ul>
				<div id="tabs-1">
					<g:form controller="refrigerator" action="updateAmount">
						<g:hiddenField name="id" value="${id}"/>
						<g:hiddenField name="baseUomType"value="${baseUomType}"/>
						<g:hiddenField name="origUom" value="${currentUom}" />
						<table>
							<tr>
								<td>
									<p><b>Name of Ingredient:</b> ${name}</p>
								</td>
							</tr>
							<tr>
								<td>
									<p><b>Ingredient is typically measured in bulk by:</b> ${uomType}</p>
								</td>
							</tr>
						</table>
						<table>
							<g:if test="${possibleUoms}">
								<tr>
									<td>
										<p><b>Select Unit of Measure:</b></p>
									</td>
									<td>
										<g:select name="uom" from="${possibleUoms}" value="${currentUom}" onChange="submit()"/>
									</td>
								</tr>
							</g:if>
							<tr>
								<td>
									<p><b>How much you have:</b></p>
								</td>
								<td>
									<g:textField style="text-align: right; width: 50px;" name="amount" value="${amount}"/>
								</td>
							</tr>
						</table>
						<br/>
						<g:actionSubmit value="Submit" class="button" controller="refrigerator" action="doEditIngredient"/>
						<g:actionSubmit value="Back" class="button" controller="refrigerator" action="refrigerator"/>
					</g:form>
				</div>
			</div>
		</div>
	</body>
</html>