package com.user.service.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.dto.UserDto;
import com.user.mapper.UserMapper;
import com.user.model.Authority;
import com.user.model.User;
import com.user.repository.AuthorityRepository;
import com.user.repository.UserRepository;
import com.user.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService{
	
	private final UserRepository userRepository;
	private final UserMapper userMapper;
	private final AuthorityRepository authorityRepository;
	private final PasswordEncoder passwordEncoder;

	@Override
	public List<UserDto> list() throws Exception {
		// TODO Auto-generated method stub
		return userMapper.mapToDtos(userRepository.findAll());
	}

	@Override
	public User add(UserDto dto) throws Exception {
		
		validateUserDto(dto);
        User user = userMapper.mapToModel(dto);
        //PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		User userSave = userRepository.save(user);
		List<Authority> authorities = dto.getRoles().stream().map(item -> 
		 new Authority(userSave.getUsername(), item.getCode())
		).collect(Collectors.toList());
		authorityRepository.saveAll(authorities);
		return user;
	}

	@Override
	public void updateStatus(User user) throws Exception {
		userRepository.save(user);
		
	}
	
	private void validateUserDto(UserDto dto) {
//	    userRepository.findByUsername(dto.getUsername())
//	            .ifPresent(u -> { throw new RuntimeException("Username already exists"); });
//	    userRepository.findByEmail(dto.getEmail())
//	            .ifPresent(u -> { throw new RuntimeException("Email already exists"); });
		Objects.requireNonNull(dto.getStatus(), "Status must not be null!");
	    Objects.requireNonNull(dto.getRoles(), "Role must not be null!");
	}

	@Override
	@Transactional
	public void updateEnable(String username,boolean status) throws Exception {
		    User user = userRepository.findById(username)
		                              .orElseThrow(() -> new RuntimeException("User not found"));
		    user.setEnabled(status);
		    userRepository.save(user);
	}




}
