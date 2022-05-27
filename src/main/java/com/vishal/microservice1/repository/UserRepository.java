package com.vishal.microservice1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vishal.microservice1.entity.User;



public interface UserRepository extends JpaRepository<User,String> {
	
	@Query(value = "SELECT phonenumber FROM user WHERE username = ?1", nativeQuery = true)
	String getRecieverPhonenumber(String username);

}
