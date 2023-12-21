package com.corpseed.security.payload.request;

import org.springframework.web.bind.annotation.RequestParam;

import lombok.Data;

@Data
public class UpdatePassword {

	 String email;
	 String password;
	 String otp;
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
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	 
	 
}
