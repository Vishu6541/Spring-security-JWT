package com.security.jpa.jwt.jwtkit;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.security.jpa.jwt.Dao.AuthenticationRepo;
import com.security.jpa.jwt.entity.Authentication;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;


public class JWTUtil {
	
	public static String getJWtToken(UserDetail userDetail ,AuthenticationRepo authenticationRepo) {
		String mytoken = null;
		if (userDetail.getUsername() != null && userDetail.getPassword() != null) {
			
			Optional<Authentication> auth = authenticationRepo.findByEmail(userDetail.getUsername());
			if (auth.isPresent()) {
				Authentication authentication = auth.get();
				if (authentication.getEmail().equals(userDetail.getUsername())
						&& authentication.getPassword().equals(userDetail.getPassword())) {

					Map<String, Object> claims = new HashMap<>();
					claims.put("usr", authentication.getName());
					claims.put("mid", authentication.getEmail());
					claims.put("iss", JWTConfig.Issuer);
					claims.put("rol", authentication.getRole());

					mytoken = Jwts.builder().addClaims(claims).signWith(SignatureAlgorithm.HS512, JWTConfig.SecretKey)
							.compact();

				}

			} else {

				return "Invalid Credintails";

			}
		} else {
			return "Please add Json data in this format";
		}
		return mytoken;

	}
}
