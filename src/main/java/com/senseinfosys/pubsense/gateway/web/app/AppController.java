package com.senseinfosys.pubsense.gateway.web.app;

import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senseinfosys.pubsense.gateway.domain.app.AppException;
import com.senseinfosys.pubsense.gateway.domain.app.AppService;
import com.senseinfosys.pubsense.gateway.domain.app.Application;
import com.senseinfosys.pubsense.gateway.infrastructure.security.JwtResponse;
import com.senseinfosys.pubsense.gateway.infrastructure.security.TokenService;

@RestController
@RequestMapping("/v1/api")
@CrossOrigin
public class AppController {

	AppService appService;

	TokenService tokenService;

	@Autowired
	public AppController(AppService appService, TokenService tokenService) {
		this.appService = appService;
		this.tokenService = tokenService;
	}

	@PostMapping(path = "/apps/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public Callable<AppRegisterResponse> registerApp(@RequestBody AppRegisterRequest appRequest) {
		return () -> {
			Application app = appService.createApp(appRequest.getRegisterAppId());
			return new AppRegisterResponse(app.getId(), app.getSecret(), app.getRegisterAppId());
		};
	}

	@GetMapping(path = "/apps")
	public List<Application> listApps() {
		return appService.findAll();
	}

	@PostMapping(path = "/apps/token")
	public JwtResponse getAccessToken() {
		String appId = SecurityContextHolder.getContext().getAuthentication().getName();
		return tokenService.getJwtResponse(appId);
	}
}
