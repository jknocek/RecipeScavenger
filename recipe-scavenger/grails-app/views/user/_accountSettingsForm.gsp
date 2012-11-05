<div>
	<g:form controller="user" action="doCreateAccount">
		<table>
			<tr>
				<td>
					<h4>Username</h4>
				</td>
				<td>
					<g:textField name="username" id="username" value="${session?.user?.username }"/>
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
					<input 	id="confPassword" type='password' name='confPassword'/>
				</td>
			</tr>
			<tr>
				<td>
					<g:actionSubmit value="Update Account" style="width: 100px;"
									class="button" action="doUpdateAccount"/>
				</td>
			</tr>
		</table>
	</g:form>
</div>