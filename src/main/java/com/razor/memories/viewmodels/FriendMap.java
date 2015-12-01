package com.razor.memories.viewmodels;

import com.razor.memories.models.StoryFriend;
import com.restfb.types.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FriendMap {

	public static List<Map<String,Object>> build(List<StoryFriend> friends){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(StoryFriend friend:friends){
			Map<String,Object> item = new HashMap<String,Object>();
			item.put("friendId",friend.getFriendId());
			item.put("name", "");
			result.add(item);
		}
		return result;
	}
	
	public static List<Map<String,Object>> buildFromUserList(List<User> friendList){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(User friend:friendList){
			Map<String,Object> item = new HashMap<String,Object>();
			item.put("friendId",friend.getId());
			item.put("name", friend.getName());
			result.add(item);
		}
		return result;
	}	
	
	public static List<Map<String,Object>> buildFromTestList(long testUserId){
		List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
		for(long index = testUserId-10;index<testUserId+10;index++){
			Map<String,Object> item = new HashMap<String,Object>();
			item.put("friendId",index);
			item.put("name", String.format("name %d name", index));
			result.add(item);
		}
		return result;
	}		
}
