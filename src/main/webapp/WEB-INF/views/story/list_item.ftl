<div id="${id}" class="existingStory storyBlock">
	<div class="storyText" contenteditable="true">
		${storyText}
	</div>
	<ul class="storySharedList">
		<#list friends as friend>
			<li class="friendItem attached" id="${friend["friendId"]}">
				<img class="friendImage" src="https://graph.facebook.com/${friend["friendId"]}/picture" />
			</li>			
		</#list>						
	</ul>
	<div class="storeStoryChooser"><a class="cancel">Cancel</a> | <a class="store">Store</a></div>
	
	<div class="commentDisplayChoose">
		<a class="showComments">show comments</a>
	</div>
	
	<ul class="storyDiscussionList">
		
		<#list comments as comment>
			<li id="${comment["commentId"]}" class="storyDiscussionItem">
				<img class="commentImage" src="https://graph.facebook.com/${comment["friendId"]}/picture" />
				<div class="storyDiscussionItemText">${comment["commentText"]}</div>
				<div class="storyDiscussionItemDelete">[x]</div>
			</li>
		</#list>
		
		<li class="storyDiscussionItem">
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
		
	</ul>
		
</div>	