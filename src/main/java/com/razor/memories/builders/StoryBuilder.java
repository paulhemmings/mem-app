package com.razor.memories.builders;

import com.razor.memories.models.Constants;
import com.razor.memories.models.Story;
import com.razor.memories.models.StoryFriend;

import java.util.Date;
import java.util.List;


public class StoryBuilder {

	/**
	 * Build a story from the creator, list of shared friends, and the text of the story
	 * @param creatorId
	 * @param friendCsv
	 * @param storyText
	 * @return
	 */
	
	public Story build(Long creatorId, String friendCsv, String storyText){
		return this.build(null, creatorId, friendCsv, storyText);
	}
	
	/**
	 * Build a story from the creator, list of shared friends, and the text of the story
	 * @param story
	 * @param creatorId
	 * @param friendCsv
	 * @param storyText
	 * @return
	 */	
	
	public Story build(Story story, Long creatorId, String friendCsv, String storyText){
		
		if(storyText!=null && !storyText.trim().isEmpty()){
			
			try{
				
				// create story if necessary
				if(story==null){
					story = new Story();
					story.setDateCreated(new Date());	
				} 
				
				// set the details
				
				story.setText(storyText);
				story.setCreatorId(creatorId);
				
				// clear any existing friends as we're going to recreate them
				// is this creating orphaned friends?
				
				story.getAttachedFriends().clear();
				
				// get the list of friend Ids selected
				if(friendCsv!=null && !friendCsv.trim().isEmpty() ){
				
					String[] friendList = friendCsv.contains(",") ? friendCsv.split(",") : new String[]{friendCsv};
					
					// loop through those friends
					
					List<StoryFriend> friends = story.getAttachedFriends();
					for(String id:friendList){
						long friendId = Long.valueOf(id);
						StoryFriend mf = new StoryFriend(friendId, Constants.PRIVATE);
						mf.setAttachedStory(story);
						mf.setStoryKey(story.getStoryKey()); // for debugging
						mf.setStoryCreatorId(creatorId);
						friends.add(mf);
					}	
				}				
								
			}catch(Exception ignore){}
		}
		return story;
	}
}
