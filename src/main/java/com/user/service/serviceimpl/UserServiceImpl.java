package com.user.service.serviceimpl;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user.dto.UserDto;
import com.user.mapper.UserMapper;
import com.user.model.User;
import com.user.repository.UserRepository;
import com.user.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;

	@Override
	public List<UserDto> list() throws Exception {
		// TODO Auto-generated method stub
		return userMapper.mapToDtos(userRepository.findAll());
	}

	@Override
	public User add(User user) throws Exception {
		// TODO Auto-generated method stub
		if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }

        // Kiểm tra email đã tồn tại chưa
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public void updateStatus(User user) throws Exception {
		userRepository.save(user);
		
	}

}
