package com.security.jpa.jwt.jwtkit;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class SecurityContextToken extends UsernamePasswordAuthenticationToken{

	
	private String mailid;
	
	
	
	public SecurityContextToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities ,String mailid) {
		super(principal, credentials, authorities );
		this.mailid=mailid;
		
	}


	public SecurityContextToken(Object principal, Object credentials ,String mailid) {
		super(principal, credentials);
		this.mailid =mailid;
		
	}


	public String getMailid() {
		return mailid;
	}


	public void setMailid(String mailid) {
		this.mailid = mailid;
	}
	
	

}
