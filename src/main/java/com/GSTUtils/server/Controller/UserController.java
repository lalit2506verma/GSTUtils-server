package com.GSTUtils.server.Controller;

import java.util.ArrayList;
import java.util.List;

import com.GSTUtils.server.Model.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.GSTUtils.server.Model.Role;
import com.GSTUtils.server.Model.User;
import com.GSTUtils.server.Model.UserRole;
import com.GSTUtils.server.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	@PostMapping("/")
	public ResponseEntity<?> createUser(@RequestBody User user) throws Exception {
		try {
			// Creating Default Roles
			Role role = new Role();
			role.setRoleName("NORMAL");

			UserRole userRole = new UserRole();
			userRole.setUser(user);
			userRole.setRole(role);

			List<UserRole> roleList = new ArrayList<>();
			roleList.add(userRole);

			user.setActive(true);

			//BCrypting  password
			user.setPassword(encoder.encode(user.getPassword()));

			User NewUser = this.userService.createUser(user, roleList);

			return ResponseEntity.ok(new GenericResponse("success", "Registration Sucessfully Completed!"));
		}
		catch(Exception e){
			return ResponseEntity
					.badRequest()
					.body(new GenericResponse("error", e.getMessage()));
		}
	}
	
	
	@PutMapping("/{username}")
	public User updateUser(@PathVariable String username, @RequestBody User user) throws Exception {
		
		return this.userService.updateUser(user, username);
	}
	
	
	@GetMapping("/username/{username}")
	public User getUserByusername(@PathVariable String username) {
		
		return this.userService.findUserByUsername(username);
	}
	
	
	@GetMapping("/id/{userID}")
	public User getUserByUserID(@PathVariable Long userID) {
		
		return this.userService.findByUserId(userID);
		
	}
	
	@DeleteMapping("/{username}")
	public void deleteUser(@PathVariable String username) throws Exception {
		
		this.userService.deleteUser(username);
	}
}
