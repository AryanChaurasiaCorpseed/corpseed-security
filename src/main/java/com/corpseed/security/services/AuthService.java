package com.corpseed.security.services;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.corpseed.security.models.User;
import com.corpseed.security.payload.request.SignupRequest;
@Service
public interface AuthService {

	 public Map<String,Object> registerUserV2(SignupRequest signUpRequest);
}
