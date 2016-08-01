package com.senseinfosys.pubsense.gateway;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.senseinfosys.pubsense.gateway.domain.user.UserInitializer;

@SpringBootApplication
public class PubsenseGatewayApplication {

	public static void main(String... args) {
		SpringApplication.run(PubsenseGatewayApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserInitializer userInitializer) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				userInitializer.populateUsersAndRoles();
			}
		};

	}
}
