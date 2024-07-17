package com.exam.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.portal.entites.Role;
import com.exam.portal.entites.UserRole;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

	public List<UserRole> findByRole(Role role);
}
