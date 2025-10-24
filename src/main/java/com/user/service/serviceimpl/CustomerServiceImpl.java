package com.user.service.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.model.ChatMessage;
import com.user.model.Customer;
import com.user.repository.CustomerRepository;
import com.user.service.CustomerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;

	@Override
	public Customer add(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	@Override
	public List<ChatMessage> getMessage(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

}
