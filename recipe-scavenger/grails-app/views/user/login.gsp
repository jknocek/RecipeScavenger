<%@ page import="com.rec.user.UserController" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta name="layout" content="main" />
		<title>User Login</title>
    </head>
	<body>
		<div class="body">
			<g:form action="doLogin" method="post">
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
				<div id="login-box">
					<div class="login-top">
						<p style="color: #fff; margin-left: 10px;"> Login:</p>
					</div>
					<br/>
					<br/>
					<div class="userForm">
			            <table>
							<tr class='prop'>
								<td valign='top' style='text-align:left;' width='20%'>
									<label for='email'>Email:</label>
								</td>
								<td valign='top' style='text-align:left;' width='80%'>
									<input id="email" type='text' name='email' value='${user?.email}' />
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
								<g:actionSubmit value="Login" class="login-button" action="doLogin"/>
							</div>
						</div>
					</div>
				</div>
			</g:form>
		</div>
	</body>
</html>