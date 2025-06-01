package com.GSTUtils.server.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.GSTUtils.server.Exception.UserNotFoundException;
import com.GSTUtils.server.Model.User;
import com.GSTUtils.server.Model.UserRole;
import com.GSTUtils.server.Repository.RoleRepository;
import com.GSTUtils.server.Repository.UserRespository;
import com.GSTUtils.server.Service.UserService;

@Component
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRespository userRespository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User createUser(User user, List<UserRole> userRoles) throws Exception {
		// First will check user is already created for not
		String username = user.getUsername();
		User localUser = this.userRespository.findByusername(username);
		
		if(localUser != null) {
			// User exists
			System.out.println("User already Exist");
			throw new Exception("User already Exist");
		}
		
		// user does not exist with the same username
		
		// Saving roles from roleList
		for(UserRole userRole : userRoles) {
			this.roleRepository.save(userRole.getRole());
		}
		
		user.getUserRoles().addAll(userRoles);
		localUser = this.userRespository.save(user);
		
		return localUser;
	}

	@Override
	public User updateUser(User user, String username) throws Exception {
		
		// Check user exist with this username
		User localUser = findUserByUsername(username);
		
		if(localUser != null) {
			// User exists
			localUser.setLastName(user.getLastName());
			localUser.setEmail(user.getEmail());
			localUser.setPhoneNumber(user.getPhoneNumber());
			localUser.setState(user.getState());
		}
		else {
			System.out.println("UserName does not exist !!!");
			throw new Exception("Username does not exist !!!");
		}
		
		localUser = this.userRespository.save(localUser);
		return localUser;
		
	}

	@Override
	public void deleteUser(String username) throws Exception {
		User localUser = findUserByUsername(username);
		
		if(localUser == null) {
			System.out.println("UserName does not exist !!!");
			throw new Exception("Username does not exist !!!");
		}
		
		this.userRespository.delete(localUser);
	}

	@Override
	public User findUserByUsername(String username) {
		
		return this.userRespository.findByusername(username);
	}

	@Override
	public User findByUserId(Long userID) {
		
		return this.userRespository.findById(userID).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userID));
	}

}
