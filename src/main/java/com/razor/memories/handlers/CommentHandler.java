package com.razor.memories.handlers;

import com.razor.memories.models.Story;
import com.razor.memories.models.StoryComment;
import com.razor.memories.wrappers.LogWrapper;

public class CommentHandler {

	/**
	 * Add a story comment
	 * @param storyId
	 * @param creatorId
	 * @param comment
	 * @throws Exception 
	 */
	
	public StoryComment addStoryComment(String storyId, long creatorId, String comment) throws Exception{
		StoryHandler handler = new StoryHandler();
		StoryComment storyComment = null;		
		Story existingStory = handler.getStory(storyId);
		if(existingStory!=null){
			storyComment = new StoryComment(existingStory, creatorId, comment);
			existingStory.getAttachedComments().add(storyComment);
			handler.upsertRecordedStory(existingStory);
		}
		return storyComment;
	}
	
	public StoryComment addStoryComment(StoryComment comment) throws Exception{
		StoryHandler handler = new StoryHandler();		
		Story existingStory = handler.getStory(comment.getCommentStory().getStoryKey());
		existingStory.getAttachedComments().add(comment);
		handler.upsertRecordedStory(existingStory);
		return comment;
	}
	
	/**
	 * Get an individual comment
	 * @param
	 * @return
	 */
	
	public StoryComment getCommentFromId(String commentId){
		StoryComment existingComment = null;
		if(commentId!=null&&commentId.trim().length()>0){
			String storyKey = commentId;
			existingComment = this.getComment(storyKey);
		}
		return existingComment;
	}	
	
	public StoryComment getComment(String storyKey){
	    StoryComment recordedComment, detached = null;
	    try {
	    	recordedComment = null;// pm.getObjectById(StoryComment.class,storyKey);
	        detached = null;//pm.detachCopy(recordedComment);
	    } catch(Exception ex){			
			LogWrapper.e("CommentHandler.getComment",ex.getMessage());
        } finally {
//	        pm.close();
	    }
	    return detached;		
	}	
	
	/**
	 * Delete Story
	 * @param
	 */
	
	public void deleteComment(String commentId){
		if(commentId!=null&&commentId.trim().length()>0){
			try {
				String storyCommentString = commentId;
//				pm.deletePersistent(storyCommentKey);
		    } catch(Exception ex){			
				LogWrapper.e("CommentHandler.deleteComment",ex.getMessage());
	        } finally {
//		        pm.close();
		    }
		}
	}	
	
}
