package com.security.jpa.jwt.jwtkit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class JWTAuthenticationFilter extends OncePerRequestFilter{
	
	@Autowired
	JwtEntryPoint jwtEntryPoint;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String token =request.getHeader(JWTConfig.Header);
		SecurityContext context=SecurityContextHolder.getContext();
		try {
			System.out.println("filter is call and your token is "+token);
			if(token !=null) {
				
				Jws<Claims> claims = Jwts.parser()
						.requireIssuer(JWTConfig.Issuer)
						.setSigningKey(JWTConfig.SecretKey)
						.parseClaimsJws(token);
				
				String user =(String)claims.getBody().get("usr");
				String role =(String)claims.getBody().get("rol");
				String mail =(String)claims.getBody().get("mid");
				
				System.out.println("your token is parse "+user +" role "+role +" mail "+mail);
				String authorites[]=role.split(",");
				System.out.println(authorites.length);
				
				List<GrantedAuthority> rolelist=new ArrayList<GrantedAuthority>();
			
				if(authorites.length >=1) {
					for(int i=0 ;i<authorites.length ;i++) {
						rolelist.add(new SimpleGrantedAuthority(authorites[i]));
					}
				}
				System.out.println(rolelist);
				
			     SecurityContextToken SCT =new SecurityContextToken(user,null, rolelist,mail);
			     context.setAuthentication(SCT);
			}
			filterChain.doFilter(request, response);
			context.setAuthentication(null);
		}catch (org.springframework.security.core.AuthenticationException ex) {
			
			jwtEntryPoint.commence(request, response, ex);
		}
		
		
	}

}
