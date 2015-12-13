<#list sharedStories as sharedStory>
	<div id="${sharedStory["id"]}" class="existingStory storyBlock">
		<div class="storyText">
			<div class="sharedStoryCreator" style="display:inline;padding-right:10px;float:left;">
				<img class="friendImage" src="https://graph.facebook.com/${sharedStory["creatorId"]}/picture" />
			</div>
			<div class="sharedStoryText" style="vertical-align:top;display:inline;">
				${sharedStory["storyText"]}
			</div>
		</div>
		
		<div class="commentDisplayChoose">
			<a class="showComments">show comments</a>
		</div>		
			
		<ul class="storyDiscussionList">
			
			<#list sharedStory["comments"] as comment>
				<li id="${comment["id"]}" class="storyDiscussionItem">
					<img class="commentImage" src="https://graph.facebook.com/${comment["friendId"]}/picture" />
					<div class="storyDiscussionItemText">${comment["commentText"]}</div>
					<div class="storyDiscussionItemDelete">[x]</div>
				</li>
			</#list>
			
			<li class="storyDiscussionItem">
				<!--
				<img class="commentImage" src="https://graph.facebook.com/${userId}/picture" />
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
</#list>
