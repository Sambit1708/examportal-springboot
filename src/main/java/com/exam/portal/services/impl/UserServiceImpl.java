package com.exam.portal.services.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.portal.entites.User;
import com.exam.portal.entites.UserRole;
import com.exam.portal.repository.RoleRepository;
import com.exam.portal.repository.UserRepository;
import com.exam.portal.services.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {

		User local = this.userRepository.findByUserName(user.getUserName());
		if(local != null) {
			System.out.println("User is already there !!");
			throw new Exception("User is already there !!");
		} else {
			// create user
			for(UserRole r : userRoles) {
				this.roleRepository.save(r.getRole());
			}
			user.getUserRoles().addAll(userRoles);
			local = this.userRepository.save(user);
		}
		return local;
	}
	
	@Override
	public User getUser(String username) {
		User user = this.userRepository.findByUserName(username);
		return user;
	}

	@Override
	public void deleteUserByid(Long id) {
		
		this.userRepository.deleteById(id);
	}
	
	@Override
	public List<User> getAllUser() {
		return this.userRepository.findAll();
	}

	@Override
	public User updateUser(User user) {
		return this.userRepository.save(user);
	}

}
