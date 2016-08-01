package com.senseinfosys.pubsense.gateway.domain.app;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Application {

	public static final int SECRET_LENGTH = 32;

	@Id
	@Column(length = 50)
	private String id;

	private String secret;

	private String registerAppId;

	public Application() {
	}

	public Application(String appId, String appSecret, String registerAppId) {
		this.id = appId;
		this.secret = appSecret;
		this.registerAppId = registerAppId;
	}

	public String getId() {
		return id;
	}

	public String getSecret() {
		return secret;
	}

	public String getRegisterAppId() {
		return registerAppId;
	}

}
