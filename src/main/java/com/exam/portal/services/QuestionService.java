package com.exam.portal.services;

import java.util.Set;

import com.exam.portal.entites.exam.Question;
import com.exam.portal.entites.exam.Quiz;

public interface QuestionService {

	public Question addQuestion(Question question);
	
	public Question udateQuestion(Question question);
	
	public Set<Question> getQuestion();
	
	public Question getQuestion(Long questionId);
	
	public void deleteQuestion(Long questionId);
	
	public Set<Question> getQuestionByQuiz(Quiz quiz);
}
