package com.exam.portal.services.impl;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.portal.entites.exam.Category;
import com.exam.portal.entites.exam.Quiz;
import com.exam.portal.repository.QuizRepository;
import com.exam.portal.services.QuizService;

@Service
public class QuizServiceImpl implements QuizService {

	@Autowired
	private QuizRepository quizRepository;
	

	@Override
	public Quiz addQuiz(Quiz quiz) {
		return this.quizRepository.save(quiz);
	}

	@Override
	public Quiz udateQuiz(Long quizId, Quiz quizDto) {
		Quiz quiz = this.getQuiz(quizId);
		if(quiz == null) {
			return quiz;
		}
		if(quizDto.getCategory() != null) {
			quiz.setCategory(quizDto.getCategory());
		}
		if(!quizDto.getDescription().isEmpty()) {
			quiz.setDescription(quizDto.getDescription());
		}
		if(!quizDto.getTitle().isEmpty()) {
			quiz.setTitle(quizDto.getTitle());
		}
		if(!quizDto.getNoOfQuestions().isEmpty()) {
			quiz.setNoOfQuestions(quizDto.getNoOfQuestions());
		}
		if(!quizDto.getMaxMark().isEmpty()) {
			quiz.setMaxMark(quizDto.getMaxMark());
		}
		return this.quizRepository.save(quiz);
	}

	@Override
	public Set<Quiz> getQuizs() {
		return new LinkedHashSet<>(this.quizRepository.findAll());
	}

	@Override
	public Quiz getQuiz(Long quizId) {
		Optional<Quiz> quiz = this.quizRepository.findById(quizId);
		if(quiz.isEmpty()) {
			return null;
		}
		return quiz.get();
	}

	@Override
	public void deleteQuiz(Long quizId) {
		this.quizRepository.deleteById(quizId);
	}

	@Override
	public Set<Quiz> getQuizByCategory(Category cat) {
		return this.quizRepository.findByCategory(cat);
	}

	@Override
	public List<Quiz> getActiveQuizes() {
		return this.quizRepository.findByActive(true);
	}

	@Override
	public List<Quiz> getActiveQuizesOfCategory(Category cat) {
		return this.quizRepository.findByCategoryAndActive(cat, true);
	}
}
