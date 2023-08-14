package com.corpseed.security.payload.response;

import java.util.List;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

public class JwtResponse {

//	public JwtResponse(String jwt, Long id, String username, String email, List<String> roles) {
//		// TODO Auto-generated constructor stub
//	}
	@Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	 String username;
	 String email;
	 @ManyToMany
	 List<String> roles;
	 String jwt;
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<String> getRoles() {
		return roles;
	}
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
	public JwtResponse(String jwt, Long id, String username, String email, List<String> roles) {
		super();
		this.id = id;
		this.username = username;
		this.email = email;
		this.roles = roles;
		this.jwt = jwt;
	}
	


}
