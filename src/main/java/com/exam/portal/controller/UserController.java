package com.exam.portal.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.entites.Role;
import com.exam.portal.entites.User;
import com.exam.portal.entites.UserRole;
import com.exam.portal.repository.RoleRepository;
import com.exam.portal.repository.UserRepository;
import com.exam.portal.services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	@PostMapping("/create-user")
	@ResponseBody
	public ResponseEntity<?> createUser(@RequestBody User user) throws Exception {

		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		Set<UserRole> userRoles = new HashSet<UserRole>();
		Role role = roleRepository.findByRoleName("NORMAL");
		UserRole userRole = new UserRole(user, role);
		userRoles.add(userRole);
		user.setProfile("default.png");
		user = userService.createUser(user, userRoles);
		
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username") String username) {
		return this.userService.getUser(username);
	}
	
	// delete User by id
	@DeleteMapping("/{user_id}")
	public void deleteUserbyId(@PathVariable("user_id") Long id) {
		this.userService.deleteUserByid(id);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getAllUser() {
		return ResponseEntity.ok(this.userService.getAllUser());
	}
	
	@GetMapping("/general")
	public ResponseEntity<?> getAllNormalUser() {
		return ResponseEntity.ok(this.userService.getNormalUsers());
	}
	
	@PostMapping("/update-user")
	public User updateUser(@RequestBody User user) {
		User user1 = this.userRepository.getReferenceById(user.getId());
		user1.setEmail(user.getEmail());
		user1.setFirstName(user.getFirstName());
		user1.setLastName(user.getLastName());
		user1.setPhone(user.getPhone());
		return this.userService.updateUser(user1);
	}
	
	@PostMapping("/user-action")
	public User UserAction(@RequestBody User user) {
		User user1 = this.getUser(user.getUsername());
		user1.setEnabled(user.isEnabled());
		return this.userService.updateUser(user1);
	}
}
