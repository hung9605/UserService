package com.user.service.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;

	@Override
	public List<User> list() throws Exception {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User add(User user) throws Exception {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	@Override
	public void updateStatus(User user) throws Exception {
		userRepository.save(user);
		
	}

}
