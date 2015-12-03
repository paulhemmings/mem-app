package com.razor.memories.handlers;

import com.mongodb.MongoClient;
import com.razor.memories.factories.ModelProviderFactory;
import com.razor.memories.models.Story;
import com.razor.memories.providers.ModelProvider;
import spark.utils.StringUtils;

import java.util.List;
import java.util.Optional;

/**
 * Recorded Story Handler 
 * @author paulhemmings
 */

public class StoryHandler {

	private ModelProvider<Story> provider;
	public static final String COLLECTION_NAME = "stories";

	/**
	 * Initialize the provider
	 * @param client
	 * @param mongoDatabase
	 * @return
	 */

	public StoryHandler initialize(MongoClient client, String mongoDatabase) {
		this.provider = new ModelProviderFactory<Story>().buildProvider(ModelProviderFactory.ORM.JONGO, client, mongoDatabase, COLLECTION_NAME, Story.class);
		return this;
	}
	
	/**
	 * Get all stories that are shared with you.
	 * @param fbId
	 * @return
	 * @throws Exception 
	 */
	
	public List<Story> listSharedStories(String fbId) throws Exception{
		return this.provider.find("{ attachedFriends : { friendId: " + fbId + " }");
	}
		
	/**
	 * Get all the users Stories
	 * @param creatorId
	 * @return
	 */
	
	public List<Story> listStoriesByCreatorId(String creatorId){
		return this.provider.find("creatorId", creatorId);
	}
		
	/**
	 * Upsert Story
	 * @param recordedStory
	 */
	
	public Story upsertRecordedStory(Story recordedStory){
		return this.provider.update(recordedStory, recordedStory.getId());
	}

	/**
	 * Add a comment to an existing story (required?)
	 * @param storyId
	 * @param creatorId
	 * @param text
	 * @return
	 */

	public Story.StoryComment addComment(String storyId, String creatorId, String text) {
		Story story = this.provider.findById(storyId);
		Story.StoryComment comment = story.addComment(new Story.StoryComment().setComment(text).setFriendId(creatorId));
		this.upsertRecordedStory(story);
		return comment;
	}

	public Story removeComment(String storyId, String creatorId, String commentId) {
		Story story = this.provider.findById(storyId);
		Optional<Story.StoryComment> comment = story.getAttachedComments().stream()
				.filter(c -> c.getId().equals(commentId) && c.getFriendId().equals(creatorId))
				.findFirst();
		if (comment.isPresent()) {
			story.removeComment(comment.get());
			this.upsertRecordedStory(story);
		}
		return story;
	}

	/**
	 * Add a friend to a story (required?)
	 * @param storyId
	 * @param friendId
	 * @return
	 */

	public Story.StoryFriend addFriend(String storyId, String friendId) {
		Story story = this.provider.findById(storyId);
		Story.StoryFriend friend = story.addFriend(new Story.StoryFriend().setFriendId(friendId));
		this.upsertRecordedStory(story);
		return friend;
	}

	public Story removeFriend(String storyId, String friendId) {
		Story story = this.provider.findById(storyId);
		Optional<Story.StoryFriend> friend = story.getAttachedFriends().stream().filter(f -> f.getFriendId().equals(friendId)).findFirst();
		if (friend.isPresent()) {
			story.removeFriend(friend.get());
			this.upsertRecordedStory(story);
		}
		return story;
	}

	/**
	 * Get an individual story
	 * @param
	 * @return
	 * @throws Exception 
	 */
	
	public Story getStory(String storyId) throws Exception{
		return StringUtils.isEmpty(storyId) ? null : this.provider.findById(storyId);
	}

	/**
	 * Delete Story
	 * @param
	 */
	
	public void deleteRecordedStory(String storyId){
		this.provider.delete(storyId);
	}

}
