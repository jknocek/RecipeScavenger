<g:form controller="home">
	<h1 style="margin-left: 10px;">Contact Us</h1>
	<hr/>
	<div>
		<div class="content-header">
			<p>
				Here at <b>Recipe Scavenger</b> we are always looking to improve ourselves. If you have any 
				questions or concerns please don't hesitate to contact us. 
				
				Please select your type of question or concern, and write a brief summary. If you are 
				logged in, we will respond to your account email, otherwise specify one below. 
				
				<b>Thank you</b> for your help!
			</p>
		</div>
		<div id="contact-type">
			<h2 class="contact-title">Narrow it Down</h2>
			<div style="margin:20% auto 0 auto; border: 1px solid #969696;">
				<g:each in="${contactOptions}">
					<div style="background-color: #fff; height:40px; border: 1px solid #969696;">
						<br/>
						<table  style="margin-left: 20px; width:100%;">
							<col align="left">
							<col align="right">
							<tr>
								<td style="width: 202px;">
									<h3 style="text-align: left;">${it.parameter}</h3>
								</td>
								<td>
									<g:checkBox name="${it.value}" />
								</td>
							</tr>
						</table>
					</div>
				</g:each>
			</div>
		</div>
		<div id="contact-info">
			<h2 class="contact-title">Who are you?</h2>
			<table style="margin-top: 40%; margin-left: auto; margin-right: auto;">
				<col align="left">
				<col align="left">
				<tr>
					<td>
						<h3>Email:</h3>
					</td>
					<td>
						<g:textField name="contactEmail" value="${session?.user?.email}"></g:textField>
					</td>
				</tr>
			</table>
			<g:actionSubmit value="Submit" action="doContact" style="position: absolute; bottom: 20px; right: 30px;"/>
		</div>
		<div id="contact-question">
			<h2 class="contact-title">What's up?</h2>
			<g:textArea name="contactQuestion" maxlength="255"
			style="height: 250px; width: 245px; resize: none; margin: 10px auto 0 auto;"></g:textArea>
		</div>
	
	</div>
</g:form>