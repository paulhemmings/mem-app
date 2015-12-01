package com.razor.memories.models;

import java.io.Serializable;

public class StoryFriend implements Serializable {

	private long friendId;
	private int friendPrivacyLevel;
	private String storyKey;
	private long storyCreatorId;
	private Story attachedStory;
	
	/**
	 * Empty Constructor
	 */
	
	public StoryFriend(){		
	}

	/**
	 * Constructor
	 * @param friendId
	 * @param friendPrivacyLevel
	 */
	
	public StoryFriend(long friendId, int friendPrivacyLevel) {
		super();
		this.friendId = friendId;
		this.friendPrivacyLevel = friendPrivacyLevel;
	}

	public long getFriendId() {
		return friendId;
	}

	public void setFriendId(long friendId) {
		this.friendId = friendId;
	}

	public int getFriendPrivacyLevel() {
		return friendPrivacyLevel;
	}

	public void setFriendPrivacyLevel(int friendPrivacyLevel) {
		this.friendPrivacyLevel = friendPrivacyLevel;
	}

	public String getStoryKey() {
		return storyKey;
	}

	public void setStoryKey(String storyKey) {
		this.storyKey = storyKey;
	}

	public long getStoryCreatorId() {
		return storyCreatorId;
	}

	public void setStoryCreatorId(long storyCreatorId) {
		this.storyCreatorId = storyCreatorId;
	}

	public Story getAttachedStory() {
		return attachedStory;
	}

	public void setAttachedStory(Story attachedStory) {
		this.attachedStory = attachedStory;
	}
}
