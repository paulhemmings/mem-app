package com.razor.memories.factories;

import com.razor.memories.providers.FacebookProvider;
import com.razor.memories.providers.FacebookTestProvider;
import com.razor.memories.providers.IFacebookProvider;

public class FacebookProviderFactory {

	public static IFacebookProvider buildProvider(boolean useTest) {
		return useTest ? new FacebookTestProvider() : new FacebookProvider();
	}
}
