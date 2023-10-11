package com.corpseed.security.controller;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corpseed.security.jwt.JwtUtils;
import com.corpseed.security.models.ERole;
import com.corpseed.security.models.OTP;
import com.corpseed.security.models.Role;
import com.corpseed.security.models.User;
import com.corpseed.security.payload.request.LoginRequest;
import com.corpseed.security.payload.request.SignupRequest;
import com.corpseed.security.payload.response.JwtResponse;
import com.corpseed.security.payload.response.MessageResponse;
import com.corpseed.security.repository.RoleRepository;
import com.corpseed.security.repository.UserRepository;
import com.corpseed.security.serviceImpl.OtpRepository;
import com.corpseed.security.services.AuthService;
import com.corpseed.security.services.UserDetailsImpl;
import com.corpseed.security.util.ResponseHandler;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/securityService/api/auth")
public class AuthController {

	@Autowired
	private AuthService authService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private OtpRepository otpRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;
	
	@GetMapping("/testm")
	public String  testMicroservices(@RequestParam("token") String token) {
		return "this is a person";
	}

//	@PostMapping("/signin")
//	public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getUserName(), loginRequest.getPassword()));
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		String jwt = jwtUtils.generateJwtToken(authentication);
//		System.out.println(jwt);
//		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
//		List<String> roles = userDetails.getAuthorities().stream()
//				.map(item -> item.getAuthority())
//				.collect(Collectors.toList());
//
//		return ResponseEntity.ok(new JwtResponse(jwt, 
//				userDetails.getId(), 
//				userDetails.getUsername(), 
//				userDetails.getEmail(), 
//				roles));
//	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUsers( @RequestBody LoginRequest loginRequest) {
//	public ResponseEntity<?> authenticateUsers( @RequestParam String email,@RequestParam String password) {

		User user=userRepository.findByEmail(loginRequest.getEmail());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		System.out.println(jwt);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
				userDetails.getId(), 
				userDetails.getUsername(), 
				userDetails.getEmail(), 
				roles));
	}
	
	/*
	 * By Aryan Chaurasia
	 */
    public OTP findOtpByMobileAndOtpCode(String mobile, String otp) {
        return this.otpRepository.findByMobileContainingAndOtpCode(mobile,otp);
    }

	@PostMapping("/createNewUser")
	public ResponseEntity<Object> registerUserV3(@RequestBody SignupRequest signUpRequest){
		
        OTP otp=findOtpByMobileAndOtpCode(signUpRequest.getMobile(),signUpRequest.getOtp());

        if(otp==null) {
            return ResponseHandler.generateResponse(HttpStatus.NOT_ACCEPTABLE,false,"Enter a valid OTP !!",null);
        }
		Map<String,Object> response = authService.registerUserV2(signUpRequest);
		
		  System.out.println("resssss=============================="+response.get("flag").toString());

		if (response.get("flag").toString().equals("true"))	{	
			return ResponseHandler.generateResponse(HttpStatus.OK, true,"sucess", response);	
		}else	{	
			return ResponseHandler.generateResponse(HttpStatus.PARTIAL_CONTENT,false,"failed",response);
		}
	}
	
	@PostMapping("/createNewUserByEmail")
	public ResponseEntity<Object> createNewUserByEmail(@RequestParam String email,@RequestParam String role,@RequestParam String designation,@RequestParam String userName){

		Map<String,Object> response = authService.createNewUserByEmail(userName,email,role,designation);
		
		if (response.get("flag").toString().equals("true"))	{	
			return ResponseHandler.generateResponse(HttpStatus.OK, true,"sucess", response);	
		}else	{	
			return ResponseHandler.generateResponse(HttpStatus.PARTIAL_CONTENT,false,"failed",response);
		}
	}


	//	@PostMapping("/createNewUser")
	//	public String registerUserV2(@RequestBody SignupRequest signUpRequest){
	//		String flag="false";
	//		User user = new User();
	//		if(signUpRequest.getUsername()!=null && signUpRequest.getEmail()!=null &&signUpRequest.getPassword()!=null&&signUpRequest.getUsername()!="" && signUpRequest.getEmail()!="" &&signUpRequest.getPassword()!="") {
	//			user.setUsername(signUpRequest.getUsername());
	//			user.setEmail(signUpRequest.getEmail());
	//			user.setPassword(encoder.encode(signUpRequest.getPassword()));
	//			Set<String> strRoles = signUpRequest.getRole();
	//			Set<Role> roles = new HashSet<>();
	//			user.setRoles(roles);
	//			userRepository.save(user);
	//			flag="true";
	//		}
	//		else {
	//			flag="can cot be empty";
	//
	//		}
	//		return flag;
	//
	//	}
	
	@PutMapping("/updateUser")
	public Boolean updateUser(Long userId,String password,String mobile) {
		Boolean flag=false;
//        OTP otp=findOtpByMobileAndOtpCode(mobile,otpId);
//        if(otp==null) {
//            return false;// if flag equals false then we have to return by 
//        }
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.get();
        user.setPassword(encoder.encode(password));
        userRepository.save(user);
        flag=true;
         
		return flag;
		
	}

	@PutMapping("/deleteUser")
	public boolean deleteUser(Long userId) {
		boolean flag=false;
		Optional<User> optionalUser = userRepository.findById(userId);
		if(optionalUser!=null && optionalUser.get()!=null) {

			User user = optionalUser.get();
			user.setIsDeleted(true);
			userRepository.save(user);
			flag=true;
		}
		return flag;
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

	      List<String> strRoles =  Arrays.asList("Admin","User");			      
		Set<Role> roles = new HashSet<>();
	      List<Role>rolesList=roleRepository.findAllByNameIn(strRoles);

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
				case "admin":
					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(adminRole);

					break;
				case "mod":
					Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(modRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			});
		}

		user.setRoles(rolesList);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}