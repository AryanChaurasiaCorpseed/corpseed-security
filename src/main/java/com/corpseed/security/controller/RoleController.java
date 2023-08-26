package com.corpseed.security.controller;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corpseed.security.models.Role;
import com.corpseed.security.models.User;
import com.corpseed.security.repository.RoleRepository;
import com.corpseed.security.repository.UserRepository;

@RestController
public class RoleController {
	
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	  
	@PatchMapping("/api/v1/updateUserRole")
	public boolean updateUserRole(@RequestParam Long userId,List<String>role) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser!=null) {
			User user = optionalUser.get();
			Set<Role> roles = user.getRoles();
		}
		return true;
	}
	
	@GetMapping("/api/v1/getUserRole")
	public 	User getUserRole(@RequestParam Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.get();	
		return user;
	}
	
	@GetMapping("/api/v1/createRole")
	public 	Role createRole(@RequestParam String name) {
		Role role = new Role();
		role.setName(name);
		Role r = roleRepository.save(role);
		return r;
	}
	@GetMapping("/api/v1/getRole")
	public List<Role>getAllRole(){
		List<Role> roles = roleRepository.findAll();
		return roles;
	}
	

}
