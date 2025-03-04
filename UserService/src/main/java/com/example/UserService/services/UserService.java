package com.example.UserService.services;

import java.util.List;

import com.example.UserService.entities.User;

public interface UserService {

	//user operations
	
	//create user
	User saveUser(User user);
	
	//get all user
	List<User> getAllUser();
	
	//get single user of given userId
	User getUser(String userId);
	
}
