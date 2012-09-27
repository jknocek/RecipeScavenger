<div>
	<g:form action="doCreateAccount" method="post">
		<div class="create-account-values">
			<br/>
			<table cellpadding="5">
				<tr>
					<td colspan="2" style="text-align:center;">
						<h3>Account Information</h3>
						<br/>
					</td>
				</tr>
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
					<td colspan="2">
						<hr/>
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
						<h4>Confirm Password</h4>
					</td>
					<td>
						<input 	id="confPassword" type='password' name='confPassword'/>
					</td>
				</tr>
				<tr>
					<td>
						<g:actionSubmit value="Create Account" style="width: 100px;"
										class="login-button" action="doCreateAccount"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="create-account-info">
			<br/>
			<table style="text-align: center;">
				<tr>
					<td>
						<h3><b>Welcome</b> to Recipe Scavenger!</h3>
					</td>
				</tr>
				<tr>
					<td>
						<br/>
						<b>Recipe Scavenger</b> is a great way to find recipes you can make, with what you have. Enter ingredients and quantities that you own 
						into your Recipe Scavenger refrigerator so we can keep tack of what you have.
					</td>
				</tr>
				<tr>
					<td>
						<br/>
						Feel free to view any of our recipes, and even <b>add some of your own</b>! 
					</td>
				</tr>
			</table>
			
		</div>
		<div class="create-account-divider"></div>
	</g:form>
</div>