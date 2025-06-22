package com.GSTUtils.server.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.GSTUtils.server.Model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByusername(String username);

}
