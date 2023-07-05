package com.exam.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.portal.entites.exam.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
