package com.exam.portal.services;

import java.util.List;
import java.util.Set;

import com.exam.portal.entites.User;
import com.exam.portal.entites.UserRole;

public interface UserService {

	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	// Get user by username
	public User getUser(String username);
	
	//Delete User by Id
	public void deleteUserByid(Long id);
	
	// TO get all user
	public List<User> getAllUser();
	
	public User updateUser(User user);
	
	public List<User> getNormalUsers();
}
