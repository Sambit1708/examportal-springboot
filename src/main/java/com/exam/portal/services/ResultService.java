package com.exam.portal.services;

import java.util.List;

import com.exam.portal.entites.User;
import com.exam.portal.entites.exam.Quiz;
import com.exam.portal.entites.exam.Result;

public interface ResultService {

	public Result addResult(Result result);
	
	public List<Result> getResultByUser(User user);
	
	public List<Result> getRecentResult();
	
	public Result findByUserAndQuiz(User user, Quiz quiz);
	
	public Result findResultById(Long id);
	
}
