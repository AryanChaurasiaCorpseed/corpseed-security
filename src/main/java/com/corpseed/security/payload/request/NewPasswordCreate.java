package com.corpseed.security.payload.request;

import lombok.Data;

@Data
public class NewPasswordCreate {
	
	Long id;
	String password;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
