package com.techgeeknext.security.google.oauth2.repository;

import com.techgeeknext.security.google.oauth2.model.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, String> {

	Users findByUsername(String username);
	Users findByEmail(String email);
	
}
