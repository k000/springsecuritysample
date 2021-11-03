package com.volkruss.securitytest.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class MyProvider implements AuthenticationProvider{



	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		// ここで返すものがいまいち理解できんン
		/**
		 * 
		 * Returns:a fully authenticated object including credentials. 
		 * May return null if the AuthenticationProvider is unable to supportauthentication of the passed Authentication object. 
		 * In such a case,the next AuthenticationProvider that supports the presented Authentication class will be tried.
		 * 
		 */
		
		return new UsernamePasswordAuthenticationToken(authentication, authentication);
		//sreturn null;
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return false;
	}

}
