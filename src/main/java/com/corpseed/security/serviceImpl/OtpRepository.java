package com.corpseed.security.serviceImpl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.corpseed.security.models.OTP;

import java.util.Optional;
@Repository
public interface OtpRepository extends JpaRepository<OTP,Long> {

    Optional<OTP> findByMobileContaining(String mobile);

    OTP findByMobileContainingAndOtpCode(String mobile, String otp);
}
