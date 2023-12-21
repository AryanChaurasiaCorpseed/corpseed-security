package com.corpseed.security.payload.request;

import java.util.List;
import java.util.Set;

import lombok.Data;
@Data
public class SignupRequest {
	
	String username;
	String email;
	String password;
//	List<String>role;
    private String mobile;
    private String otp;
    String companyName;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	/*
	 * public List<String> getRole() { return role; } public void
	 * setRole(List<String> role) { this.role = role; }
	 */
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	
	
	

}
