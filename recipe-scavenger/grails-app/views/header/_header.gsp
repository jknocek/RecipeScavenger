<%@ page import="com.rec.header.HeaderController" %>
<div id="header-background-container" style="top:0px;">
	<div id="header-content">
		<div id="header-title">
			<h1>Recipe Scavenger</h1>
		</div>
		<g:if test="${!session.user}">
			<div id="header-info">
				<table style="margin-top: 25px;">
					<tr>
						<td style="width: 45px;">
							<!--<g:link controller="user" action="login" class="header-highlight" id="login-dialog-button"
									style="padding: 3px 3px 3px 3px;">Sign In</g:link>-->
							<a href="" id="login-dialog-button" class="header-highlight" style="padding: 3px 3px 3px 3px;" >Sign In</a>
							<g:selectLogin/>
						</td>
						<td style="width: 115px;">
							<g:link controller="user" action="createAccount" class="header-highlight"
									style="padding: 3px 3px 3px 3px;">Create an Account</g:link>
						</td>
						<td>
							<a href="" id="terms-dialog-button" class="header-highlight" style="padding: 3px 3px 3px 3px;">Terms and Conditions</a>
							<g:selectTerms/>
						</td>
					</tr>
				</table>
			</div>
		</g:if>
		<g:if test="${session.user}">
			<div id="header-info">
				<table style="margin-top: 15px;">
					<tr>
						<td>
							<g:link controller="user" action="accountSettings" 
								style="padding: 3px 3px 3px 3px;" 
								class="header-highlight">Account Settings</g:link>
						</td>
						<td>
							<g:link controller="header" action="logout" 
									style="padding: 3px 3px 3px 3px;" 
									class="header-highlight">Sign Out</g:link>
						</td>
						<td>
							<a href="" id="terms-dialog-button" class="header-highlight" style="padding: 3px 3px 3px 3px;">Terms and Conditions</a>
							<g:selectTerms/>
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<p style="font-size: 1em; color: #fff; padding: 3px 3px 3px 3px;">Logged in: ${session.user.username}</p>
						</td>
					</tr>
				</table>
			</div>
		</g:if>
	</div>
</div>