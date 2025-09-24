package com.user.service.serviceimpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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

	@Override
	public List<UserDto> list() throws Exception {
		// TODO Auto-generated method stub
		return userMapper.mapToDtos(userRepository.findAll());
	}

	@Override
	public User add(UserDto dto) throws Exception {
		
		if (userRepository.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("Username already exists");
        }
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Email already exists");
        }
        Objects.requireNonNull(dto.getRoles(), "Role must not be null!");
        User user = userMapper.mapToModel(dto);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
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

}
