<%@ page import="com.rec.user.UserController" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'create-account.css')}" type="text/css">
		<title>Create Account</title>
    </head>
	<body>
		<div class="body">
			<g:selectLeftSideBar/>
			<table style="margin:0 auto;">
				<tr>
					<td>
						<g:if test="${errorMessage?.size() > 0}">
							<div class="error-message" style="margin-left: 250px;">
								<h4>${errorMessage}</h4>
							</div>
							<br/>
						</g:if>
					</td>
				</tr>
			</table>
			<div id="create-account">
				<g:selectCreateAccountForm/>
			</div>
		</div>
	</body>
</html>