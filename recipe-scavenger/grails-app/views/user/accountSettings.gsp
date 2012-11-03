<%@ page import="com.rec.user.UserController" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
		<title>User Login</title>
    </head>
	<body>
		<div class="content">
			<div id="tabs">
				<ul>
					<li><h3><a href="#tabs-1">Account Settings</a></h3></li>
				</ul>
				<div id="tabs-1">
					<g:selectAccountSettingsForm/>
				</div>
			</div>
		</div>
	</body>
</html>