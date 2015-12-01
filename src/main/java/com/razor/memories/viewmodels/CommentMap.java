package com.razor.memories.viewmodels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.razor.memories.models.StoryComment;

public class CommentMap {

	public static List<Map<String,Object>> build(List<StoryComment> comments){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(StoryComment comment:comments){
			Map<String,Object> item = CommentMap.build(comment);
			result.add(item);
		}
		return result;
	}
	
	public static Map<String,Object> build(StoryComment storyComment){
        Map<String, Object> root = new HashMap<String, Object>();
    	root.put("creatorId",storyComment.getCreatorId());  
    	root.put("commentId",storyComment.getKey() == null ? "" : storyComment.getKey());
    	root.put("commentText",storyComment.getText());  		
        return root;
	}
}
