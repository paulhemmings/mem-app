package com.razor.memories.controllers;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.razor.memories.builders.StoryBuilder;
import com.razor.memories.handlers.StoryHandler;
import com.razor.memories.models.RequestResponse;
import com.razor.memories.models.Story;
import com.razor.memories.viewmodels.CommentMap;
import com.razor.memories.viewmodels.FriendMap;
import com.razor.memories.viewmodels.StoryMap;
import com.razor.memories.wrappers.TemplateHelper;

@SuppressWarnings("serial")
public class StoryController extends Controller {

    public static final String ctrlName = "/story";

    /**
     * get an existing story
     * @throws Exception
     */

    public void get() throws Exception{

    	long creatorId = this.facebookSR.getUser_id();
    	String storyId = request.getParameter("storyId");
    	StoryHandler handler = new StoryHandler();
    	Story story = handler.getStory(storyId);

    	if(story==null || story.getCreatorId() != creatorId){
    		return;
    	}

		Map<String, Object> root = new HashMap<String, Object>();
		try{
			root.put("storyId",story.getStoryKey());
			root.put("storyText",story.getText());
			root.put("sharedWithCount", story.getAttachedFriends().size());
			root.put("friends", FriendMap.build(story.getAttachedFriends()));
			root.put("comments",CommentMap.build(story.getAttachedComments()));
		}catch(Exception ignore){}

		TemplateHelper.callTemplate(configuration, response, ctrlName + "/list_item.ftl",root);
    }

    /**
     * Store a story (including update existing)
     * @throws Exception
     */

    public void store() throws Exception
    {
    	RequestResponse requestResponse = new RequestResponse();
    	try{

			StoryBuilder builder = new StoryBuilder();
			long creatorId = this.facebookSR.getUser_id();
			String friendList = request.getParameter("friendList");
			String storyText = request.getParameter("storyText");
			String storyId = request.getParameter("storyId");

			StoryHandler handler = new StoryHandler();
			Story existingStory = handler.getStory(storyId);

			if(existingStory!=null && existingStory.getCreatorId() != creatorId){

				requestResponse.setMessage("you are not the owner of this story.");

			}else if(storyText.trim().length()==0){

				requestResponse.setMessage("the story is empty.");

			}else{

				Story recordedStory = builder.build(existingStory, creatorId, friendList, storyText);
				String storeMessage = handler.upsertRecordedStory(recordedStory);
				requestResponse.setMessage(storeMessage);
			}
			/*
	        Map<String, Object> root = new HashMap<String, Object>();
	        root.put("message",message);
	        TemplateHelper.callTemplate(configuration,response, ctrlName + "/stored.ftl",root);
	        */

    	}catch(Exception ex){
    		requestResponse.setMessage("listShared failed with: " + ex.getMessage());
    	}
		this.response.setContentType("text/json; charset=utf-8");
		this.response.getWriter().println(requestResponse.toJson());
    }

    /**
     * List own stories
     * @throws IOException
     * @throws ServletException
     */

    public void listOwn() throws IOException, ServletException
    {
    	RequestResponse requestResponse = new RequestResponse();
    	try{

			long creatorId = this.facebookSR.getUser_id();

			StoryHandler handler = new StoryHandler();
			List<Story> stories = handler.listStoriesByCreatorId(creatorId);

			Writer writer = new StringWriter();
			for(Story story:stories){
				Map<String, Object> root = StoryMap.build(story);
				TemplateHelper.callTemplate(configuration, writer, ctrlName + "/list_item.ftl",root);
			}

	        Map<String, Object> root = new HashMap<String, Object>();
	        root.put("storyItems",writer.toString());

	        String responseHtml = TemplateHelper.callTemplate(configuration, ctrlName + "/listOwn.ftl",root);
	        requestResponse.setResponseHtml(responseHtml);

    	}catch(Exception ex){
    		requestResponse.setMessage("listShared failed with: " + ex.getMessage());
    	}
		this.response.setContentType("text/json; charset=utf-8");
		this.response.getWriter().println(requestResponse.toJson());
    }

    /**
     * List shared stories
     * @throws IOException
     * @throws ServletException
     */

    public void listShared() throws IOException, ServletException{
    	RequestResponse requestResponse = new RequestResponse();
    	try{
	    	long creatorId = this.facebookSR.getUser_id();

			StoryHandler handler = new StoryHandler();
			List<Story> stories = handler.listSharedStories(creatorId);
			requestResponse.setRequestLogInfo(String.format("%d returned %d stories", creatorId, stories.size()));

	        Map<String, Object> root = new HashMap<String, Object>();
	        root.put("storyCount", stories.size());
	        root.put("userId", creatorId);
	        root.put("sharedStories",StoryMap.build(stories));

	        String responseHtml = TemplateHelper.callTemplate(configuration, ctrlName + "/listShared.ftl",root);
	        requestResponse.setResponseHtml(responseHtml);

    	}catch(Exception ex){
    		requestResponse.setMessage("listShared failed with: " + ex.getMessage());
    	}
		this.response.setContentType("text/json; charset=utf-8");
		this.response.getWriter().println(requestResponse.toJson());
    }

}
