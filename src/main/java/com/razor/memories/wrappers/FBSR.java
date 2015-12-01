package com.razor.memories.wrappers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.razor.memories.models.Constants;

public class FBSR {

	private static final String AuthenticationCookieName = "RazorMemoriesAuthenticationCookie";
	
	/*
	public static FacebookSignedRequest retrieveSignedRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		FacebookSignedRequest facebookSR = null;
		
		// initial facebook request comes in as a POST with a signed_request		
		String signedRequest = (String) request.getParameter("signed_request");
		if(signedRequest!=null){
			
			// build the request
			facebookSR = FacebookSignedRequest.getFacebookSignedRequest(signedRequest);
			// set the cookie
			addFacebookCookie(response, signedRequest);
		}

		// if still null try and load from a cookie
		if(facebookSR == null){
			// get any stored cookie
			Cookie authenticationCookie = getFacebookCookie(request);	
			if(authenticationCookie!=null){
				facebookSR = FacebookSignedRequest.getFacebookSignedRequest(authenticationCookie.getValue());
			}
		}	
		
		return facebookSR;
	}
	
	public static FacebookClient buildFacebookClient(FacebookSignedRequest facebookSR){
		String oauthToken = facebookSR == null ? null : facebookSR.getOauth_token();
		return (oauthToken == null) ? null : new DefaultFacebookClient(oauthToken);		
	}
	*/
	
	public static void addFacebookCookie(HttpServletResponse response, String signedRequest){
        Cookie cookie1 = new Cookie(AuthenticationCookieName, signedRequest);
        cookie1.setMaxAge(-1); // delete when they close browser 1*60*60);
        response.addCookie(cookie1); 		
	}
	
	public static Cookie getFacebookCookie(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for(int i = 0; i < cookies.length; i++) { 
            Cookie cookie1 = cookies[i];
            if (cookie1.getName().equals(AuthenticationCookieName)) {
                return cookie1;
            }
        }		
        return null;
	}
	
	public static void buildRedirectScript(HttpServletResponse response) throws IOException{
		response.setContentType("text/html");
		String authURL = "https://www.facebook.com/dialog/oauth?client_id="
							+ Constants.API_KEY
							+ "&redirect_uri=https://apps.facebook.com/razor_memories/&scope=";
		
		PrintWriter writer = response.getWriter();
		writer.print("<script> top.location.href='"	+ authURL + "'</script>");
		writer.close();		
	}
	
}
