package com.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.model.ChatMessage;
import com.user.model.User;

public interface ChatMessageRepository extends JpaRepository<ChatMessage,Integer>{
	
	List<ChatMessage> findByUser(User user);
}