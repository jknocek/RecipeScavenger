<g:form controller="home" action="home">
	<h1 style="margin-left: 10px;">News</h1>
	<hr/>
	<g:if test="${newsPostedToday}">
		<div class="openAccordion">
			<h3 style="text-align: center;"><a href="#">Posted Today</a></h3>
			<div class="accordion" style="width:813px;">
				<g:each in="${newsPostedToday}">
					<h3 style="text-align: center;"><a href="#">${it?.title} -  ${dateFormat.format(it?.createdDate)}</a></h3>
					<div>
						<table style="margin: 0 auto; width: 750px;">
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
						<div class="news-text">
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
			<div class="accordion" style="width:813px;">
				<g:each in="${newsPostedThisWeek}">
					<h3 style="text-align: center;"><a href="#">${it?.title} -  ${dateFormat.format(it?.createdDate)}</a></h3>
					<div>
						<table style="margin: 0 auto; width: 750px;">
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
						<div class="news-text">
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
			<div class="accordion" style="width:813px;">
				<g:each in="${newsRemaining}">
					<h3 style="text-align: center;"><a href="#">${it?.title} -  ${dateFormat.format(it?.createdDate)}</a></h3>
					<div>
						<table style="margin: 0 auto; width: 750px;">
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
						<div class="news-text">
							${it?.text }
						</div>
					</div>
				</g:each>
			</div>
		</div>
	</g:if>
</g:form>