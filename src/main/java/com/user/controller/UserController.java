package com.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.constants.CommonConstant;
import com.user.dto.ResponseBean;
import com.user.model.User;
import com.user.service.UserService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin("*")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController extends BaseController {
	
	final UserService userService;
	
	@GetMapping("/list")
	public ResponseEntity<?> list(){
		try {
			return response(new ResponseBean(userService.list()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return responseError(new ResponseBean(e.getMessage()), e);
		}
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody User user){
		try {
			userService.add(user);
			return response(new ResponseBean(CommonConstant.OK));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return responseError(new ResponseBean(e.getMessage()), e);
		}
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> update(@RequestBody User user){
		try {
			userService.updateStatus(user);
			return response(new ResponseBean(CommonConstant.OK));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return responseError(new ResponseBean(e.getMessage()), e);
		}
	}

}
