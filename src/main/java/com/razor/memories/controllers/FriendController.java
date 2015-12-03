package com.razor.memories.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;

import com.razor.memories.models.RequestResponse;
import com.razor.memories.viewmodels.FriendMap;
import com.razor.memories.wrappers.TemplateHelper;
import com.restfb.Connection;
import com.restfb.types.User;

public class FriendController extends Controller {

    public static final String ctrlName = "/friend";

    public void list() throws IOException, ServletException{
    	RequestResponse requestResponse = new RequestResponse();
    	try{
	    	List<Map<String,Object>> friendListMap = null;

	    	if(this.runningLocally){
	    		friendListMap = new FriendMap().buildFromTestList(this.facebookSR.getUser_id());
	    	}else{
	    		Connection<User> myFriends = facebookClient.fetchConnection("me/friends", User.class);
	    		List<User> friendList = myFriends.getData();
	    		friendListMap = new FriendMap().buildFromUserList(friendList);
	    	}

	        Map<String, Object> root = new HashMap<String, Object>();
	        root.put("friends",friendListMap);

	        String responseHtml = TemplateHelper.callTemplate(configuration, ctrlName + "/list.ftl",root);
	        requestResponse.setResponseHtml(responseHtml);

    	}catch(Exception ex){
    		requestResponse.setMessage("listShared failed with: " + ex.getMessage());
    	}
		this.response.setContentType("text/json; charset=utf-8");
		this.response.getWriter().println(requestResponse.toJson());
    }
}
