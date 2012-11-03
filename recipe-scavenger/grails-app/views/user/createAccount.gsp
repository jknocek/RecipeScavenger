<%@ page import="com.rec.user.UserController" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
		<title>Create Account</title>
    </head>
	<body>
		<div class="content">
			<div id="tabs">
				<ul>
					<li><h3><a href="#tabs-1">Create Account</a></h3></li>
				</ul>
				<div id="tabs-1">
					<g:selectCreateAccountForm/>
				</div>
			</div>
		</div>
	</body>
</html>