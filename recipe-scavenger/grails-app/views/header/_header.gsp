<%@ page import="com.rec.header.HeaderController" %>
<div id="header-divider" style="top:0px;"></div>
<div id="header-background" style="top:5px;">
	<div id="header-image">
		<img style="top: 5px;" src="${resource(dir: 'images', file: 'icon_small.png')}"/>
		<img src="${resource(dir: 'images', file: 'title_50x125.png')}"/>
	</div>
	<g:if test="${!session.user}">
		<div id="header-login">
			<table>
				<tr>
					<td>
						<g:link controller="user" action="login" class="header-highlight"
								style="color: #fff; font-size: .8em; padding: 3px 3px 3px 3px;">Sign In</g:link>
					</td>
					<td>
						<g:link controller="user" action="createAccount" class="header-highlight"
								style="color: #fff; font-size: .7em; padding: 3px 3px 3px 3px;">Create an Account</g:link>
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
							style="font-size: .7em; color: #fff; padding: 3px 3px 3px 3px;" 
							class="header-highlight"><b>Account Settings</b></g:link>
					</td>
					<td>
						<g:link controller="header" action="logout" 
								style="font-size: .7em; color: #fff; padding: 3px 3px 3px 3px;" 
								class="header-highlight">Sign Out</g:link>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<h5 style="font-size: .7em; color: #fff; padding-left: 3px;
							">Logged in: ${session.user.username}</h5>
					</td>
				</tr>
			</table>
		</div>
	</g:if>
</div>
<div id="header-divider" style="top: 55px;"></div>