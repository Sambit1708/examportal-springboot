package com.exam.portal.services;

import java.util.List;

import com.exam.portal.dto.EvalQuizDto;
import com.exam.portal.entites.User;
import com.exam.portal.entites.exam.Result;

public interface ResultService {

	public Result addResult(String username, EvalQuizDto evalQuizDto);
	
	public List<Result> getResultByUser(User user);
	
	public List<Result> getRecentResult();
	
	public Result findByUserAndQuiz(String username, String quizId);
	
	public Result findResultById(Long id);
	
	public List<Result> findResultOfQuiz(Long quizId);
	
	public List<Result> getAllResults();
	
	public List<Result> searchResults(String quiz, String firstName, String lastName);
	
}
