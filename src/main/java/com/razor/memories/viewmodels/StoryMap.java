package com.razor.memories.viewmodels;

import com.razor.memories.models.Story;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StoryMap {

	public List<Map<String,Object>> build(List<Story> stories) throws Exception{
		List<Map<String,Object>> result = new ArrayList<>();
		for(Story story:stories){
			Map<String,Object> item = build(story);
			result.add(item);
		}
		return result;
	}
	
	public Map<String,Object> build(Story story){
		Map<String,Object> item = new HashMap<>();
		item.put("id", story.getId());
		item.put("creatorId", story.getCreatorId());
		item.put("storyText",story.getText());
		item.put("friends", new StoryFriendMap().build(story.getAttachedFriends()));
		item.put("comments", new CommentMap().build(story.getAttachedComments()));
		return item;
	}	
	
}
