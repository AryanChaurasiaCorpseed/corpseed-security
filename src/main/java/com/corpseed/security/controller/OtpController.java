package com.corpseed.security.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corpseed.security.models.User;
import com.corpseed.security.payload.request.OtpRequest;
import com.corpseed.security.payload.request.OtpResponse;
import com.corpseed.security.repository.UserRepository;
import com.corpseed.security.services.OtpService;

@RestController
//@Api("Handle otp related actions")
@RequestMapping("/api/auth")
public class OtpController {

    @Autowired
    private OtpService otpService;
    
	@Autowired
	private UserRepository userRepository;

    @PostMapping("/otp")
    public OtpResponse generateOtp(@RequestBody OtpRequest otpRequest){
        return this.otpService.generateOtp(otpRequest.getMobile(),otpRequest.getName(),otpRequest.getPassword());
    }
    @GetMapping("/isUserExistOrNot")
    public Map<String,Object> isUserExistOrNot(@RequestParam String email){
    	
        return this.otpService.isUserExistOrNot(email);
    }
    @PostMapping("/forgetOtp")
    public OtpResponse forgetOtp(@RequestParam String email,@RequestParam String password){	 	
    	User user = userRepository.findByEmail(email);
    	System.out.println();
    	System.out.println();
    	System.out.println();

	    return this.otpService.generateOtp(user.getMobile(),user.getUsername(),password);
    }
}

