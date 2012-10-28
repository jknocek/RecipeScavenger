<g:if test="${session.error?.size() > 0}">
	<div class="content">
		<div class="error-message">
			<h4 style="text-align:center;">${session.error}</h4>
		</div>
	</div>
	<br/>
</g:if>