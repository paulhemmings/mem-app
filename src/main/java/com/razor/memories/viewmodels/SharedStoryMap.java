package com.razor.memories.viewmodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.razor.memories.handlers.StoryHandler;
import com.razor.memories.models.Story;
import com.razor.memories.models.StoryFriend;

public class SharedStoryMap {
	
	/**
	 * Build a list of stories along with their creatorId from the list of friends
	 * @param friends
	 * @return
	 * @throws Exception 
	 */
	
	public static List<Map<String,Object>>  build(List<StoryFriend> friends) throws Exception{		
		StoryHandler handler = new StoryHandler();
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(StoryFriend friend:friends){						
			if(friend.getAttachedStory() != null){
				Story attachedStory = handler.getStory(friend.getAttachedStory().getStoryKey());
				if(!attachedStory.isDeleted()){
					Map<String,Object> item = new HashMap<String,Object>();				
					item.put("storyId", attachedStory.getStoryKey());
					item.put("text", attachedStory.getText());
					item.put("creatorId", friend.getStoryCreatorId());
					item.put("comments", CommentMap.build(attachedStory.getAttachedComments()));
					result.add(item);
				}
			}
		}
		return result;
	}
}
