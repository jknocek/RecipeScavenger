<%@ page import="com.rec.user.UserController" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
		<title>User Login</title>
    </head>
	<body>
		<div class="body">
			<table style="margin:0 auto;">
				<tr>
					<td>
						<g:if test="${errorMessage?.size() > 0}">
							<div class="error-message">
								<h4>${errorMessage}</h4>
							</div>
							<br/>
						</g:if>
					</td>
				</tr>
			</table>
			<div id="create-account">
				<g:selectAccountSettingsForm/>
			</div>
		</div>
	</body>
</html>