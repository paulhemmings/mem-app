package com.razor.memories.factories;

import com.razor.memories.providers.FacebookProvider;
import com.razor.memories.providers.IFacebookProvider;

public class FacebookProviderFactory {

	public static IFacebookProvider buildProvider(){
		return new FacebookProvider();
	}
}
