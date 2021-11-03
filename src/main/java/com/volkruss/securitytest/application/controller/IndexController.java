package com.volkruss.securitytest.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.volkruss.securitytest.domain.repository.UserRepository;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "hello";
	}
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/test")
	@ResponseBody
	public String test() {
		// SecurityContextHolder は、Spring Security が認証された人の詳細を格納する場所です。
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		String username = authentication.getName();
		// Object principal = authentication.getPrincipal();
		
		
		return username;
	}

	
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private UserRepository userRepository;
	
	// 本来はformを利用して作成します。
	@ResponseBody
	@PostMapping("/register")
	public String signup(@RequestParam("username") String username, @RequestParam("password") String password) {
		if(username.isEmpty() || password.isEmpty()) {
			return "ユーザー名またはパスワードが未入力です";
		}
		
		boolean result = userRepository.saveUser(username, encoder.encode(password));
		if(result) {
			return "登録しました";
		}else {
			return "エラーがあります";
		}
	}

}
