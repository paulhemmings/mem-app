package com.razor.memories.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Story implements Serializable {
	
	private static final long serialVersionUID = 6899284258473985914L;

	private String key;
	private String text;
	private Date dateOccurred;
	private long creatorId;
	private int creatorPrivacyLevel;
	private List<StoryFriend> attachedFriends;
	private List<StoryComment> attachedComments;
	private Date dateCreated;
	private boolean isDeleted;
	
	/**
	 * Empty Constructor
	 */
	
	public Story(){		
		this.attachedFriends = new ArrayList<StoryFriend>();
		this.attachedComments = new ArrayList<StoryComment>();
		this.isDeleted = false;
	}
	
	/**
	 * Constructor
	 * @param text
	 * @param creatorId
	 * @param creatorPrivacyLevel
	 * @param attachedFriends
	 * @param dateCreated
	 */

	public Story(String text, int creatorId, int creatorPrivacyLevel,List<StoryFriend> attachedFriends, Date dateCreated) {
		super();
		this.text = text;
		this.creatorId = creatorId;
		this.creatorPrivacyLevel = creatorPrivacyLevel;
		this.attachedFriends = attachedFriends;
		this.dateCreated = dateCreated;
		this.isDeleted = false;
	}

	public String getText() {
		return text==null?"":text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public int getCreatorPrivacyLevel() {
		return creatorPrivacyLevel;
	}

	public void setCreatorPrivacyLevel(int creatorPrivacyLevel) {
		this.creatorPrivacyLevel = creatorPrivacyLevel;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public List<StoryFriend> getAttachedFriends() {
		return attachedFriends;
	}

	public void setAttachedFriends(List<StoryFriend> attachedFriends) {
		this.attachedFriends = attachedFriends;
	}

	public String getStoryKey() {
		return key;
	}
	
	public void setStoryKey(String key) {
		this.key = key;
	}

	public Date getDateOccured() {
		return dateOccurred;
	}

	public void setDateOccured(Date dateOccured) {
		this.dateOccurred = dateOccured;
	}

	public List<StoryComment> getAttachedComments() {
		return attachedComments;
	}

	public void setAttachedComments(List<StoryComment> attachedComments) {
		this.attachedComments = attachedComments;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}


}
