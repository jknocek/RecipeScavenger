<%@ page import="com.rec.recipe.RecipeController" %>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'news.css')}" type="text/css">
		<title>Recipe Scavenger</title>
	</head>
	<body>
		<g:selectLeftSideBar/>
		<div class="content">
			<g:errorDisplay/>
			<div id="tabs">
				<ul>
					<li><h3><a href="#tabs-1">All Time</a></h3></li>
					<li><h3><a href="#tabs-2">This Month</a></h3></li>
					<li><h3><a href="#tabs-3">This Week</a></h3></li>
				</ul>
				<div id="tabs-1">
					<h1 style="margin-left: 10px;">Top 5 of All Time</h1>
					<hr/>
					<g:selectRatedRecipes/>
				</div>
				<div id="tabs-2">
					<h1 style="margin-left: 10px;">Top 5 This Month</h1>
					<hr/>
					<g:selectRatedRecipesMonth/>
				</div>
				<div id="tabs-3">
					<h1 style="margin-left: 10px;">Top 5 This Week</h1>
					<hr/>
					<g:selectRatedRecipesWeek/>
				</div>
			</div>
		</div>
	</body>
</html>