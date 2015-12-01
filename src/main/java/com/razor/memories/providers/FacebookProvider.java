package com.razor.memories.providers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.razor.memories.models.FacebookSignedRequest;
import com.razor.memories.wrappers.FBSR;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Version;

public class FacebookProvider implements IFacebookProvider {

	public FacebookSignedRequest retrieveSignedRequest(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		FacebookSignedRequest facebookSR = null;
		
		// initial facebook request comes in as a POST with a signed_request		
		String signedRequest = (String) request.getParameter("signed_request");
		if(signedRequest!=null){
			
			// build the request
			facebookSR = FacebookSignedRequest.getFacebookSignedRequest(signedRequest);
			// set the cookie
			FBSR.addFacebookCookie(response, signedRequest);
		}

		// if still null try and load from a cookie
		if(facebookSR == null){
			// get any stored cookie
			Cookie authenticationCookie = FBSR.getFacebookCookie(request);	
			if(authenticationCookie!=null){
				facebookSR = FacebookSignedRequest.getFacebookSignedRequest(authenticationCookie.getValue());
			}
		}	
		
		return facebookSR;
	}
	
	public FacebookClient buildFacebookClient(FacebookSignedRequest facebookSR){
		String oauthToken = facebookSR == null ? null : facebookSR.getOauth_token();
		return (oauthToken == null) ? null : new DefaultFacebookClient(oauthToken, Version.VERSION_2_0);
	}
	
}
