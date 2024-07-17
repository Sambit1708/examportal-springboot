package com.exam.portal.services.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.portal.entites.exam.Question;
import com.exam.portal.entites.exam.Quiz;
import com.exam.portal.repository.QuestionRepository;
import com.exam.portal.services.QuestionService;
import com.exam.portal.services.QuizService;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private QuizService quizService;

	@Override
	public Question addQuestion(Question question) {
		return this.questionRepository.save(question);
	}

	@Override
	public Question updateQuestion(Long quesId, Question quesDto) {
		Question question = this.getQuestion(quesId);
		
		if(question == null) {
			return null;
		}
		if(!quesDto.getContent().isEmpty()) {
			question.setContent(quesDto.getContent());
		}
		if(!quesDto.getAnswer().isEmpty()) {
			question.setAnswer(quesDto.getAnswer());
		}
		if(!quesDto.getOption1().isEmpty()) {
			question.setOption1(quesDto.getOption1());
		}
		if(!quesDto.getOption2().isEmpty()) {
			question.setOption2(quesDto.getOption2());
		}
		if(!quesDto.getOption3().isEmpty()) {
			question.setOption3(quesDto.getOption3());
		}
		if(!quesDto.getOption4().isEmpty()) {
			question.setOption4(quesDto.getOption4());
		}
		
		return this.questionRepository.save(question);
	}

	@Override
	public Set<Question> getQuestion() {
		return new LinkedHashSet<>(this.questionRepository.findAll());
	}

	@Override
	public Question getQuestion(Long questionId) {
		Optional<Question> question = this.questionRepository.findById(questionId);
		if(question.isEmpty()) {
			return null;
		}
		return question.get();
	}

	@Override
	public void deleteQuestion(Long questionId) {
		Question question = this.getQuestion(questionId);
		if(question == null) {
			return;
		}
		this.questionRepository.delete(question);
	}

	@Override
	public List<Question> getQuestionByQuiz(Long quizId) {
		Quiz quiz = this.quizService.getQuiz(quizId);
		Set<Question> questions = this.questionRepository.findByQuiz(quiz);
		List<Question> list = new ArrayList<>(questions);
		if(list.size() > Integer.parseInt(quiz.getNoOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNoOfQuestions() + 1));
		}
		
		list.forEach((q) -> {
			q.setAnswer("");
		});
		
		Collections.shuffle(Arrays.asList(list));
		return list;
	}
}