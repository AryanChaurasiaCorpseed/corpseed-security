package com.corpseed.security.serviceImpl;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import com.corpseed.security.models.OTP;
import com.corpseed.security.models.User;
import com.corpseed.security.payload.request.OtpResponse;
import com.corpseed.security.payload.request.UpdateOtpResponse;
import com.corpseed.security.repository.UserRepository;
import com.corpseed.security.services.OtpService;
import com.corpseed.security.util.CommonUtil;

@Service
public class OtpServiceImpl implements OtpService {

	@Autowired
	private OtpRepository otpRepository;

	@Autowired
	MailSenderServiceImpl mailSenderServiceImpl;

	@Autowired
	private UserRepository userRepository;

	@Override
	public OtpResponse generateOtp(String mobile, String name,String password,String email) {
		String otpCode = CommonUtil.generateOTP(6);
		System.out.println(otpCode);

//		OTP otp = this.otpRepository.findByMobileContaining
//				(mobile.length() > 10 ? mobile.trim().substring(mobile.length() - 10)
//						: mobile.trim())
//				.orElse(new OTP().builder().mobile(mobile.trim())
//								.otpCode(otpCode).count(1L).isUsed(false).created_at(CommonUtil.getDate()).name(name).password(password)
//								.expiredAt(CommonUtil.getExpiryDateTime()).build());
		OTP otp=null;
		
		otp = otpRepository.findByMobile
				(mobile.length() > 10 ? mobile.trim().substring(mobile.length() - 10): mobile.trim());
		 if(otp==null) {
			 otp=new OTP();
			 otp.setMobile(mobile.trim());
			 otp.setOtpCode(otpCode);
			 otp.setCount(1L);
			 otp.setUsed(false);
			 otp.setCreated_at(CommonUtil.getDate());
			 otp.setName(name);
			 otp.setPassword(password);
			 otp.setExpiredAt(CommonUtil.getExpiryDateTime());
			 otpRepository.save(otp);
			 
		 }
		 
		System.out.println("otp====="+otp);

		if(otp.getId()!=null&&otp.getId()>0){
			otp.setCount(otp.getCount()+1);
			//            System.out.println("otp ..........."+otpCode);
			otp.setOtpCode(otpCode);
			otp.setName(name);
			otp.setEmail(email);
			otp.setPassword(password);
			otp.setExpiredAt(CommonUtil.getExpiryDateTime());
		};

		OTP save = this.otpRepository.save(otp);
		
		
		//===============================================Mail - Implementation ===============================
		try {

		Context context = new Context();
		context.setVariable("otp", otpCode);
		context.setVariable("currentYear", LocalDateTime.now().getYear());
		String subject="OTP Verification";
		String text="CLICK ON THIS link and set password";
		String[] ccPersons= {"aryan.chaurasia@corpseed.com"};
		String[] toPersons= {"aryan.chaurasia@corpseed.com",email};
		mailSenderServiceImpl.sendEmail(toPersons, ccPersons,ccPersons, subject,text,context,"TeamAdd.html");
		}catch(Exception e) {
			e.printStackTrace();
		}
		//===============================================
		if(save!=null)
			//        	return null;
			return OtpResponse.builder().mobile(mobile).otp(otpCode).build();
		else return null;
	}

	@Override
	public OTP findOtpByMobileAndOtpCode(String mobile, String otp) {
		return this.otpRepository.findByMobileContainingAndOtpCode(mobile,otp);
	}

	@Override
	public Map<String,Object> isUserExistOrNot(String email) {
		Map<String,Object>res = new HashMap<>();
		boolean flag=false;
		User user = userRepository.findByEmail(email);
		if(user!=null) {
			flag=true;			
		}
		res.put("flag", flag);
		res.put("userId", user!=null?user.getId():null);
		res.put("mobile", user!=null?user.getMobile():null);

		return res;
	}



	@Override
	public UpdateOtpResponse forgetOtp(String mobile, String name, String password, String email) {
		String otpCode = CommonUtil.generateOTP(6);
		OTP otp=null;
        if(mobile!=null) {
//    		otp = this.otpRepository.findByMobileContaining
//    				(mobile.length() > 10 ? mobile.trim().substring(mobile.length() - 10)
//    						: mobile.trim()).orElse(new OTP().builder().mobile(mobile.trim())
//    								.otpCode(otpCode).count(1L).isUsed(false).created_at(CommonUtil.getDate()).name(name).password(password)
//    								.expiredAt(CommonUtil.getExpiryDateTime()).build());
    		
    		otp = otpRepository.findByMobile
    				(mobile.length() > 10 ? mobile.trim().substring(mobile.length() - 10): mobile.trim());
    		 if(otp==null) {
    			 otp=new OTP();
    			 otp.setMobile(mobile.trim());
    			 otp.setOtpCode(otpCode);
    			 otp.setCount(1L);
    			 otp.setUsed(false);
    			 otp.setCreated_at(CommonUtil.getDate());
    			 otp.setName(name);
    			 otp.setPassword(password);
    			 otp.setExpiredAt(CommonUtil.getExpiryDateTime());
    			 otpRepository.save(otp);
    			 
    		 }
        }else {
//    		otp = this.otpRepository.findByEmailContaining
//    				(email).orElse(new OTP().builder().email(email)
//    								.otpCode(otpCode).count(1L).isUsed(false).created_at(CommonUtil.getDate()).name(name).password(password)
//    								.expiredAt(CommonUtil.getExpiryDateTime()).build());
    		
    		otp = otpRepository.findByEmail(email);
    		 if(otp==null) {
    			 otp=new OTP();
    			 otp.setMobile(mobile.trim());
    			 otp.setOtpCode(otpCode);
    			 otp.setCount(1L);
    			 otp.setUsed(false);
    			 otp.setCreated_at(CommonUtil.getDate());
    			 otp.setName(name);
    			 otp.setPassword(password);
    			 otp.setExpiredAt(CommonUtil.getExpiryDateTime());
    			 otpRepository.save(otp);
    			 
    		 }
        }
		System.out.println("otp====="+otp);

		if(otp.getId()!=null&&otp.getId()>0){
			otp.setCount(otp.getCount()+1);
			otp.setOtpCode(otpCode);
			otp.setName(name);
			otp.setEmail(email);
			otp.setPassword(password);
			otp.setExpiredAt(CommonUtil.getExpiryDateTime());
		};

		OTP save = this.otpRepository.save(otp);
		//===============================================Mail - Implementation ===============================
		try {

		Context context = new Context();
		context.setVariable("otp", otpCode);
		context.setVariable("currentYear", LocalDateTime.now().getYear());
		String subject="OTP Verification";
		String text="CLICK ON THIS link and set password";
		String[] ccPersons= {"aryan.chaurasia@corpseed.com"};
		String[] toPersons= {"aryan.chaurasia@corpseed.com",email};
		mailSenderServiceImpl.sendEmail(toPersons, ccPersons,ccPersons, subject,text,context,"TeamAdd.html");
		}catch(Exception e) {
			e.printStackTrace();
		}
		//===============================================
		if(save!=null) {
			UpdateOtpResponse updateOtpResponse = new UpdateOtpResponse();
			updateOtpResponse.setEmail(email);
			updateOtpResponse.setMobile(mobile);
			updateOtpResponse.setOtp(otpCode);
			return updateOtpResponse;
		}
		else {
			return null;
		}
	}
}
