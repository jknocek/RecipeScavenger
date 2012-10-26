<div>
	<g:form controller="user" action="doCreateAccount">
		<div class="create-account-values">
			<br/>
			<table>
				<tr>
					<td colspan="2">
						<h3 style="text-align:center;">Account Information</h3>
						<br/>
						<br/>
					</td>
				</tr>
				<tr>
					<td>
						<h4>Username</h4>
					</td>
					<td>
						<g:textField name="newUsername" id="newUsername"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<br/>
					</td>
				</tr>
				<tr>
					<td>
						<h4>Email</h4>
					</td>
					<td>
						<g:textField name="newEmail" id="newEmail"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<br/>
						<hr/>
						<br/>
					</td>
				</tr>
				<tr>
					<td>
						<h4>Password</h4>
					</td>
					<td>
						<input 	id="newPassword" type='password' name='newPassword'/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<br/>
					</td>
				</tr>
				<tr>
					<td>
						<h4 style="width: 15em;">Confirm Password</h4>
					</td>
					<td>
						<input 	id="confPassword" type='password' name='confPassword'/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<br/>
					</td>
				</tr>
				<tr>
					<td>
						<g:actionSubmit value="Create Account" class="button" action="doCreateAccount"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="create-account-info">
			<br/>
			<table>
				<tr>
					<td>
						<br/><br/><br/>
						<h3 style="text-align: center;"><b>Welcome</b> to Recipe Scavenger!</h3>
					</td>
				</tr>
				<tr>
					<td style="text-align: center;">
						<br/>
						<b>Recipe Scavenger</b> is a great way to find recipes you can make, with what you have. Enter ingredients and quantities that you own 
						into your Recipe Scavenger refrigerator so we can keep tack of what you have.
					</td>
				</tr>
				<tr>
					<td style="text-align: center;">
						<br/>
						Feel free to view any of our recipes, and even <b>add some of your own</b>! 
					</td>
				</tr>
			</table>
			
		</div>
		<div class="create-account-divider"></div>
	</g:form>
</div>