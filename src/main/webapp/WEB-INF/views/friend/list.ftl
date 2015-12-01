<#list friends as friend>
	<li class="friendItem" data-name="${friend["name"]}" id="${friend["friendId"]}">
		<img src="https://graph.facebook.com/${friend["friendId"]}/picture" />
		<div>${friend["name"]}</div>
	</li>	
</#list>
		