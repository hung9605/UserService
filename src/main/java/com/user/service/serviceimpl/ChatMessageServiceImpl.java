package com.user.service.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.model.ChatMessage;
import com.user.model.Customer;
import com.user.repository.ChatMessageRepository;
import com.user.service.ChatMessageService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ChatMessageServiceImpl implements ChatMessageService {
	
	private final ChatMessageRepository chatMessageRepository;

	@Override
	public ChatMessage add(ChatMessage message) {
		// TODO Auto-generated method stub
		return chatMessageRepository.save(message);
	}

	@Override
	public void delete(ChatMessage message) {
		// TODO Auto-generated method stub
		chatMessageRepository.delete(message);
	}

	@Override
	public List<ChatMessage> getMessage(Customer customer) {
		// TODO Auto-generated method stub
		return chatMessageRepository.findByCustomer(customer);
	}

}