package com.corpseed.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private List<User>store = new ArrayList<>();
	
	public UserService() {
		store.add(new User(1L,"aryan","aryan@1998"));
		store.add(new User(2L,"ram","aryan@1998"));

		store.add(new User(3L,"shyam","aryan@1998"));
		store.add(new User(4L,"aj","aj@1998"));
		store.add(new User(5L,"bj","bj@1998"));
		store.add(new User(6L,"cj","cj@1998"));
		store.add(new User(7L,"dj","dj@1998"));

	}
	public List<User>getAllUser(){
		return store;
	}
}
