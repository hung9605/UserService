package com.user.service.serviceimpl;

import org.springframework.stereotype.Service;

import com.user.dto.Account;
import com.user.repository.UserRepository;
import com.user.service.DashBoardService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DashBoardServiceImpl implements DashBoardService{
	
	final UserRepository userRepository;

	@Override
	public Account getAccountDashBoard() {
		// TODO Auto-generated method stub
		return userRepository.getDataDashBoardAccount();
	}

}
