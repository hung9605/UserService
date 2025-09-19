package com.user.service;

import java.util.List;

import com.user.model.Authority;

public interface AuthorityService {
	
	List<Authority> list(String username) throws Exception;
	Authority add(Authority authority) throws Exception;
	List<Authority> updateAuthority(List<Authority> authorities) throws Exception;
}
