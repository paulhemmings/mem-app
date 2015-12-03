package com.razor.memories.controllers;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.google.gson.Gson;
import com.razor.memories.builders.StoryBuilder;
import com.razor.memories.factories.StoryHandlerFactory;
import com.razor.memories.handlers.StoryHandler;
import com.razor.memories.models.RequestResponse;
import com.razor.memories.models.Story;
import com.razor.memories.models.StoryDTO;
import com.razor.memories.viewmodels.CommentMap;
import com.razor.memories.viewmodels.FriendMap;
import com.razor.memories.viewmodels.StoryMap;
import com.razor.memories.wrappers.TemplateHelper;
import spark.utils.StringUtils;

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

    	StoryHandler handler = new StoryHandlerFactory().buildStoryHandler();
    	Story story = handler.getStory(storyId);

    	if(story==null || story.getCreatorId().equals(String.valueOf(creatorId))) {
    		return;
    	}

		Map<String, Object> root = new StoryMap().build(story);
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
			String creatorId = String.valueOf(this.facebookSR.getUser_id());
			StoryDTO storyDTO = new Gson().fromJson(request.getParameter("storyDTO"), StoryDTO.class);

			StoryHandler handler = new StoryHandlerFactory().buildStoryHandler();
			Story existingStory = handler.getStory(storyDTO.storyId);

			if (existingStory!=null && existingStory.getCreatorId().equals(creatorId)) {
				requestResponse.setMessage("you are not the owner of this story.");
			} else if (StringUtils.isEmpty(storyDTO.storyText)) {
				requestResponse.setMessage("the story is empty.");
			} else {
				Story story = new StoryBuilder().build(storyDTO);
				story.setCreatorId(creatorId);
				if (existingStory != null) {
					story.setId(existingStory.getId());
				}
				handler.upsertRecordedStory(story);
				requestResponse.setMessage("stored");
			}
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

			StoryHandler handler = new StoryHandlerFactory().buildStoryHandler();
			List<Story> stories = handler.listStoriesByCreatorId(String.valueOf(creatorId));

			Writer writer = new StringWriter();
			for(Story story:stories){
				Map<String, Object> root = new StoryMap().build(story);
				TemplateHelper.callTemplate(configuration, writer, ctrlName + "/list_item.ftl", root);
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

			StoryHandler handler = new StoryHandlerFactory().buildStoryHandler();
			List<Story> stories = handler.listSharedStories(String.valueOf(creatorId));
			requestResponse.setRequestLogInfo(String.format("%d returned %d stories", creatorId, stories.size()));

	        Map<String, Object> root = new HashMap<String, Object>();
	        root.put("storyCount", stories.size());
	        root.put("userId", creatorId);
	        root.put("sharedStories", new StoryMap().build(stories));

	        String responseHtml = TemplateHelper.callTemplate(configuration, ctrlName + "/listShared.ftl",root);
	        requestResponse.setResponseHtml(responseHtml);

    	}catch(Exception ex){
    		requestResponse.setMessage("listShared failed with: " + ex.getMessage());
    	}
		this.response.setContentType("text/json; charset=utf-8");
		this.response.getWriter().println(requestResponse.toJson());
    }

}
