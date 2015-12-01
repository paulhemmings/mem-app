package com.razor.memories.providers;

import com.razor.memories.models.FacebookSignedRequest;
import com.restfb.*;
import com.restfb.batch.BatchRequest;
import com.restfb.batch.BatchResponse;
import com.restfb.exception.devicetoken.FacebookDeviceTokenCodeExpiredException;
import com.restfb.exception.devicetoken.FacebookDeviceTokenDeclinedException;
import com.restfb.exception.devicetoken.FacebookDeviceTokenPendingException;
import com.restfb.exception.devicetoken.FacebookDeviceTokenSlowdownException;
import com.restfb.scope.ScopeBuilder;
import com.restfb.types.DeviceCode;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public class FacebookTestProvider implements IFacebookProvider {

	public static class TestFacebookClient implements FacebookClient{

		@Override
		public <T> T fetchObject(String object, Class<T> objectType, Parameter... parameters) {
			return null;
		}

		@Override
		public <T> T fetchObjects(List<String> ids, Class<T> objectType, Parameter... parameters) {
			return null;
		}

		@Override
		public <T> Connection<T> fetchConnection(String connection, Class<T> connectionType, Parameter... parameters) {
			return null;
		}

		@Override
		public <T> Connection<T> fetchConnectionPage(String connectionPageUrl, Class<T> connectionType) {
			return null;
		}

		@Override
		public <T> List<T> executeQuery(String query, Class<T> objectType, Parameter... parameters) {
			return null;
		}

		@Override
		public <T> T executeMultiquery(Map<String, String> queries, Class<T> objectType, Parameter... parameters) {
			return null;
		}

		@Override
		public <T> List<T> executeFqlQuery(String query, Class<T> objectType, Parameter... parameters) {
			return null;
		}

		@Override
		public <T> T executeFqlMultiquery(Map<String, String> queries, Class<T> objectType, Parameter... parameters) {
			return null;
		}

		@Override
		public List<BatchResponse> executeBatch(BatchRequest... batchRequests) {
			return null;
		}

		@Override
		public List<BatchResponse> executeBatch(List<BatchRequest> batchRequests) {
			return null;
		}

		@Override
		public List<BatchResponse> executeBatch(List<BatchRequest> batchRequests, List<BinaryAttachment> binaryAttachments) {
			return null;
		}

		@Override
		public <T> T publish(String connection, Class<T> objectType, Parameter... parameters) {
			return null;
		}

		@Override
		public <T> T publish(String connection, Class<T> objectType, List<BinaryAttachment> binaryAttachments, Parameter... parameters) {
			return null;
		}

		@Override
		public <T> T publish(String connection, Class<T> objectType, BinaryAttachment binaryAttachment, Parameter... parameters) {
			return null;
		}

		@Override
		public boolean deleteObject(String object, Parameter... parameters) {
			return false;
		}

		@Override
		public List<AccessToken> convertSessionKeysToAccessTokens(String appId, String secretKey, String... sessionKeys) {
			return null;
		}

		@Override
		public AccessToken obtainUserAccessToken(String appId, String appSecret, String redirectUri, String verificationCode) {
			return null;
		}

		@Override
		public AccessToken obtainAppAccessToken(String appId, String appSecret) {
			return null;
		}

		@Override
		public AccessToken obtainExtendedAccessToken(String appId, String appSecret, String accessToken) {
			return null;
		}

		@Override
		public String obtainAppSecretProof(String accessToken, String appSecret) {
			return null;
		}

		@Override
		public AccessToken obtainExtendedAccessToken(String appId, String appSecret) {
			return null;
		}

		@Override
		public <T> T parseSignedRequest(String signedRequest, String appSecret, Class<T> objectType) {
			return null;
		}

		@Override
		public DeviceCode fetchDeviceCode(String appId, ScopeBuilder scope) {
			return null;
		}

		@Override
		public AccessToken obtainDeviceAccessToken(String appId, String code) throws FacebookDeviceTokenCodeExpiredException, FacebookDeviceTokenPendingException, FacebookDeviceTokenDeclinedException, FacebookDeviceTokenSlowdownException {
			return null;
		}

		@Override
		public DebugTokenInfo debugToken(String inputToken) {
			return null;
		}

		@Override
		public JsonMapper getJsonMapper() {
			return null;
		}

		@Override
		public WebRequestor getWebRequestor() {
			return null;
		}

		@Override
		public String getLogoutUrl(String next) {
			return null;
		}

		@Override
		public String getLoginDialogUrl(String appId, String redirectUri, ScopeBuilder scope) {
			return null;
		}
	}

	public static final Long[] TEST_CREATOR_ID = { Long.valueOf(1010101), Long.valueOf(1010100) };


	@Override
	public FacebookSignedRequest retrieveSignedRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		FacebookSignedRequest fsr = new FacebookSignedRequest();
		fsr.originalSignedRequest = "this is the test signed request";

		String userIndex = request.getParameter("userIndex");
		if(userIndex!=null && !userIndex.isEmpty()){
			int index = Integer.parseInt(userIndex);
			fsr.setUser_id(Long.valueOf(TEST_CREATOR_ID[index]));
		}else{
			fsr.setUser_id(Long.valueOf(TEST_CREATOR_ID[1]));
		}
		return fsr;
	}

	@Override
	public FacebookClient buildFacebookClient(FacebookSignedRequest facebookSR) {
		return new TestFacebookClient();
	}

}
