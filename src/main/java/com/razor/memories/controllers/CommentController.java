package com.razor.memories.controllers;

import com.razor.memories.factories.StoryHandlerFactory;
import com.razor.memories.handlers.StoryHandler;
import com.razor.memories.models.RequestResponse;
import com.razor.memories.models.Story;
import com.razor.memories.models.Story.StoryComment;
import com.razor.memories.models.Story.StoryFriend;
import com.razor.memories.viewmodels.CommentMap;
import com.razor.memories.wrappers.TemplateHelper;
import spark.utils.StringUtils;

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
		StoryHandler storyHandler = new StoryHandlerFactory().buildStoryHandler();

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
		}else if(story.getCreatorId().equals(String.valueOf(creatorId))) {
			creatorIsSharedFriendOfStory = true;
		}else{
			List<StoryFriend> friends = story.getAttachedFriends();
			for (StoryFriend friend:friends) {
				if(friend.getFriendId().equals(String.valueOf(creatorId))) {
					creatorIsSharedFriendOfStory = true;
					break;
				}
			}
		}

		if (!creatorIsSharedFriendOfStory) {
			requestResponse.setMessage("you are not a shared friend of this story");
		}

		if (requestResponse.isSuccess()) {
			// store comment
			StoryComment storyComment = storyHandler.addComment(storyId, String.valueOf(creatorId), commentText);

			// build html

	        Map<String, Object> root = new CommentMap().build(storyComment);
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
		StoryHandler storyHandler = new StoryHandlerFactory().buildStoryHandler();

		// get parameters

		long creatorId = this.facebookSR.getUser_id();
		String commentId = request.getParameter("commentId");
		String storyId = request.getParameter("storyId");

		// validate parameters

		if (StringUtils.isEmpty(commentId)) {
			requestResponse.setMessage("no comment ID supplied");
		}

		// implement action

		if (requestResponse.isSuccess()) {
			storyHandler.removeComment(storyId, String.valueOf(creatorId), commentId);
		}

		// return response
		this.response.setContentType("text/json; charset=utf-8");
		this.response.getWriter().println(requestResponse.toJson());
	}

}
