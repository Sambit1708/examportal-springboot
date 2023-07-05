package com.exam.portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.exam.portal.entites.User;
import com.exam.portal.entites.exam.Quiz;
import com.exam.portal.entites.exam.Result;

public interface ResultRepository extends JpaRepository<Result, Long> {

	public List<Result> findByUser(User user);
	
	@Query("FROM Result res ORDER BY res.createDate DESC LIMIT 5")
	public List<Result> findRecentResult();
	
	public Result findByUserAndQuiz(User user, Quiz quiz);
}
