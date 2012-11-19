<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'recipe.css')}" type="text/css">
		<title>Recipe Scavenger - Add New Recipe</title>
		<script type="text/javascript">
			function setCookie(name, value, expires, path, domain, secure){
			    if (!expires){expires = new Date()}
			    document.cookie = name + "=" + escape(value) + 
			    ((expires == null) ? "" : "; expires=" + expires.toGMTString()) +
			    ((path == null) ? "" : "; path=" + path) +
			    ((domain == null) ? "" : "; domain=" + domain) +
			    ((secure == null) ? "" : "; secure")
			}
			
			function getCookie(name) {
			    var arg = name + "="
			    var alen = arg.length
			    var clen = document.cookie.length
			    var i = 0
			    while (i < clen) {
			        var j = i + alen
			        if (document.cookie.substring(i, j) == arg){
			            return getCookieVal(j)
			        }
			        i = document.cookie.indexOf(" ", i) + 1
			        if (i == 0) break
			    }
			    return null
			}
			
			function getCookieVal(offset){
			    var endstr = document.cookie.indexOf (";", offset)
			    if (endstr == -1)
			    endstr = document.cookie.length
			    return unescape(document.cookie.substring(offset, endstr))
			}
			
			function deleteCookie(name,path,domain){
			    document.cookie = name + "=" +
			    ((path == null) ? "" : "; path=" + path) +
			    ((domain == null) ? "" : "; domain=" + domain) +
			    "; expires=Thu, 01-Jan-00 00:00:01 GMT"
			}
			
			function saveScroll(){ // added function
			    var expdate = new Date ()
			    expdate.setTime (expdate.getTime() + (expdays*24*60*60*1000)); // expiry date
			
			    var x = (document.pageXOffset?document.pageXOffset:document.body.scrollLeft)
			    var y = (document.pageYOffset?document.pageYOffset:document.body.scrollTop)
			    Data=x + "_" + y
			    setCookie(cookieName,Data,expdate)
			}
			
			function loadScroll(){ // added function
			    inf=getCookie(cookieName)
			    if(!inf){return}
			    var ar = inf.split("_")
			    if(ar.length == 2){
			        window.scrollTo(parseInt(ar[0]), parseInt(ar[1]))
			    }
			}
		</script>
	</head>
	<body onload="loadScroll()" onunload="saveScroll()">
		<div class="content">
			<div id="tabs">
				<ul>
					<li><h3><a href="#tabs-1">Add Recipe</a></h3></li>
				</ul>
				<div id="tabs-1">
					<g:addRecipe/>
				</div>
			</div>
		</div>
	</body>
</html>