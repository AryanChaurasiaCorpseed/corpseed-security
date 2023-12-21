package com.corpseed.security.payload.request;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.Data;

@Data
public class NewSignupRequest {

	String email;
	List<String> role;
	String designation;
	String userName;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public List<String> getRole() {
		return role;
	}
	public void setRole(List<String> role) {
		this.role = role;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
