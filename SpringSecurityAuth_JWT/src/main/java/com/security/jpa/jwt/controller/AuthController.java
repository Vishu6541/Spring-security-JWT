package com.security.jpa.jwt.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.jpa.jwt.Dao.AuthenticationRepo;
import com.security.jpa.jwt.entity.Authentication;

@RestController
@RequestMapping("/auths")
public class AuthController {
	
	@Autowired
	AuthenticationRepo authenticationRepo;
	
	@PostMapping("/")
	public String save(@RequestBody Authentication auth) {
		authenticationRepo.save(auth);
		return "save";
	}
	
	@GetMapping("/")
	public Iterable<Authentication>getAllUser(){
		return authenticationRepo.findAll();
	}
	
	@GetMapping("/{email}")
	public Authentication getByEmail(@PathVariable String email) {
		Optional<Authentication> opt =authenticationRepo.findByEmail(email);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			return null;
		}
	}
	
	

}
