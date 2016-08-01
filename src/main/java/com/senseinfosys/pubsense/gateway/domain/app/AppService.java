package com.senseinfosys.pubsense.gateway.domain.app;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.senseinfosys.pubsense.gateway.infrastructure.id.IdFactory;
import com.senseinfosys.pubsense.gateway.infrastructure.id.SecretFactory;

@Service
public class AppService {

	private AppRepository appRepository;

	@Autowired
	public AppService(AppRepository appRepository) {
		this.appRepository = appRepository;
	}

	@Transactional(readOnly = true)
	public List<Application> findAll() {
		return appRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Application find(String appId, String appSecret) {
		Application app = appRepository.findByIdAndSecret(appId, appSecret);
		if (app == null) {
			throw new AppException("Not found.");
		}
		return app;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED)
	public Application createApp(String registerAppId) {
		Application app = new Application(IdFactory.getUUID(), SecretFactory.getSecret(Application.SECRET_LENGTH),
				registerAppId);
		return appRepository.save(app);
	}

}
