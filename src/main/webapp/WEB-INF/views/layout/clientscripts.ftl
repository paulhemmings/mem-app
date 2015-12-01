<script type="text/javascript">

	$(function(){
	
				/* 
				 * Set the events
				 */
				 
				$('#storyHelp').click(function(){
					$(this).hide('slow');
				});
				
				$('.storyText').live('focus',function(){
					$('#storyHelp').hide('slow');
				});
				 
				$('#yoursOrSharedChooser').click(function(){
					var isYours = $(this).hasClass("yours");
					if(!isYours){	 			
						$(this).addClass("yours");
						$(this).removeClass("shared");
						Razor.Story.listOwn(function(result){
							if(result["errorMessage"]==null){
								var responseHtml = result["responseHtml"];
								$('#storyListContainer').html(responseHtml);
							}
						});					
					}else{						
						$(this).addClass("shared");
						$(this).removeClass("yours");
						Razor.Story.listShared(function(result){
							if(result["errorMessage"]==null){
								var responseHtml = result["responseHtml"];
								$('#storyListContainer').html(responseHtml);	
							}										
						});					
					}
				});
				 
				$('.storyText').live('click',function(){
					// if not editing already
					if($(this).hasClass('readwrite')) return;
					
					// reset one that is editing
					
					$('.storyText.readwrite').each(function(){
						$(this).removeClass('readwrite');						
					});
					
					/*
					// for now only let them edit one section at a time
					if( $('.storyText.readwrite').length>0 ) return;
					*/
					
					// set this to editing
					$(this).addClass('readwrite');
					
					// CSS isn't updating in chrome (does on firefox), need to remind it to show the store option
					$(".storyText + .storySharedList + .storeStoryChooser").each(function(){
						$(this).hide();
					});
					$(".storyText.readwrite + .storySharedList + .storeStoryChooser").show();
				});
			
				$('#friendFilter').keyup(function(){
					// filter the friend list by filter text
					var filterText = $(this).val().toLowerCase();
					
					$('#friendContainer > .friendItem').each(function(){
						var match = $(this).attr('data-name').toLowerCase().match(filterText);
						if(match){
							$(this).show();
						}else{
							$(this).hide();
						}
					});
				});
				
				$('.friendItem').live('click',function(){
				
					var targetList;
					if( $(this).hasClass("attached")){
						var editingFriendListLength = $(this).closest(".storyText.readwrite + .storySharedList").length;
						if(editingFriendListLength==0) return;
						$(this).remove();
					}else{
						var editingFriendList = $(".storyText.readwrite + .storySharedList");
						var friendId=$(this).attr('id');
						// exist already?
						var exists = $("li[id='" + friendId + "']",editingFriendList).length;
						// add
						if(exists==0){
							$(this).clone().appendTo(editingFriendList).fadeIn(function () {
								$(this).addClass("attached");
								$(this).css({display:inline});
							});
						}
					}
				});
			
				$('.storeStoryChooser a.cancel').live('click',function(){
					 
					// get details
					var editingSection = $(this).closest('.storyBlock');
					var storySection =  $('.storyText.readwrite',editingSection);
					var storyId = $(editingSection).attr('id'); 
										
					// stop editing(!)										
					$(storySection).removeClass('readwrite');
					
					// remove options (css doesn't always update correctly)
					$(".storyText + .storySharedList + .storeStoryChooser").each(function(){
						$(this).hide();
					});					
					
					if(storyId!=null){
						// hit with big hammer
						Razor.Story.get(storyId, function(result){
							$(editingSection).remove();
							$('#storyListContainer').append(result);					
						});
					}
				});
				
				$('.storeStoryChooser a.store').live('click',function(){
					
					var editingSection = $(this).closest('.storyBlock');
					var storySection =  $('.storyText.readwrite',editingSection);
					var storyText = $(storySection).html();
					var friendsArray = $('.friendItem',editingSection);
					var storyId = $(editingSection).attr('id'); 					
					var friendList = friendsArray.map(function(){return this.id}).get().join(',');
										
					// stop editing
					$(storySection).removeClass("readwrite");			
					
					// remove options (css doesn't always update correctly)
					$(".storyText + .storySharedList + .storeStoryChooser").each(function(){
						$(this).hide();
					});			
					
					// say something...
					$(storySection).html("storing...");
					
					Razor.Story.store(storyId, storyText,friendList,function(result){
						// update stored story list
						if(storyId!=null){
							Razor.UI.refreshOwn();					
						}else{
							// clear the shared friend list
							$('li','.newStory > .storySharedList').each(function(){$(this).remove();});
							// wait eight seconds then do it						
							//setTimeout('  Razor.Story.listOwn(function(own){$("#storyListContainer").html(own);}); $(".newStory > .storyText").html("tell your story here");  ', 8000)
							setTimeout('Razor.UI.refreshOwn();',8000);
						}
					});
					
				});
				
				$('.addComment').live('click',function(){
				
					var discussionList = $(this).closest('.storyDiscussionList');
					var storyBlock = $(this).closest('.storyBlock');					
					var discussionItem = $(this).closest('.storyDiscussionItem');										
					var discussionText = $('.readwrite',discussionItem).html();					
					var storyId = $(storyBlock).attr('id');
					
					Razor.Comment.store(storyId, discussionText, function(result){					
						if(result["errorMessage"]==null){
							var responseHtml = result["responseHtml"];					
							$(discussionItem).remove();							
							$(discussionList).append(responseHtml);							
						}						
					});
												
				});
				
				$('.storyDiscussionItemDelete').live('click',function(){
					var discussionItem = $(this).closest('.storyDiscussionItem');
					var commentId = $(discussionItem).attr('id');
					
					Razor.Comment.erase(commentId,function(result){
						if(result["errorMessage"]==null){
							$(discussionItem).remove();	
						}
					});
				});
				
				$('.commentDisplayChoose a.showComments').live('click',function(){
					var storyBlock = $(this).closest('.storyBlock');
					var commentList = $('.storyDiscussionList',storyBlock);
					var commentImage = $('img.showComments',storyBlock);
					if( $(this).html()=="show comments" ){
						$(this).html("hide comments");
						$(commentList).show();
					}else{
						$(this).html("show comments");
						$(commentList).hide();
					}
				});
				
				/*
				 * Initialise
				 */
				 
				Razor.Friend.list(function(result){
					if(result["errorMessage"]==null){
						var responseHtml = result["responseHtml"];
						$('#friendContainer').html(responseHtml);
					}
				});

				Razor.Story.listOwn(function(result){
					if(result["errorMessage"]==null){
						var responseHtml = result["responseHtml"];
						$('#storyListContainer').html(responseHtml);
					}
				});			
		
	});
</script>