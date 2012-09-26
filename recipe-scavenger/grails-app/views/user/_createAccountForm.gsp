<div>
	<g:form action="doCreateAccount" method="post">
		<table>
			<tr>
				<td>
					<h4>Username</h4>
				</td>
				<td>
					<g:textField name="username" id="username"/>
				</td>
			</tr>
			<tr>
				<td>
					<h4>Email</h4>
				</td>
				<td>
					<g:textField name="email" id="email"/>
				</td>
			</tr>
			<tr>
				<td>
					<h4>Password</h4>
				</td>
				<td>
					<input 	id="password" type='password' name='password'/>
				</td>
			</tr>
			<tr>
				<td>
					<g:actionSubmit value="Create Account" style="width: 100px;"
									class="login-button" action="doCreateAccount"/>
				</td>
			</tr>
		</table>
	</g:form>
</div>