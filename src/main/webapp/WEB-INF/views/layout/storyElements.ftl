<script type="text/javascript">

Razor.Comms = 
{
	pullData : function(url,data, callback){
		Razor.Comms.baseRequest({
			type: 		'GET',
			url:		url,
			data:		data,
			success:	function(data){
							callback(data);
						}
		});
	},
	
	baseRequest : function(request){
	
		// add signed request to every call
		request.data["signed_request"] = $('#signed_request').html();
	
		// make the request
		$.ajax({
			type: 		request.type,
			url: 		request.url,
			data: 		request.data,
			success: 	function(data){
							request.success(data);
						}
		});	
	}
};

Razor.Friend =
{	
	list : function(resultContainer){
		Razor.Comms.pullData('/friend/list',{},resultContainer);
	}
}

Razor.Comment = 
{
	store: function(storyId, commentText, callback){
		Razor.Comms.baseRequest({
			type: 'POST',
			url: '/comment/store',
			data:{
				storyId: storyId,
				commentText: commentText
			},
			success: function(data) {
				callback(data);				
			}		
		});	
	},
	
	erase: function(commentId,callback){
		Razor.Comms.baseRequest({
			type: 'POST',
			url: '/comment/delete',
			data:{
				commentId: commentId
			},
			success: function(data) {
				callback(data);				
			}		
		});		
	}
}

	
Razor.Story =
{
	get : function(storyId, callback){
		Razor.Comms.pullData('/story/get',{ storyId: storyId },callback);
	},
	
	store : function(storyId, storyText, friendList, callback){
		Razor.Comms.baseRequest({
			type: 'POST',
			url: '/story/store',
			data:{
				storyId: storyId,
				storyText: storyText,
				friendList: friendList
			},
			success: function(data) {
				callback(data);				
			}		
		});
	},
	
	listOwn : function(callback){
		Razor.Comms.pullData('/story/listOwn',{},callback);
	},
	
	listShared : function(callback){
		Razor.Comms.pullData('/story/listShared',{},callback);
	}	
}	

Razor.UI = 
{
	refreshOwn : function(){
		Razor.Story.listOwn(function(result){
			if(result["errorMessage"]==null){
				var responseHtml = result["responseHtml"];
				$('#storyListContainer').html(responseHtml);
				$(".newStory > .storyText").html("tell your story here.."); 
			}
		});
	}	
}

</script>