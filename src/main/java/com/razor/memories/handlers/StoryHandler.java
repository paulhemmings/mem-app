package com.razor.memories.handlers;

import com.razor.memories.models.Story;
import com.razor.memories.models.StoryFriend;
import com.razor.memories.wrappers.LogWrapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Recorded Story Handler 
 * https://developers.google.com/appengine/docs/java/datastore/jdo/queries
 * @author paulhemmings
 */

public class StoryHandler {
	
	public static final String COMPLETED_SUCCESSFULLY = "Success";
	
	/**
	 * Get all storyFriend rows where you are the friend, thus getting
	 * all the stories that are shared with you.
	 * @param friendId
	 * @return
	 * @throws Exception 
	 */
	
	public List<Story> listSharedStories(long friendId) throws Exception{

//		Query q = pm.newQuery(StoryFriend.class,"friendId == friendIdParam");
//		q.declareParameters("long friendIdParam");
		
		Map<String, Story> results = new HashMap<String, Story>();
		try{
			List<StoryFriend> temp = null; // (List<StoryFriend>) q.execute(friendId);
			for(StoryFriend tmp:temp){				
				Story sharedStory = tmp.getAttachedStory();
				if(sharedStory!=null && sharedStory!=null){
					if(!results.containsKey(sharedStory)){
						results.put(sharedStory.getStoryKey(), sharedStory);
					}
				}
			}			
		}catch(Exception ex){			
			LogWrapper.e("StoryHandler.listSharedStories",ex.getMessage());
			throw(new Exception("StoryHandler.listSharedStories failed with: " + ex.getMessage()));
		}finally{
//			q.closeAll();
//			pm.close();
		}
		return new ArrayList<Story>( results.values() );
		
	}
		
	/**
	 * Get all the users Stories
	 * http://stackoverflow.com/questions/5417856/google-app-engine-not-saving-a-list-of-objects-in-a-class
	 * @param creatorId
	 * @return
	 */
	
	public List<Story> listStoriesByCreatorId(long creatorId){
		
//		PersistenceManager pm = PMF.get().getPersistenceManager();
//		Query q = pm.newQuery(Story.class,"creatorId == creatorIdParam");
//		q.declareParameters("long creatorIdParam");
//		q.setOrdering("dateCreated descending");
		
		List<Story> results = new ArrayList<Story>();
		try{
			List<Story> temp = null; // (List<Story>) q.execute(creatorId);
			for(Story tmp:temp){
				try{
					tmp.getAttachedFriends();
					tmp.getAttachedComments();
				}catch(Exception ignore){}
				results.add(tmp);
			}
		}catch(Exception ex){			
			LogWrapper.e("RecordedStoryHandler.listStoriesByCreatorId",ex.getMessage());
		}finally{
//			q.closeAll();
//			pm.close();
		}
		return results;
	}
		
	/**
	 * Upsert Story
	 * @param recordedStory
	 */
	
	public String upsertRecordedStory(Story recordedStory){		
		String message = "";
//        PersistenceManager pm = PMF.get().getPersistenceManager();
//        Transaction tx = pm.currentTransaction();
        try {
//        	tx.begin();
//            pm.makePersistent(recordedStory);
//            tx.commit();
        } catch(Exception ex){			
			LogWrapper.e("RecordedStoryHandler.upsertRecordedStory",ex.getMessage());
			message = ex.getMessage();
        } finally {
//        	if (tx.isActive()) {
//                tx.rollback();
//            }        	
//            pm.close();
        }
        return message;
	}
	
	/**
	 * Get an individual story
	 * @param
	 * @return
	 * @throws Exception 
	 */
	
	public Story getStoryFromId(String storyId) throws Exception{
		Story existingStory = null;
		if(storyId!=null&&storyId.trim().length()>0){
			String storyKey = null; // StringFactory.createString("Story", Long.valueOf(storyId));
			existingStory = this.getStory(storyKey);
		}
		return existingStory;
	}
	
	public Story getStory(String storyKey) throws Exception{
//	    PersistenceManager pm = PMF.get().getPersistenceManager();
	    Story recordedStory, detached = null;
	    try {
	    	recordedStory = null; // pm.getObjectById(Story.class,storyString);
	    	recordedStory.getAttachedFriends();
	    	recordedStory.getAttachedComments();
//	        detached = pm.detachCopy(recordedStory);
	    } catch(Exception ex){			
			LogWrapper.e("RecordedStoryHandler.getRecordedStory",ex.getMessage());
			//throw(new Exception("RecordedStoryHandler.getRecordedStory failed with: " + ex.getMessage()));
        } finally {
//	        pm.close();
	    }
	    return detached;		
	}
	


	/**
	 * Delete Story
	 * @param
	 */
	
	public void deleteRecordedStory(String storyId){
		if(storyId!=null&&storyId.trim().length()>0){
//			PersistenceManager pm = PMF.get().getPersistenceManager();
			try {
				String storyString = null; //  StringFactory.createString("Story", Long.valueOf(storyId));
				Story story = null; // pm.getObjectById(Story.class,storyString);
				if(story!=null){
					story.setDeleted(true);
					// pm.deletePersistent(story.getStoryString());
//					pm.makePersistent(story);
				}
		    } catch(Exception ex){			
				LogWrapper.e("RecordedStoryHandler.deleteRecordedStory",ex.getMessage());
	        } finally {
//		        pm.close();
		    }
		}
	}
	
	

}
