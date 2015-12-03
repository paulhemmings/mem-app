package com.razor.memories.viewmodels;

import com.razor.memories.models.Story;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by paulhemmings on 12/3/15.
 */

public class StoryFriendMap {

    public List<Map<String,Object>> build(List<Story.StoryFriend> friends){
        List<Map<String,Object>> result = new ArrayList<Map<String,Object>>();
        if (friends != null) {
            for(Story.StoryFriend friend:friends){
                Map<String,Object> item = new HashMap<String,Object>();
                item.put("friendId",friend.getFriendId());
                item.put("name", "");
                result.add(item);
            }
        }
        return result;
    }

}
