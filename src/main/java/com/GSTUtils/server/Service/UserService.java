package com.GSTUtils.server.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.GSTUtils.server.Model.User;
import com.GSTUtils.server.Model.UserRole;

@Service
public interface UserService {
	
	// Add new User
	public User createUser(User user, List<UserRole> userRoles) throws Exception;
	
	//Update User
	public User updateUser(User user, String username) throws Exception;
	
	//Delete User
	public void deleteUser(String username) throws Exception;
	
	//Find user by username
	public User findUserByUsername(String username);
	
	//Find user by UserID
	public User findByUserId(Long userID);	

}
