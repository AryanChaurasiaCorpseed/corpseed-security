package com.corpseed.security.util;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.corpseed.security.models.OTP;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@UtilityClass
public class CommonUtil {

    public static boolean isOtpExpired(OTP otp,String enteredOtp) {
        if(otp==null||!otp.equals(enteredOtp))
            return true;

        return false;
    }

    public static Date getDate(){return new Date();}

    public static LocalTime getCurrentDateAndTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalTime localTime = localDateTime.toLocalTime();
        return localTime;
    }

    public static String generateOTP(int length) {
        String numbers = "1234567890";
        Random random = new Random();
        StringBuffer otp = new StringBuffer(length);

        for(int i = 0; i< length ; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return otp.toString();
    }

    public static Date getExpiryDateTime() {
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MINUTE,10);
        return calendar.getTime();
    }

    public static String encodePassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
