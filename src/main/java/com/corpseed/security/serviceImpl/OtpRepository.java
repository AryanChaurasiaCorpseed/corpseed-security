package com.corpseed.security.serviceImpl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.corpseed.security.models.OTP;

import java.util.Optional;
@Repository
public interface OtpRepository extends JpaRepository<OTP,Long> {

    Optional<OTP> findByMobileContaining(String mobile);
    
    Optional<OTP> findByEmailContaining(String email);


    OTP findByMobileContainingAndOtpCode(String mobile, String otp);
	@Query(value = "SELECT * FROM otp o WHERE o.email =:email and o.otp_code=:otp", nativeQuery = true)
    OTP findByEmailContainingAndOtpCode(String email, String otp);

}
