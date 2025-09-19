package com.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.model.Authority;

public interface AuthorityRepository extends JpaRepository<Authority, Object> {
	
	List<Authority> findByUsername(String username) throws Exception;
}
