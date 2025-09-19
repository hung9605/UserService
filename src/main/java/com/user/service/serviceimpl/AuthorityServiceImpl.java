package com.user.service.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.user.model.Authority;
import com.user.repository.AuthorityRepository;
import com.user.service.AuthorityService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthorityServiceImpl implements AuthorityService{
	
	private final AuthorityRepository authorityRepository;

	@Override
	public List<Authority> list(String username) throws Exception {
		// TODO Auto-generated method stub
		return authorityRepository.findByUsername(username);
	}

	@Override
	public Authority add(Authority authority) throws Exception {
		// TODO Auto-generated method stub
		return authorityRepository.save(authority);
	}

	@Override
	public List<Authority> updateAuthority(List<Authority> authorities) throws Exception {
		// TODO Auto-generated method stub
		return authorityRepository.saveAll(authorities);
	}

}
