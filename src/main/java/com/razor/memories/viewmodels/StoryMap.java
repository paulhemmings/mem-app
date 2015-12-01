package com.razor.memories.viewmodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.razor.memories.handlers.StoryHandler;
import com.razor.memories.models.Story;

public class StoryMap {

	public static List<Map<String,Object>> build(List<Story> stories) throws Exception{
		StoryHandler handler = new StoryHandler();
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(Story tmp:stories){
			if(tmp!=null && tmp.getStoryKey()!=null){
				Story story = handler.getStory(tmp.getStoryKey());
				if(story!=null){
					Map<String,Object> item = build(story);
					result.add(item);
				}
			}
		}
		return result;
	}
	
	public static Map<String,Object> build(Story story){
		Map<String,Object> item = new HashMap<String,Object>();
		item.put("creatorId", story.getCreatorId());
		item.put("storyId", story.getStoryKey() == null ? "" : story.getStoryKey());
		item.put("storyText",story.getText());
		//item.put("sharedWithCount", story.getAttachedFriends().size());
		item.put("friends", FriendMap.build(story.getAttachedFriends()));
		item.put("comments",CommentMap.build(story.getAttachedComments()));
		return item;
	}	
	
}
