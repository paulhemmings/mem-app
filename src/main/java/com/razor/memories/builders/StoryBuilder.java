package com.razor.memories.builders;

import com.razor.memories.models.Story;
import com.razor.memories.models.StoryDTO;

public class StoryBuilder {

    public Story build(StoryDTO storyDTO) {
        Story story = new Story();
        story.setText(storyDTO.storyText);
        for (String friendId : storyDTO.friendList.split(",")) {
            story.addFriend(new Story.StoryFriend().setFriendId(friendId));
        }
        return story;
    }
}
