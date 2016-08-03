package com.senseinfosys.pubsense.gateway.infrastructure.security;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Configuration
//@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/v1/api/apps/token").allowCredentials(false).maxAge(3600);
	}

}
