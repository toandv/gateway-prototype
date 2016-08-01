package com.senseinfosys.pubsense.gateway.domain.app;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRepository extends JpaRepository<Application, String> {

	Application findByIdAndSecret(String idd, String secret);

}
