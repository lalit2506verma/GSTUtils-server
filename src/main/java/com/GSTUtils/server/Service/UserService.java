package com.GSTUtils.server.Service;

import java.util.ArrayList;

import javax.crypto.interfaces.DHPublicKey;

import com.GSTUtils.server.Model.User;
import com.GSTUtils.server.Model.UserRole;

public interface UserService {
	
	// Add new User
	public User createUser(User user, ArrayList<UserRole> userRoles);
	
	//Update User
	public User updateUser(User user);
	
	//Delete User
	public Long removeUser(String username);
	
	//Find user by username
	public User findUserByUsername(String username);
	
	//Find user by UserID
	public User findByUserId(Long userID);	

}
