<%@ page import="com.rec.user.UserController" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
		<title>Create Account</title>
    </head>
	<body>
		<g:selectLeftSideBar/>
		<div id="content">
			<g:errorDisplay/>
			<div id="content-form">
				<g:selectCreateAccountForm/>
			</div>
		</div>
	</body>
</html>