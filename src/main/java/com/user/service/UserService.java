package com.user.service;

import java.util.List;

import com.user.dto.UserDto;
import com.user.dto.UserPassDto;
import com.user.model.User;

public interface UserService {
	
	List<UserDto> list();
	User add(UserDto dto) throws Exception;
	void updateStatus(User user);
	void updateEnable(String username,boolean enable);
	void changePass(UserPassDto dto) throws Exception;
}
