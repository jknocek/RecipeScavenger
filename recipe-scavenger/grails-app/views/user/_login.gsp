<div id="login-dialog" >
	<g:form controller="user">
		<div class="userForm">
			<br/>
			<table style="text-align:left; width:300px;">
				<tr>
					<td>
						<label for='email'>Username or Email:</label>
					</td>
					<td>
						<input id="emailOrUsername" type='text' name='emailOrUsername'/>
					</td>
				</tr>
				<tr>
					<td>
						<br/>
					</td>
				</tr>
				<tr> 
					<td>
						<label for='password'>Password:</label>
					</td>
					<td>
						<input 	id="password" type='password' name='password'/>
					</td>
				</tr>
			</table>
		</div>
		<br/>
		
		<div style="float: right;">
			<g:actionSubmit value="Sign In" class="button" controller="user" action="doLogin"/>
		</div>
	</g:form>
</div>
