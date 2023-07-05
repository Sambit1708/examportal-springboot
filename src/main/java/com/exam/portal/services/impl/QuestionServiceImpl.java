package com.exam.portal.services.impl;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.portal.entites.exam.Question;
import com.exam.portal.entites.exam.Quiz;
import com.exam.portal.repository.QuestionRepository;
import com.exam.portal.services.QuestionService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;

	@Override
	public Question addQuestion(Question question) {
		return this.questionRepository.save(question);
	}

	@Override
	public Question udateQuestion(Question question) {
		return this.questionRepository.save(question);
	}

	@Override
	public Set<Question> getQuestion() {
		return new LinkedHashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long questionId) {
		return this.questionRepository.findById(questionId).get();
	}

	@Override
	public void deleteQuestion(Long questionId) {
		Question question = new Question();
		question.setQuesid(questionId);
		this.questionRepository.delete(question);
	}

	@Override
	public Set<Question> getQuestionByQuiz(Quiz quiz) {
		return this.questionRepository.findByQuiz(quiz);
	}


}
