package com.razor.memories.providers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.razor.memories.models.FacebookSignedRequest;
import com.restfb.FacebookClient;

public interface IFacebookProvider {
	FacebookSignedRequest retrieveSignedRequest(HttpServletRequest request, HttpServletResponse response) throws Exception;
	FacebookClient buildFacebookClient(FacebookSignedRequest facebookSR);
}
