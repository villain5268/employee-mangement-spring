package com.boldnest.em.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.boldnest.em.dto.UserRegistrationDto;
import com.boldnest.em.entity.User;

@Service
public interface UserService extends UserDetailsService {
	User save(UserRegistrationDto registrationDto);

	void updateUserProfile(String email, User updatedUser);

	User findUserByEmail(String name);
}
