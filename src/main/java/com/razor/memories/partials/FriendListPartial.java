package com.razor.memories.partials;

import java.util.List;

import com.restfb.Connection;
import com.restfb.types.User;

public class FriendListPartial {
	
	public String build(Connection<User> myFriends){		
		
		StringBuilder builder = new StringBuilder();
		
		builder.append("<div class=\"friendSelectionContainer\" >");
		for (List<User> myFriendsList : myFriends) {

			for(User user: myFriendsList){
				
				String selectionHtml = String.format("<input class=\"friendSelector\" type=\"checkbox\" value=\"%s\" data-name=\"%s\" />", user.getId(),user.getName());
				
				builder.append(
						"<div class=\"friendItem\" >" 
						+ "<img class=\"friendImage\" src=\"https://graph.facebook.com/" + user.getId() + "/picture\"/>" 
						+ "<div class=\"friendName\">" + user.getName() + "</div>"
						+ "<div class=\"friendSeletorBlock\" >" + selectionHtml + "</div>"
						+ "</div>");
			}
		}
		builder.append("</div>");		
		
		return builder.toString();
	}	

}
