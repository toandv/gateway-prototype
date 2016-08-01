package com.senseinfosys.pubsense.gateway.web.app;

public class AppRegisterResponse {

	private String appId;

	private String appSecret;

	private String registerAppId;

	public AppRegisterResponse(String appId, String appSecret, String registerAppId) {
		this.appId = appId;
		this.appSecret = appSecret;
		this.registerAppId = registerAppId;
	}

	public AppRegisterResponse() {
	}

	public String getAppId() {
		return appId;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public String getRegisterAppId() {
		return registerAppId;
	}

}
