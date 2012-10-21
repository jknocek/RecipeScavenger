<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'news.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'contact.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css/jquery-te', file: 'jquery-te-Style.css')}" type="text/css">
		<title>Recipe Scavenger - Ingredient Database</title>
	</head>
	<body>
		<g:selectLeftSideBar/>
		<div id="content">
			<g:errorDisplay/>
			<span>Ingredients:</span>
			<ul>
			<g:each in="${ ingredients }" var="ingredient">
				<li>
					<span>${ ingredient?.name }</span>
					<span>${ ingredient?.baseOumName })</span>
				</li>
			</g:each> 
			</ul>
		</div>
	</body>
</html>