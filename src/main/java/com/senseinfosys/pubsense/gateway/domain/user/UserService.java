package com.senseinfosys.pubsense.gateway.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public User findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User: " + username + " not found.");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				user.rolesToAuthorities());
	}
}
