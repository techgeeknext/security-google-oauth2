package com.techgeeknext.security.google.oauth2.service;

import com.techgeeknext.security.google.oauth2.model.Users;
import com.techgeeknext.security.google.oauth2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserRegistrationServiceImpl implements UserRegistrationService{
	@Autowired
	private UserRepository userRepository;

	@Override
	public void createUser(Users user) {
		
		userRepository.save(user);
		
		System.out.println("***** User Saved  *****");
	}

	

}