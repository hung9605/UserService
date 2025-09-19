package com.user.service;

import java.util.List;

import com.user.model.User;

public interface UserService {
	
	List<User> list() throws Exception;
	User add(User user) throws Exception;
	void updateStatus(User user) throws Exception;
}
