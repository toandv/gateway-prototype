package com.senseinfosys.pubsense.gateway.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.senseinfosys.pubsense.gateway.domain.app.AppService;
import com.senseinfosys.pubsense.gateway.domain.user.UserService;
import com.senseinfosys.pubsense.gateway.infrastructure.json.JsonService;

@Configuration
@EnableWebSecurity(debug = true)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private AppService appService;
	
	@Autowired
	JsonService jsonService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.anyRequest().fullyAuthenticated()
			.and().httpBasic().disable()
			.csrf().disable()
			.addFilterAfter(new StatelessTokenFilter(tokenService, userService, appService, jsonService),
						BasicAuthenticationFilter.class);
	}

}
