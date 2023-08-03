package com.corpseed.security.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corpseed.security.service.User;
import com.corpseed.security.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@RestController
public class UserController {

//	@PostMapping("user")
//	public User login(@RequestParam("user") String username, @RequestParam("password") String pwd) {
//		
//		String token = getJWTToken(username);
//		User user = new User();
//		user.setName(username);
//		user.setToken(token);		
//		return user;
//		
//	}
//
//	private String getJWTToken(String username) {
//		String secretKey = "mySecretKey";
//		List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//				.commaSeparatedStringToAuthorityList("ROLE_USER");
//		
//		String token = Jwts
//				.builder()
//				.setId("softtekJWT")
//				.setSubject(username)
//				.claim("authorities",
//						grantedAuthorities.stream()
//								.map(GrantedAuthority::getAuthority)
//								.collect(Collectors.toList()))
//				.setIssuedAt(new Date(System.currentTimeMillis()))
//				.setExpiration(new Date(System.currentTimeMillis() + 600000))
//				.signWith(SignatureAlgorithm.HS512,
//						secretKey.getBytes()).compact();
//
//		return "Bearer " + token;
//	}
	@Autowired
	UserService userService;
	
	
	@GetMapping("/admin")
	public List<User> getUsers() {
		return userService.getAllUser();
	}
	
	
	
	
}