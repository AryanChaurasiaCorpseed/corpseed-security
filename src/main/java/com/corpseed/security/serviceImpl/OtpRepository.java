package com.corpseed.security.serviceImpl;

import org.springframework.data.jpa.repository.JpaRepository;

import com.corpseed.security.models.OTP;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OTP,Long> {

    Optional<OTP> findByMobileContaining(String mobile);

    OTP findByMobileContainingAndOtpCode(String mobile, String otp);
}
