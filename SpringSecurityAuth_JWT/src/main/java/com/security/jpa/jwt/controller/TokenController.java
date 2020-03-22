package com.security.jpa.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.jpa.jwt.Dao.AuthenticationRepo;
import com.security.jpa.jwt.jwtkit.JWTUtil;
import com.security.jpa.jwt.jwtkit.UserDetail;

@RestController
@RequestMapping("/tokens")
public class TokenController {
	
	
	@Autowired
	AuthenticationRepo AuthenticationRepo;

	@PostMapping("/")
	public String getTokenString(@RequestBody UserDetail userDetail) {
		try {
			return JWTUtil.getJWtToken(userDetail,AuthenticationRepo);
		}catch (Exception e) {
			e.printStackTrace();
			return "Invalid";
		}
		
	}
	
}
