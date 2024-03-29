package com.corpseed.security.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.*;

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
import com.corpseed.security.payload.request.NewPasswordCreate;
import com.corpseed.security.payload.request.NewSignupRequest;
import com.corpseed.security.payload.request.SignupRequest;
import com.corpseed.security.payload.request.UpdatePassword;
import com.corpseed.security.payload.request.UpdateUserDataDto;
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
	
	@GetMapping("/test")
	public List<String>  testMicroservices() {
        try {
//          ipAddressInfoList.add(localhost.getHostAddress());
//          ipAddressInfoList.add(localhost.getHostName());
//          ipAddressInfoList.add(Arrays.toString(localhost.getAddress()));
//          ipAddressInfoList.add(localhost.getCanonicalHostName());

          InetAddress localhost = InetAddress.getLocalHost();
          String systemipaddress = localhost.getHostAddress();
          String systemhostname = localhost.getHostName();
          String systemaddress = Arrays.toString(localhost.getAddress());
//          String systemipaddress3 = localhost.getCanonicalHostName();


          List<String>  ipaddressdetails= new ArrayList<>();
          ipaddressdetails.add(systemhostname);
          ipaddressdetails.add(systemaddress);
          ipaddressdetails.add(systemipaddress);

          System.out.println(systemhostname+systemaddress);


          System.out.println("System IP Address: " + systemipaddress + "\n");
          return ipaddressdetails; // return the IP address as a String
      } catch (UnknownHostException e) {
          System.err.println("Error resolving host: " + e.getMessage());
          return new ArrayList<>(); // or any other default value
      } catch (Exception e) {
          e.printStackTrace();
          return new ArrayList<>(); // handle other exceptions appropriately in your application
      }
//		return "this is a person";
	}
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
		System.out.println(user+"====================");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(user.getUsername(), loginRequest.getPassword()));
		System.out.println(authentication+"=====AA===============");

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
    
    public OTP findOtpByEmailAndOtpCode(String email, String otp) {
        return this.otpRepository.findByEmailContainingAndOtpCode(email,otp);
    }

	@PostMapping("/createNewUser")
	public ResponseEntity<Object> registerUserV3(@RequestBody SignupRequest signUpRequest){
		
        OTP otp=findOtpByMobileAndOtpCode(signUpRequest.getMobile(),signUpRequest.getOtp());

        if(otp==null) {
            return ResponseHandler.generateResponse(HttpStatus.NOT_ACCEPTABLE,false,"Enter a valid OTP !!",null);
        }
		Map<String,Object> response = authService.registerUserV2(signUpRequest);
		
		if (response.get("flag").toString().equals("true"))	{	
			return ResponseHandler.generateResponse(HttpStatus.OK, true,"sucess", response);	
		}else	{	
			return ResponseHandler.generateResponse(HttpStatus.PARTIAL_CONTENT,false,"failed",response);
		}
	}
	
	@PostMapping("/createNewUserByEmail")
	public ResponseEntity<Object> createNewUserByEmail(@RequestBody NewSignupRequest newSignupRequest){

		Map<String,Object> response = authService.createNewUserByEmail(newSignupRequest.getUserName(),newSignupRequest.getEmail(),newSignupRequest.getRole(),newSignupRequest.getDesignation());
		
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
	public Boolean updateUser(@RequestBody UpdatePassword updatePassword) {
		Boolean flag=false;
		User user =userRepository.findByEmail(updatePassword.getEmail());
		 OTP o=null;
		 System.out.println("user .. "+user);
		if(user.getMobile()!=null) {
	        o=findOtpByMobileAndOtpCode(user.getMobile(),updatePassword.getOtp());
		}else {
	        o=findOtpByEmailAndOtpCode(user.getEmail(),updatePassword.getOtp());
		}
		 System.out.println("OTP . .. . "+o);

        if(o==null) {
              flag=false;
        }else {
        	 flag = authService.updateUser( updatePassword,user,o);
        }    
		return flag;
		
	}
	
	@PutMapping("/setNewPassword")
	public ResponseEntity<Object> setNewPassword(@RequestBody NewPasswordCreate newPasswordCreate) {
		Boolean flag=false;

//		Optional<User> optionalUser = userRepository.findById(userId);
		 Optional<User> user = userRepository.findById(newPasswordCreate.getId());
        if((!user.isEmpty()) &&user.get()!=null) {
        	User u =user.get();
        	if(u.isFlag()==true) {
        		u.setPassword(encoder.encode(newPasswordCreate.getPassword()));
        		u.setFlag(false);
                userRepository.save(u);
    			return ResponseHandler.generateResponse(HttpStatus.OK, true,"sucess", u);	

        	}else {
    			return ResponseHandler.generateResponse(HttpStatus.UNAUTHORIZED, false,"User already set password . please forget it", null);	

        	}
        }else {
			return ResponseHandler.generateResponse(HttpStatus.UNAUTHORIZED, false,"User is not prsent", null);	

        }

		
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
	
	@PutMapping("/updateUserDataBySwagger")
	public ResponseEntity<Object> updateUserData(@RequestBody UpdateUserDataDto updateUserDataDto) {
		

//		Optional<User> optionalUser = userRepository.findById(userId);
		User u=authService.updateUserData(updateUserDataDto);
		Boolean flag=u!=null?true:false;
        if(flag) {
        	
			return ResponseHandler.generateResponse(HttpStatus.ACCEPTED, true,"user has been updated", flag);	

        }else {
			return ResponseHandler.generateResponse(HttpStatus.UNAUTHORIZED, false,"User is not prsent", null);	

        }

		
	}
}