package com.corpseed.security.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corpseed.security.models.Role;
import com.corpseed.security.models.User;
import com.corpseed.security.repository.UserRepository;

@RestController
public class RoleController {
	
	@Autowired
	UserRepository userRepository;
	  
	@PatchMapping("/api/v1/updateRole")
	public boolean updateRole(@RequestParam Long userId,List<String>role) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser!=null) {
			User user = optionalUser.get();
			Set<Role> roles = user.getRoles();
		}
		return true;
	}
	
	@GetMapping("/api/v1/getRole")
	public 	User getRole(@RequestParam Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.get();
		
		return user;
	}
	

}
