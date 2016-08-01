package com.senseinfosys.pubsense.gateway.web.app;

public class AppAuthTokenRequest {

	private String appId;

	private String appSecret;

	public AppAuthTokenRequest(String appId, String appSecret) {
		this.appId = appId;
		this.appSecret = appSecret;
	}

	public AppAuthTokenRequest() {
	}

	public String getAppId() {
		return appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

}
