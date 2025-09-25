package com.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.dto.ResponseBean;
import com.user.repository.AuthorityRepository;
import com.user.service.AuthorityService;
import com.user.service.DashBoardService;
import com.user.service.UserService;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/authority")
@AllArgsConstructor
@CrossOrigin("*")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorityController extends BaseController{
	
	final AuthorityService authorityService;
	
	@GetMapping("/getRole")
	public ResponseEntity<?> getRole(@RequestParam String username){
		try {
			return response(new ResponseBean(authorityService.list(username)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return responseError(new ResponseBean(e.getMessage()), e);
		}
	}

}
