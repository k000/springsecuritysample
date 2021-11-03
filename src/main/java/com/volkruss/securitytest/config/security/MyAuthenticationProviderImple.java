package com.volkruss.securitytest.config.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.volkruss.securitytest.domain.entity.auth.UserEntity;
import com.volkruss.securitytest.domain.repository.UserRepository;

@Configuration
public class MyAuthenticationProviderImple implements AuthenticationProvider {
	
	@Bean
	public PasswordEncoder passwordEncorder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//　ユーザー名とパスワードを取得する
		String username = authentication.getName();
		String password = (String)authentication.getCredentials();
		
		// DB認証をする場合は、ここでリポジトリからユーザー情報を取得してパスワードの整合性をチェックする。
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		Optional<UserEntity> opt = userRepository.findByUserName(username);
		if(opt.isEmpty()) {
			throw new BadCredentialsException("Authentication Error");
		}
		PasswordEncoder encoder = passwordEncorder();
		if(encoder.matches(password,opt.get().getPassword())) {
			// TODO 認可の取得を行う
			authorities.add(new SimpleGrantedAuthority("USER"));
		}else {
			throw new BadCredentialsException("Authentication Error");
		}
		//　認可も付与します。	
		/*
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		if (username.equals("rengoku") && password.equals("kyojurou")) {
			authorities.add(new SimpleGrantedAuthority("USER"));
		} else {
			throw new BadCredentialsException("Authentication Error");
		}
		*/
		return new UsernamePasswordAuthenticationToken(username, password,authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}
}
