package com.senseinfosys.pubsense.gateway.domain.user;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.senseinfosys.pubsense.gateway.domain.user.Role.RoleName;

@Service
public class UserInitializer {

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	@Autowired
	public UserInitializer(UserRepository userRepository, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
	}

	@Transactional
	public void populateUsersAndRoles() {
		// TODO - configure role hierarchy, ROLE_ADMIN > ROLE_USER.
		Role adminRole = new Role(RoleName.ROLE_ADMIN);
		Role userRole = new Role(RoleName.ROLE_USER);
		Collection<Role> roles = Arrays.asList(adminRole, userRole);
		roleRepository.save(roles);
		// TODO - For now passwords are raw, and should be encoded with Bcrypt
		// or something similar.
		User admin = new User("gateway_admin", "admin", Arrays.asList(adminRole));
		User user = new User("gateway_user", "user", Arrays.asList(adminRole));
		userRepository.save(Arrays.asList(admin, user));
	}

}
