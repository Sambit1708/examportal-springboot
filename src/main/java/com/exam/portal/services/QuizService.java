package com.exam.portal.services;

import java.util.List;
import java.util.Set;

import com.exam.portal.entites.exam.Category;
import com.exam.portal.entites.exam.Quiz;

public interface QuizService {

	public Quiz addQuiz(Quiz quiz);
	
	public Quiz udateQuiz(Long quizId, Quiz quiz);
	
	public Set<Quiz> getQuizs();
	
	public Quiz getQuiz(Long quizId);
	
	public void deleteQuiz(Long quizId);
	
	public Set<Quiz> getQuizByCategory(Category cat);
	
	public List<Quiz> getActiveQuizes();
	
	public List<Quiz> getActiveQuizesOfCategory(Category cat);
}
