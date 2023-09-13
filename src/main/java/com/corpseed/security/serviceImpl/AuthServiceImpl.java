package com.corpseed.security.serviceImpl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.corpseed.security.jwt.JwtUtils;
import com.corpseed.security.models.Role;
import com.corpseed.security.models.User;
import com.corpseed.security.payload.request.SignupRequest;
import com.corpseed.security.repository.RoleRepository;
import com.corpseed.security.repository.UserRepository;
import com.corpseed.security.services.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
	  @Autowired
	  AuthenticationManager authenticationManager;

	  @Autowired
	  UserRepository userRepository;

	  @Autowired
	  RoleRepository roleRepository;

	  @Autowired
	  PasswordEncoder encoder;

	  @Autowired
	  JwtUtils jwtUtils;
	  
	  public Map<String,Object> registerUserV2(SignupRequest signUpRequest){

		  Map<String,Object>map = new HashMap<>();
		   String flag="false";
			  User user = null;
			  if(signUpRequest.getUsername()!=null && signUpRequest.getEmail()!=null &&signUpRequest.getPassword()!=null&&signUpRequest.getUsername()!="" && signUpRequest.getEmail()!="" &&signUpRequest.getPassword()!="") {
				  user = new User();
				  user.setUsername(signUpRequest.getUsername());
				  user.setEmail(signUpRequest.getEmail());
				  user.setMobile(signUpRequest.getMobile());
				  user.setCompanyName(signUpRequest.getCompanyName()!=null?signUpRequest.getCompanyName():"NA");
				  user.setPassword(encoder.encode(signUpRequest.getPassword()));
			      Set<String> strRoles = signUpRequest.getRole();
			      Set<Role> roles = new HashSet<>();
			      user.setRoles(roles);
			      User u = userRepository.save(user);
			      map.put("name", u.getUsername());
			      map.put("email", u.getEmail());
			      map.put("roles", u.getRoles());
			      map.put("flag", "true");
                  map.put("message", "successfully registered");
			  }else {
			      map.put("flag", "false");
                  map.put("message", "either mail,name or password is missing please insert");

			  }
			 return map;

		  }
}
