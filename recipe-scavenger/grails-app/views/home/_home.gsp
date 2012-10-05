<div id="" style="margin-bottom: 100px;">
	<div id="tabs">
		<ul>
			<li><h3><a href="#tabs-1">News</a></h3></li>
			<li><h3><a href="#tabs-2">Contact Us</a></h3></li>
			<li><h3><a href="#tabs-3">About Recipe Scavenger</a></h3></li>
		</ul>
		<div id="tabs-1">
			<g:form controller="home" action="home">
				<h1 style="margin-left: 10px;">News</h1>
				<hr/>
				<g:if test="${newsPostedToday}">
					<div class="openAccordion">
						<h3 style="text-align: center;"><a href="#">Posted Today</a></h3>
						<div class="accordion" style="width:1023px;">
							<g:each in="${newsPostedToday}">
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
					</div>
				</g:if>
				<g:if test="${newsPostedThisWeek}">
					<div class="openAccordion">
						<h3 style="text-align: center;"><a href="#">Posted This Week</a></h3>
						<div class="accordion" style="width:1023px;">
							<g:each in="${newsPostedThisWeek}">
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
					</div>
				</g:if>
				<g:if test="${newsRemaining}">
					<div class="accordion">
						<h3 style="text-align: center;"><a href="#">Remaining</a></h3>
						<div class="accordion" style="width:1023px;">
							<g:each in="${newsRemaining}">
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
					</div>
				</g:if>
			</g:form>
		</div>
	</div>
</div>