package com.senseinfosys.pubsense.gateway.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserRepository extends JpaRepository<User, Long> {
	public User findByUsername(String username);

	public User findByUsernameAndPassword(String username, String password);
}
