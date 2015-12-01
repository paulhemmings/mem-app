package com.razor.memories.models;

import java.util.Date;

import com.google.gson.Gson;

public class RequestResponse {

	private String errorMessage;
	private String responseHtml;
	private String responseDate;
	private String requestLogInfo;

	public RequestResponse(){		
		this.setResponseDate( new Date().toString());
	}
	
	public RequestResponse(String errorMessage, String responseHtml){
		this();
		this.errorMessage = errorMessage;
		this.responseHtml = responseHtml;
	}

	public String toJson(){ 
		String json = new Gson().toJson(this);
		return json;
	}
	
	public String getResponseHtml() {
		return responseHtml;
	}

	public void setResponseHtml(String responseHtml) {
		this.responseHtml = responseHtml;
	}

	public boolean isSuccess() {
		return errorMessage==null || errorMessage.isEmpty();
	}

	public void setMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setResponseDate(String responseDate) {
		this.responseDate = responseDate;
	}

	public String getResponseDate() {
		return responseDate;
	}

	public void setRequestLogInfo(String requestLogInfo) {
		this.requestLogInfo = requestLogInfo;
	}

	public String getRequestLogInfo() {
		return requestLogInfo;
	}
	
	
}
