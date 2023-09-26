package com.corpseed.security.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corpseed.security.models.Role;
import com.corpseed.security.models.User;
import com.corpseed.security.repository.RoleRepository;
import com.corpseed.security.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1")
public class RoleController {
	
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserRepository userRepository;
	  
	@PatchMapping("/roles/updateUserRole")
	public boolean updateUserRole(@RequestParam Long userId,List<String>role) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser!=null) {
			User user = optionalUser.get();
			List<Role> roles = user.getRoles();
		}
		return true;
	}
	
	@GetMapping("/roles/getUserRole")
	public 	User getUserRole(@RequestParam Long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.get();	
		return user;
	}
	
	@GetMapping("/roles/createRole")
	public 	Role createRole(@RequestParam String name) {
		Role role = new Role();
		role.setName(name);
		Role r = roleRepository.save(role);
		return r;
	}
	@GetMapping("/roles/getRole")
	public List<Role>getAllRole(){
		List<Role> roles = roleRepository.findAll();
		return roles;
	}
	

}
