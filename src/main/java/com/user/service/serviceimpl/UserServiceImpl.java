package com.user.service.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.user.constants.CommonConstant;
import com.user.dto.NotificationMessage;
import com.user.dto.UserDto;
import com.user.dto.UserPassDto;
import com.user.exception.PasswordConfirmMismatchException;
import com.user.exception.PasswordNotMatchException;
import com.user.exception.UserNotFoundException;
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
	private final SimpMessagingTemplate messagingTemplate;

	@Override
	public List<UserDto> list() {
		return userMapper.mapToDtos(userRepository.findAll(Sort.by(Sort.Direction.ASC,"username")));
	}

	@Override
	@Transactional
	public User add(UserDto dto) throws Exception {
		validateUserDto(dto);
		Optional<User> existingUserOpt = userRepository.findById(dto.getUsername());
		boolean isNew = existingUserOpt.isEmpty();
        User user = userMapper.mapToModel(dto);
        if(isNew) {
        	user.setPassword(passwordEncoder.encode(user.getPassword()));
        }else {
        	user.setPassword(existingUserOpt.get().getPassword());
        	authorityRepository.deleteByUsername(user.getUsername());
        }
		User userSave = userRepository.save(user);
		List<Authority> authorities = dto.getRoles().stream().map(item -> 
		 	new Authority(userSave.getUsername(), item.getCode())).collect(Collectors.toList());
   		authorityRepository.saveAll(authorities);
		notify("User", "created");
		return user;
	}

	@Override
	public void updateStatus(User user) {
		if (!userRepository.existsById(user.getUsername())) {
	        throw new UserNotFoundException();
	    }
		userRepository.save(user);
		notify("User", "updated");
	}
	
	private void validateUserDto(UserDto dto) {
		Objects.requireNonNull(dto.getStatus(), "Status must not be null!");
	    Objects.requireNonNull(dto.getRoles(), "Role must not be null!");
	}

	@Override
	@Transactional
	public void updateEnable(String username,boolean status){
		    User user = userRepository.findById(username).orElseThrow(() -> new UserNotFoundException());
		    user.setEnabled(status);
		    userRepository.save(user);
		    notify("User", "updated");
	}

	@Override
	public void changePass(UserPassDto dto) throws Exception {
		 User user = userRepository.findById(dto.getUsername())
                 .orElseThrow(UserNotFoundException::new);
		 if(!passwordEncoder.matches(dto.getCurrentPass(), user.getPassword())) {
			throw new PasswordNotMatchException();
		 }
		 if(!dto.getNewPass().equals(dto.getConfirmPass())) {
			throw new PasswordConfirmMismatchException();
		 }
		 user.setPassword(passwordEncoder.encode(dto.getNewPass()));
		 userRepository.save(user);
		 notify("User", "updated");
	}
	
	private void notify(String entity, String action) {
	    NotificationMessage message = new NotificationMessage(entity, action);
	    messagingTemplate.convertAndSendToUser("tuannd", "/queue/notify", message);
	}

	@Override
	public void resetPass(UserDto dto) {
		// TODO Auto-generated method stub
		userRepository.updatePassword(dto.getUsername(), passwordEncoder.encode(CommonConstant.DEFAULT_PASSWORD));
	}
	


}