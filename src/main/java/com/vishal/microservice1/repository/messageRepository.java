package com.vishal.microservice1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vishal.microservice1.entity.messageinfo;

public interface messageRepository extends JpaRepository<messageinfo,Integer>{

	@Query(value = "SELECT * FROM messageinfo WHERE username1 = ?1", nativeQuery = true)
	 List<messageinfo> getMessageOfUser(String username);
	 
//	 String getPhoneNumber(String username)
}
