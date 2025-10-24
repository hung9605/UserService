package com.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.constants.CommonConstant;
import com.user.dto.ResponseBean;
import com.user.dto.UserDto;
import com.user.model.ChatMessage;
import com.user.model.Customer;
import com.user.service.ChatMessageService;
import com.user.service.CustomerService;
import com.user.service.DashBoardService;
import com.user.service.UserService;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/support")
@AllArgsConstructor
@CrossOrigin("*")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SupportController extends BaseController{
	
	final CustomerService customerService;
	final ChatMessageService chatMessageService;
	
	@PostMapping("/addcustomer")
	public ResponseEntity<?> addCustomer(@Valid @RequestBody Customer customer){
		customerService.add(customer);
		return defaultResponse();
	}
	
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
	public ResponseEntity<?> listMessage(@RequestBody Customer customer){
		return response(new ResponseBean(chatMessageService.getMessage(customer)));
	}
	
	
}
