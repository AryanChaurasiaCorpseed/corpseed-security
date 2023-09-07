package com.corpseed.security.services;

import com.corpseed.security.models.OTP;
import com.corpseed.security.payload.request.OtpResponse;

public interface OtpService {


    OtpResponse generateOtp(String mobile, String name,String password);

    OTP findOtpByMobileAndOtpCode(String mobile, String otp);
}
