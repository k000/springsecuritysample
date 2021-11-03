package com.volkruss.securitytest.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class MyWebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	// 自分のAuthenticationProviderを利用する
	@Autowired
    MyAuthenticationProviderImple authenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/","/hello","/register").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login")
			.permitAll()
			.and()
			.logout().permitAll();
	}
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		// 自分のauthenticationProviderを設定する
		auth.authenticationProvider(authenticationProvider);
	}

}
