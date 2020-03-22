package com.security.jpa.jwt.jwtkit;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@EnableWebSecurity
public class SpringSecurityConfigration extends WebSecurityConfigurerAdapter {
	
	@Bean
	public JWTAuthenticationFilter getJwtAuthenticationFilter() {
		return new JWTAuthenticationFilter();
	}
	
	protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
        	 .authorizeRequests()
        	 	  .antMatchers("/tokens/**")
        	 	  		.permitAll()
        	 	  		.antMatchers("/auths/**").permitAll()
       	 	  		.antMatchers("/admins/**").hasAuthority("Admin")
        	 	  		.antMatchers("/users/**").hasAuthority("User")
             	 .anyRequest()
             	 	.authenticated()
             	 		.and()
             .addFilterBefore(getJwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

}
	}
