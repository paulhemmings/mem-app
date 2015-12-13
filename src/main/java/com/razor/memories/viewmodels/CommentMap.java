package com.razor.memories.viewmodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.razor.memories.models.Story.StoryComment;

public class CommentMap {

	public List<Map<String,Object>> build(List<StoryComment> comments){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		if (comments != null) {
			for (StoryComment comment : comments) {
				Map<String, Object> item = this.build(comment);
				result.add(item);
			}
		}
		return result;
	}
	
	public Map<String,Object> build(StoryComment storyComment){
        Map<String, Object> root = new HashMap<String, Object>();
		root.put("id", storyComment.getId());
    	root.put("friendId",storyComment.getFriendId());
    	root.put("commentText",storyComment.getComment());
        return root;
	}
}
