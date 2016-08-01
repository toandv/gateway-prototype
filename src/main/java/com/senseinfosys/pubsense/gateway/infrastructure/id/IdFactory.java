package com.senseinfosys.pubsense.gateway.infrastructure.id;

import java.util.UUID;

public class IdFactory {

	public static final String getUUID() {
		return UUID.randomUUID().toString();
	}
}
