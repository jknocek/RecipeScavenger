<%@ page import="com.rec.home.HomeController" %>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'news.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css/jquery-te', file: 'jquery-te-Style.css')}" type="text/css">
		<title>Recipe Scavenger</title>
	</head>
	<body>
		<g:selectLeftSideBar/>
		<div id="content">
			<g:errorDisplay/>
			<g:form controller="news" action="doAddNews">
				<div id="" style="margin-bottom: 100px;">
					<h1 style="text-align: center;">Add News Article</h1>
					<hr/>
					<table style="margin: 0 auto;">
						<tr>
							<td>
								<h2 style="text-align: right; padding-bottom: 30px;">Title: </h2>
							</td>
							<td>
								<g:textField name="newArticleTitle" id="newArticleTitle"/>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<g:textArea name="newArticleText" id="newArticleText" class="editor"></g:textArea>
							</td>
						</tr>
						<tr>
							<td>
								<g:actionSubmit value="Submit" style="width: 100px;"
												class="news-submit-button" action="doAddNews"/>
							</td>
						</tr>
					</table>		
				</div>
			</g:form>
		</div>
	</body>
</html>