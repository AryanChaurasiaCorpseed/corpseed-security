package com.corpseed.security.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.tomcat.util.digester.ArrayStack;
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
				  
			      List<String> strRoles =  Arrays.asList("ADMIN","USER");			      
			      List<Role>rolesList=roleRepository.findAllByNameIn(strRoles);
//			      List<Role> roles = new ArrayList<>();
			      if(rolesList!=null && rolesList.size()>0) {
				      user.setRoles(rolesList);
			      }
			      User u = userRepository.save(user);
			      map.put("id", u.getId());
			      map.put("name", u.getUsername());
			      map.put("designation", u.getDesignation());
			      map.put("email", u.getEmail());
			      map.put("roles", u.getRoles().stream().map(i->i.getName()).collect(Collectors.toList()));
			      map.put("flag", "true");
                  map.put("message", "successfully registered");
			  }else {
			      map.put("flag", "false");
                  map.put("message", "either mail,name or password is missing please insert");

			  }
			 return map;

		  }

	@Override
	public Map<String, Object> createNewUserByEmail(String userName,String email, String role,String designation) {
		// TODO Auto-generated method stub
		Map<String,Object>res = new HashMap<String,Object>();
		User user = new User();
		user.setUsername(userName);
		user.setEmail(email);
		List<String> strRoles =  new ArrayList<>();		      
		strRoles.add(role);
	     List<Role>rolesList=roleRepository.findAllByNameIn(strRoles);
		user.setRoles(rolesList);
		user.setDesignation(designation);
		User u=userRepository.save(user);
        if(u!=null) {
            res.put("flag", true);
        }else {
            res.put("flag", false);
        }
        res.put("userId", user.getId());
        res.put("name", user.getUsername());
        res.put("role", user.getRoles());
        res.put("designation", user.getDesignation());
        res.put("email", user.getEmail());

		return res;
	}
}
