package com.exam.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.portal.entites.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByRoleName(String roleName);
}
