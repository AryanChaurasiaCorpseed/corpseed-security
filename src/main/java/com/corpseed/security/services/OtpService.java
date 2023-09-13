package com.corpseed.security.services;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.corpseed.security.models.OTP;
import com.corpseed.security.payload.request.OtpResponse;
@Service
public interface OtpService {


    OtpResponse generateOtp(String mobile, String name,String password);

    OTP findOtpByMobileAndOtpCode(String mobile, String otp);

    Map<String,Object>  isUserExistOrNot(String email);
}
