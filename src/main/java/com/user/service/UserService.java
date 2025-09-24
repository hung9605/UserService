package com.user.service;

import java.util.List;

import com.user.dto.UserDto;
import com.user.model.User;

public interface UserService {
	
	List<UserDto> list() throws Exception;
	User add(UserDto dto) throws Exception;
	void updateStatus(User user) throws Exception;
}
