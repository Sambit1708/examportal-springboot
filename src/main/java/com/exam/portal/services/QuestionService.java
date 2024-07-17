package com.exam.portal.services;

import java.util.List;
import java.util.Set;

import com.exam.portal.entites.exam.Question;

public interface QuestionService {

	public Question addQuestion(Question question);
	
	public Question updateQuestion(Long questionId, Question question);
	
	public Set<Question> getQuestion();
	
	public Question getQuestion(Long questionId);
	
	public void deleteQuestion(Long questionId);
	
	public List<Question> getQuestionByQuiz(Long quizId);
}
