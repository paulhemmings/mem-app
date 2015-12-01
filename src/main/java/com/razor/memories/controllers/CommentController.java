package com.razor.memories.controllers;

import com.razor.memories.handlers.CommentHandler;
import com.razor.memories.handlers.StoryHandler;
import com.razor.memories.models.RequestResponse;
import com.razor.memories.models.Story;
import com.razor.memories.models.StoryComment;
import com.razor.memories.models.StoryFriend;
import com.razor.memories.viewmodels.CommentMap;
import com.razor.memories.wrappers.TemplateHelper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class CommentController extends Controller {

	public static final String ctrlName = "/comment";

	/**
	 * Store a comment
	 * @throws Exception
	 */

	public void store() throws Exception{

		RequestResponse requestResponse = new RequestResponse();
		StoryHandler storyHandler = new StoryHandler();

		// get parameters

		long creatorId = this.facebookSR.getUser_id();
		String storyId = request.getParameter("storyId");
		String commentText = request.getParameter("commentText");

		// validate parameters

		if(commentText==null || commentText.trim().length()==0){
			requestResponse.setMessage("empty comment");
		}

		if(storyId == null || storyId.trim().length()==0){
			requestResponse.setMessage("no story ID defined");
		}

		boolean creatorIsSharedFriendOfStory = false;
		Story story = storyHandler.getStory(storyId);

		if(story==null){
			requestResponse.setMessage("no story with that ID exists");
		}else if(story.getCreatorId() == creatorId){
			creatorIsSharedFriendOfStory = true;
		}else{
			List<StoryFriend> friends = story.getAttachedFriends();
			for(StoryFriend friend:friends){
				if(friend.getFriendId()==creatorId){
					creatorIsSharedFriendOfStory = true;
					break;
				}
			}
		}

		if(!creatorIsSharedFriendOfStory){
			requestResponse.setMessage("you are not a shared friend of this story");
		}

		if(requestResponse.isSuccess()){

			// store comment
			CommentHandler commentHandler = new CommentHandler();
			StoryComment storyComment = commentHandler.addStoryComment(storyId, creatorId, commentText);

			// build html

	        Map<String, Object> root = CommentMap.build(storyComment);
	        requestResponse.setResponseHtml(TemplateHelper.callTemplate(configuration, ctrlName + "/item.ftl",root));
		}

		// return response

		this.response.setContentType("text/json; charset=utf-8");
		this.response.getWriter().println(requestResponse.toJson());

	}

	/**
	 * Delete a comment
	 * @throws IOException
	 */

	public void delete() throws IOException{

		RequestResponse requestResponse = new RequestResponse();
		CommentHandler handler = new CommentHandler();

		// get parameters

		long creatorId = this.facebookSR.getUser_id();
		String commentId = request.getParameter("commentId");

		// validate parameters

		StoryComment comment = handler.getComment(commentId);

		if(comment==null){
			requestResponse.setMessage("a comment with that ID does not exist");
		}else if(comment.getCreatorId() != creatorId){
			requestResponse.setMessage("you are not the owner of this comment");
		}

		// implement action

		if(requestResponse.isSuccess()){
			handler.deleteComment(commentId);
		}

		// return response
		this.response.setContentType("text/json; charset=utf-8");
		this.response.getWriter().println(requestResponse.toJson());
	}

}
