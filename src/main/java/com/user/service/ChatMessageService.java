package com.user.service;

import java.util.List;

import com.user.model.ChatMessage;
import com.user.model.User;

public interface ChatMessageService {
	
	ChatMessage add(ChatMessage message);
	void delete(ChatMessage message);
	List<ChatMessage> getMessage();

}
