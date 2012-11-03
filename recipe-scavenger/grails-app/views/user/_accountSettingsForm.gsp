<div>
	<g:form action="doCreateAccount" method="post">
		<div class="create-account-values">
			<table>
				<tr>
					<td>
						<h4>Email</h4>
					</td>
					<td>
						<g:textField name="email" id="email" value="${session?.user?.username }"/>
					</td>
				</tr>
				<tr>
					<td>
						<h4>Email</h4>
					</td>
					<td>
						<g:textField name="email" id="email" value="${session?.user?.email }"/>
					</td>
				</tr>
				<tr>
					<td>
						<h4>New Password</h4>
					</td>
					<td>
						<input 	id="password" type='password' name='password'/>
					</td>
				</tr>
				<tr>
					<td>
						<h4>Confirm New Password</h4>
					</td>
					<td>
						<input 	id="password" type='password' name='password'/>
					</td>
				</tr>
				<tr>
					<td>
						<g:actionSubmit value="Update Account" style="width: 100px;"
										class="login-button" action="doCreateAccount"/>
					</td>
				</tr>
			</table>
		</div>
	</g:form>
</div>