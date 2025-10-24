package com.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.ResponseBean;
import com.user.model.ChatMessage;
import com.user.model.User;
import com.user.service.ChatMessageService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/support")
@AllArgsConstructor
@CrossOrigin("*")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupportController extends BaseController{
	
	final ChatMessageService chatMessageService;
	
	@PostMapping("/addmessage")
	public ResponseEntity<?> addMessage(@RequestBody ChatMessage message){
		chatMessageService.add(message);
		return defaultResponse();
	}
	
	@PostMapping("/deletemessage")
	public ResponseEntity<?> deleteMessage(@RequestBody ChatMessage message){
		chatMessageService.delete(message);
		return defaultResponse();
	}

	@PostMapping("/listmessage")
	public ResponseEntity<?> listMessage(){
		return response(new ResponseBean(chatMessageService.getMessage()));
	}
	
	
}
