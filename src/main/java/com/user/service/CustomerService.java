package com.user.service;

import java.util.List;

import com.user.model.ChatMessage;
import com.user.model.Customer;

public interface CustomerService {
	
	Customer add(Customer customer);
	
	List<ChatMessage> getMessage(Customer customer);

}
