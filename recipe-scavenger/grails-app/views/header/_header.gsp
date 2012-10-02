<%@ page import="com.rec.header.HeaderController" %>
<div id="header-divider" style="top:0px;"></div>
<div id="header-background-container" style="top:5px;">
	<div id="header-content">
		<div id="header-image">
			<table>
				<tr>
					<td>
						<g:link controller="home" action="home">
							<img style="top: 5px;" src="${resource(dir: 'images', file: 'icon_small.png')}"/>
						</g:link>
					</td>
					<td>
						<g:link controller="home" action="home">
							<img src="${resource(dir: 'images', file: 'title_50x125.png')}"/>
						</g:link>
					</td>
				</tr>
			</table>
		</div>
		<g:if test="${!session.user}">
			<div id="header-login">
				<table>
					<tr>
						<td style="width: 45px;">
							<!--<g:link controller="user" action="login" class="header-highlight" id="login-dialog-button"
									style="padding: 3px 3px 3px 3px;">Sign In</g:link>-->
							<a href="" id="login-dialog-button" class="header-highlight" style="padding: 3px 3px 3px 3px;" >Sign In</a>
							<g:selectLogin/>
						</td>
						<td style="width: 100px;">
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
			<div id="header-user-info">
				<table>
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
<div id="header-divider" style="top: 55px;"></div>