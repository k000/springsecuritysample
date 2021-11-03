package com.volkruss.securitytest.config.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Configuration
public class MyAuthenticationProviderImple implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//　ユーザー名とパスワードを取得する
		String username = authentication.getName();
		String password = (String)authentication.getCredentials();
		
		// TODO DB認証をする場合は、ここでリポジトリからユーザー情報を取得してパスワードの整合性をチェックする。
		//　認可も付与します。
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if (username.equals("rengoku") && password.equals("kyojurou")) {
			authorities.add(new SimpleGrantedAuthority("USER"));
		} else {
			throw new BadCredentialsException("Authentication Error");
		}
		
		return new UsernamePasswordAuthenticationToken(username, password,authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
