package com.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.user.dto.Account;
import com.user.model.User;

public interface UserRepository extends JpaRepository<User,String> {
	
	Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
	
	@Query(value = "select \n"
			+ "	count(username) as `total` \n"
			+ "	,sum(case when enabled = 0 then 1 else 0 end) as 'numberNotActive' \n"
			+ "	,sum(case when enabled = 1 then 1 else 0 end) as 'numberActive' \n"
			+ "from users;",nativeQuery = true)
	Account getDataDashBoardAccount();
	
	@Modifying
	@Transactional
	@Query("UPDATE User u SET u.password = :password WHERE u.username = :username")
	int updatePassword(@Param("username") String username, @Param("password") String password);


}