package com.razor.memories.models;

import java.util.Date;

public class StoryComment {

    private String key;
	private Date dateCreated;
	private long creatorId;
	private Story commentStory;
	private String text;
	
	public StoryComment(){		
	}
	
	public StoryComment(Story commentStory, long creatorId, String comment){
		this.creatorId = creatorId;
		this.commentStory = commentStory;
		this.text = comment;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(long creatorId) {
		this.creatorId = creatorId;
	}

	public Story getCommentStory() {
		return commentStory;
	}

	public void setCommentStory(Story commentStory) {
		this.commentStory = commentStory;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
