<#list comments as comment>
	<li id="${comment["commentId"]}" class="storyDiscussionItem">
		<img class="commentImage" src="https://graph.facebook.com/${comment["creatorId"]}/picture" />
		<div class="storyDiscussionItemText">${comment["commentText"]}</div>
		<div class="storyDiscussionItemDelete">[x]</div>
	</li>
</#list>

<li class="storyDiscussionItem" >
	<!--
	<img class="commentImage" src="https://graph.facebook.com/${creatorId}/picture" />
	-->
	<div class="readwrite" contenteditable="true">
		</br>
	</div>		
	<div class="addCommentChooserRow">
		<div class="addComment button">add comment</div>
	</div>	
</li>