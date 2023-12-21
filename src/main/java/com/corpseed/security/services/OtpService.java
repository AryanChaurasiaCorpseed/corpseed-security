package com.corpseed.security.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.corpseed.security.models.OTP;
import com.corpseed.security.payload.request.OtpResponse;
import com.corpseed.security.payload.request.UpdateOtpResponse;
@Service
public interface OtpService {


    OtpResponse generateOtp(String mobile, String name,String password,String email);

    OTP findOtpByMobileAndOtpCode(String mobile, String otp);

    Map<String,Object>  isUserExistOrNot(String email);
    
	UpdateOtpResponse forgetOtp(String mobile, String username, String password, String email);

}
