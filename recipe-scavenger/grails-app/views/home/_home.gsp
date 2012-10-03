<div id="" style="margin-bottom: 100px;">
	<g:form controller="home" action="home">
		<h1 style="margin-left: 10px;">Home</h1>
		<hr/>
		<g:each in="${news}">
			<h2 style="text-align: center;">${it?.title}</h2>
			<div class="news-text" style="background-color: #FFFFCC;">
				${it?.text }
			</div>
			<br/>
			<br/>
			<hr/>
		</g:each>
	</g:form>
</div>