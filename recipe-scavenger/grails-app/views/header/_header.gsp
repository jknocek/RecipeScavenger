<%@ page import="com.rec.header.HeaderController" %>
<div id="header-divider" style="top:0px;"></div>
<div id="header-background" style="top:5px;">
	<div id="header-image">
		<img style="top: 5px;" src="${resource(dir: 'images', file: 'icon_small.png')}"/>
		<img src="${resource(dir: 'images', file: 'title_50x125.png')}"/>
	</div>
	<g:if test="${!session.user}">
		<div id="header-login">
			<g:link controller="user" action="login" style="color: #fff;">Log in</g:link>
		</div>
	</g:if>
	<g:if test="${session.user}">
		<div id="header-user-info">
			<table>
				<tr>
					<td>
						<h5 style="font-size: .7em;">Logged in: ${session.user.email}</h5>
					</td>
					<td>
						<a href="" style="font-size: .7em; color: #fff;"><b>Account Settings</b></a>
					</td>
				</tr>
				<tr>
					<td>
						<g:link controller="header" action="logout" style="font-size: .7em; color: #fff;">log out</g:link>
					</td>
				</tr>
			</table>
		</div>
	</g:if>
</div>
<div id="header-divider" style="top: 55px;"></div>