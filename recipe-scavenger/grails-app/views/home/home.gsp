<%@ page import="com.rec.home.HomeController" %>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'news.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css/jquery-te', file: 'jquery-te-Style.css')}" type="text/css">
		<title>Recipe Scavenger</title>
	</head>
	<body>
		<g:selectLeftSideBar/>
		<div id="content">
			<g:errorDisplay/>
			<g:selectHome/>
		</div>
	</body>
</html>