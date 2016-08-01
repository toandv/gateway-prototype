package com.senseinfosys.pubsense.gateway.infrastructure.json;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class JsonService {
	@Autowired
	ObjectMapper objectMapper;

	public String serialize(Object value) {
		try {
			return objectMapper.writeValueAsString(value);
		} catch (JsonProcessingException e) {
			throw new JsonException(e.getMessage(), e);
		}
	}

	public <T> T deserialize(String json, Class<T> type) {
		try {
			return objectMapper.readValue(json, type);
		} catch (IOException e) {
			throw new JsonException(e.getMessage(), e);
		}
	}
}
