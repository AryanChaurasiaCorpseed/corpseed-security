package com.corpseed.security.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.corpseed.security.models.OTP;
import com.corpseed.security.models.User;
import com.corpseed.security.payload.request.OtpRequest;
import com.corpseed.security.payload.request.OtpResponse;
import com.corpseed.security.repository.UserRepository;
import com.corpseed.security.serviceImpl.OtpRepository;
import com.corpseed.security.services.OtpService;
import com.corpseed.security.util.ResponseHandler;

@RestController
//@Api("Handle otp related actions")
@RequestMapping("/api/auth")
public class OtpController {

    @Autowired
    private OtpService otpService;
    
    @Autowired
    private OtpRepository otpRepository;
    
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
    
    public OTP findOtpByMobileAndOtpCode(String mobile, String otp) {
        return this.otpRepository.findByMobileContainingAndOtpCode(mobile,otp);
    }
    @GetMapping("/validateOtp")
    public ResponseEntity<Object> validateOtp(@RequestParam String mobile,@RequestParam String otpNo){
    	
    	 OTP otp=findOtpByMobileAndOtpCode(mobile,otpNo);

         if(otp==null) {
             return ResponseHandler.generateResponse(HttpStatus.NOT_ACCEPTABLE,false,"Enter a valid OTP !!",null);
  		}else	{	
 			return ResponseHandler.generateResponse(HttpStatus.OK, true,"success", otp.getOtpCode());	
 		}   
 	}
    @PostMapping("/forgetOtp")
    public OtpResponse forgetOtp(@RequestParam String email){	 	
    	User user = userRepository.findByEmail(email);
	    return this.otpService.generateOtp(user.getMobile(),user.getUsername(),user.getPassword());
    }
}

