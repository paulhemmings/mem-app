package com.razor.memories.models;

import spark.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

/*
 * The Story document.
 */

public class Story extends MongoModel {

	private String creatorId;
	private String text;
	private int creatorPrivacyLevel;
	private List<StoryFriend> attachedFriends;
	private List<StoryComment> attachedComments;

	public StoryFriend addFriend(StoryFriend storyFriend) {
		if (attachedFriends == null) {
			this.attachedFriends = new ArrayList<>();
		}
		if (StringUtils.isEmpty(storyFriend.getId())) {
			storyFriend.setId(storyFriend.newId());
		}
		this.attachedFriends.add(storyFriend);
		return storyFriend;
	}

	public Story removeFriend(StoryFriend storyFriend) {
		this.attachedFriends.remove(storyFriend);
		return this;
	}

	public StoryComment addComment(StoryComment storyComment) {
		if (attachedComments == null) {
			this.attachedComments = new ArrayList<>();
		}
		if (StringUtils.isEmpty(storyComment.getId())) {
			storyComment.setId(storyComment.newId());
		}
		this.attachedComments.add(storyComment);
		return storyComment;
	}

	public Story removeComment(StoryComment comment) {
		this.attachedComments.remove(comment);
		return this;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getText() {
		return text;
	}

	public Story setText(String text) {
		this.text = text;
		return this;
	}

	public int getCreatorPrivacyLevel() {
		return creatorPrivacyLevel;
	}

	public void setCreatorPrivacyLevel(int creatorPrivacyLevel) {
		this.creatorPrivacyLevel = creatorPrivacyLevel;
	}

	public List<StoryFriend> getAttachedFriends() {
		return attachedFriends;
	}

	public List<StoryComment> getAttachedComments() {
		return attachedComments;
	}

	/*
	 * Friends associated with this story
	 */

	public static class StoryFriend extends MongoModel {
		private String friendId;

		public String getFriendId() {
			return friendId;
		}

		public StoryFriend setFriendId(String friendId) {
			this.friendId = friendId;
			return this;
		}
	}

	/*
	 * Comments associated with this story
	 */

	public static class StoryComment extends MongoModel {
		private String friendId;
		private String comment;

		public String getFriendId() {
			return friendId;
		}

		public StoryComment setFriendId(String friendId) {
			this.friendId = friendId;
			return this;
		}

		public String getComment() {
			return comment;
		}

		public StoryComment setComment(String comment) {
			this.comment = comment;
			return this;
		}
	}

}
