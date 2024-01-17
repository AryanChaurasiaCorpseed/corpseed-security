package com.corpseed.security.services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.corpseed.security.models.OTP;
import com.corpseed.security.models.User;
import com.corpseed.security.payload.request.SignupRequest;
import com.corpseed.security.payload.request.UpdatePassword;
import com.corpseed.security.payload.request.UpdateUserDataDto;
@Service
public interface AuthService {

	 public Map<String,Object> registerUserV2(SignupRequest signUpRequest);

	public Map<String, Object> createNewUserByEmail(String userName,String email, List<String> role,String designation,String department);

	public User updateUserData(UpdateUserDataDto updateUserDataDto);


	public boolean updateUser(UpdatePassword updatePassword, User user, OTP o);
}
