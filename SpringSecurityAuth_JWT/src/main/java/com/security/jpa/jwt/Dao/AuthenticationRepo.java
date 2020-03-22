package com.security.jpa.jwt.Dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.security.jpa.jwt.entity.Authentication;

public interface AuthenticationRepo extends CrudRepository<Authentication, Integer> {
	
	public Optional<Authentication> findByEmail(String email);

}
