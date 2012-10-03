<div id="" style="margin-bottom: 100px;">
	<g:form controller="home" action="home">
		<h1 style="margin-left: 10px;">News</h1>
		<hr/>
		<div id="accordion">
			<g:each in="${news}">

				<h3 style="text-align: center;"><a href="#">${it?.title} -  ${dateFormat.format(it?.createdDate)}</a></h3>
				<div>
					<table style="margin: 0 auto; width: 960px;">
						<tr>
							<td>
								<div class="news-title">
									<h2>${it?.title}</h2>
								</div>
							</td>
						</tr>
						<tr>
							<td>
								<div class="news-info">
									<p style="text-align: left; margin: 0 0 0 0">Written by: ${it?.author.username}</p>
									<p style="text-align: left; margin: 0 0 10px 0">Published On: ${dateFormat.format(it?.createdDate)}</p>
								</div>
							</td>
						</tr>
					</table>
					
					<div class="news-text" style="background-color: #FFFFCC;">
						${it?.text }
					</div>
				</div>
			</g:each>
		</div>
	</g:form>
</div>