package com.GSTUtils.server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GSTUtils.server.Model.User;

public interface UserRespository extends JpaRepository<User, Long>{
	
	User findByusername(String username);

}
