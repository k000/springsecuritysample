package com.volkruss.securitytest.config.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

public class MyAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException{
		
		//　ユーザー名とパスワードをフォームから取得する。
		String username = obtainUsername(request);
		String password = obtainPassword(request);
		//　トークンの作成を行う
		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		// Configクラスで設定するAuthenticationProviderが帰ります。
		Authentication result = this.getAuthenticationManager().authenticate(authRequest);
		return result;
	}
}
