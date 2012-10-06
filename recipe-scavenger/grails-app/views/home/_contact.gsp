<g:form controller="home">
	<h1 style="margin-left: 10px;">Contact Us</h1>
	<hr/>
	<div>
		<div id="contact-desc">
			<p>
				Here at <b>Recipe Scavenger</b> we are always looking to improve ourselves. If you have any 
				questions or concerns please don't hesitate to contact us. 
				
				Please select your type of question or concern, and write a brief summary. If you are 
				logged in, we will respond to your account email, otherwise specify one below. 
				
				<b>Thank you</b> for your help!
			</p>
		</div>
		<div id="contact-type">
			<h2 style="text-align: center">Narrow it Down</h2>
		</div>
		<div id="contact-info">
			<h2 style="text-align: center">Who are you?</h2>
			<g:textField name="contactInformation"></g:textField>
		</div>
		<div id="contact-question">
			<h2 style="text-align: center">What's up?</h2>
			<g:textArea name="contactQuestion" 
						style="height: 250px; width: 245px; resize: none; margin: 10px auto 0 auto;">
			</g:textArea>
		</div>
	
	</div>
</g:form>