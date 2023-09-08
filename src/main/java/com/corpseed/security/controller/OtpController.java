package com.corpseed.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.corpseed.security.payload.request.OtpRequest;
import com.corpseed.security.payload.request.OtpResponse;
import com.corpseed.security.services.OtpService;

@RestController
//@Api("Handle otp related actions")
@RequestMapping("/api/auth/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;


//    @ApiOperation(value = "generate otp",response = OtpResponse.class)
//    @ApiResponses(value = {
//            @ApiResponse(code = 200,message = "Successfully otp generated"),
//            @ApiResponse(code = 500,message = "Something Went-Wrong"),
//            @ApiResponse(code = 400,message = "Bad Request")
//    })
    @PostMapping()
    public OtpResponse generateOtp(@RequestBody OtpRequest otpRequest){

//        SignupUser signupUser1 = this.signupService.createUserDetail(signupUser);

        return this.otpService.generateOtp(otpRequest.getMobile(),otpRequest.getName(),otpRequest.getPassword());
    }
}

