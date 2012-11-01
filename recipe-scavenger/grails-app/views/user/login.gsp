<%@ page import="com.rec.user.UserController" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
		<title>User Login</title>
    </head>
	<body>
		<g:form action="doLogin" method="post" >
			<div id="login-box">
				<div class="login-top">
				</div>
				<br/>
				<br/>
				<div class="userForm">
		            <table>
						<tr class='prop'>
							<td valign='top' style='text-align:left;' width='20%'>
								<label for='email'>Username or Email:</label>
							</td>
							<td valign='top' style='text-align:left;' width='80%'>
								<input id="emailOrUsername" type='text' name='emailOrUsername' value='${user?.email}' />
							</td>
						</tr>
						<tr class='prop'>
							<td valign='top' style='text-align:left;' width='20%'>
								<label for='password'>Password:</label>
							</td>
							<td valign='top' style='text-align:left;' width='80%'>
								<input 	id="password" type='password' name='password'
		       							value='${user?.password}' />
							</td>
						</tr>
					</table>
				</div>
				<div class="login-bottom">
					<div class="login-button-container">
						<div class="login-button">
							<g:actionSubmit value="Sign In" class="login-button" action="doLogin"/>
						</div>
					</div>
				</div>
			</div>
		</g:form>
	</body>
</html>