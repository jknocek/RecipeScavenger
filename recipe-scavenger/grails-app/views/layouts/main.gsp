<!doctype html>
<html lang="en">
	<head>
		<title>Recipe Scavenger</title>
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'icon_small.png')}" type="image/x-icon">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'header.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'login.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'errors.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'left-side-bar.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'image-slider.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'create-account.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'jquery-ui-1.8.24.custom.css')}" type="text/css">
		<g:layoutHead/>
        <r:layoutResources />
	</head>
	<body>
		<g:javascript src="jquery/jquery-1.7.1.min.js" />
		<g:javascript src="jquery/ui/jquery-1.8.2.min.js" />
		<g:javascript src="jquery/ui/jquery-ui-1.8.24.custom.min.js" />
		<g:javascript src="jquery/ui/jquery.ui.dialog.min.js" />
		<g:javascript src="jquery/ui/jquery.ui.core.min.js" />
		<g:javascript src="jquery/ui/jquery.ui.widget.min.js" />
		<g:javascript src="jquery/ui/jquery.ui.button.min.js" />
		<g:javascript src="jquery/jshowoff/jquery.jshowoff.min.js" />
		<g:javascript src="jquery/jquery-te/jquery-te-1.0.5.min.js" />
		<g:javascript src="application.js" />
		<g:javascript src="mask.js" />
		<g:selectHeader/>
		<div id="center">
			<div style="margin-top: 60px; height:100%;">
				<g:layoutBody/>
			</div>
		</div>
		<div id="footer-divider"></div>
		<div id="bottom-footer"></div>
        <r:layoutResources />
	</body>
</html>