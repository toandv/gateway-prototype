package com.senseinfosys.pubsense.gateway.infrastructure.security;

import java.util.Date;

import org.joda.time.DateTimeConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.senseinfosys.pubsense.gateway.domain.app.Application;
import com.senseinfosys.pubsense.gateway.infrastructure.json.JsonService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	private JsonService jsonService;

	private String secret;

	private long expiration;

	@Autowired
	public TokenService(JsonService jsonService, Environment evn) {
		this.jsonService = jsonService;
		this.secret = evn.getProperty("security.jwt.secret");
		this.expiration = Integer.parseInt(evn.getProperty("security.jwt.expiration"))
				* DateTimeConstants.MILLIS_PER_HOUR;
	}

	public JwtResponse getJwtResponse(Application app) {
		Date issuedAt = new Date();
		Date expiredAt = new Date(issuedAt.getTime() + expiration);
		String token = Jwts.builder().setSubject(jsonService.serialize(new JwtSubject(app.getId())))
				.signWith(SignatureAlgorithm.HS512, secret).setIssuedAt(issuedAt).setExpiration(expiredAt).compact();
		return new JwtResponse(token, expiredAt);
	}

	public JwtSubject validateToken(String token) {
		try {
			String json = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
			return jsonService.deserialize(json, JwtSubject.class);
		} catch (Exception e) {
			throw new TokenException(e.getMessage(), e);
		}
	}
}
