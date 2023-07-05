package com.exam.portal.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.portal.entites.exam.Category;
import com.exam.portal.entites.exam.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	Set<Quiz> findByCategory(Category category);
	
	List<Quiz> findByActive(boolean active);
	
	List<Quiz> findByCategoryAndActive(Category category, boolean active);
}
