package com.senseinfosys.pubsense.gateway.domain.user;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.senseinfosys.pubsense.gateway.domain.user.Role.RoleName;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(unique = true)
	private String username;

	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = {
			@JoinColumn(name = "user_id", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "role_id", nullable = false, updatable = false) })
	private Collection<Role> roles;

	public User() {
	}

	public User(String username, String password, Collection<Role> roles) {
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	public Collection<GrantedAuthority> rolesToAuthorities() {
		Collection<Role> roles = getRoles();
		if (roles == null) {
			return Collections.emptyList();
		}
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName().toString()))
				.collect(Collectors.toSet());
	}

	public Collection<RoleName> roleNames() {
		if (roles == null) {
			return Collections.emptyList();
		}
		return roles.stream().map(r -> r.getName()).collect(Collectors.toList());
	}
}
