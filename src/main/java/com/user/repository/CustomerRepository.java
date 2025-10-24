package com.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{


}
