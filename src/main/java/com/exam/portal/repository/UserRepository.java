package com.exam.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.portal.entites.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByUserName(String userName);
}
