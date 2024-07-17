package com.exam.portal.services.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.portal.dto.EvalQuizDto;
import com.exam.portal.entites.User;
import com.exam.portal.entites.exam.Question;
import com.exam.portal.entites.exam.Quiz;
import com.exam.portal.entites.exam.Result;
import com.exam.portal.repository.ResultRepository;
import com.exam.portal.services.QuestionService;
import com.exam.portal.services.QuizService;
import com.exam.portal.services.ResultService;
import com.exam.portal.services.UserService;

@Service
public class ResultServiceImpl implements ResultService {
	
	@Autowired
	private QuestionService questionService; 
	
	@Autowired
	private QuizService quizService;

	@Autowired
	private ResultRepository resultRepository;
	
	@Autowired
	private UserService userService;
	
	@Override
	public Result addResult(String username, EvalQuizDto evalQuizDto) {
		User user = this.userService.getUser(username);
		Quiz quiz = this.quizService.getQuiz(evalQuizDto.getQuizId());
		int maxMark = Integer.parseInt(quiz.getMaxMark());
		int noOfQues = Integer.parseInt(quiz.getNoOfQuestions());
		int correct = 0;
		int attempted = 0;
		double mark = 0;
		
		for(int i=0; i<evalQuizDto.getQuestionAnswer().size(); i++) {
			Question question = this.questionService.getQuestion(
										evalQuizDto.getQuestionAnswer().get(0).getQuestionId());
		
			if(question.getAnswer() == evalQuizDto.getQuestionAnswer().get(0).getChoosedAnswer()) 
			{
				correct++;
				mark += (maxMark / noOfQues);
			}

			attempted++;
		}
		Result result = new Result();
		result.setAttempted(String.valueOf(attempted));
		result.setCorrect(String.valueOf(correct));
		result.setCreateDate(LocalDateTime.now());
		result.setMark(mark);
		result.setUser(user);
		result.setQuiz(quiz);
		
		result = this.resultRepository.save(result);
		return result;
	}

	@Override
	public List<Result> getResultByUser(User user) {
		return this.resultRepository.findByUser(user);
	}

	@Override
	public List<Result> getRecentResult() {
		return this.resultRepository.findRecentResult();
	}

	@Override
	public Result findResultById(Long id) {
		return this.resultRepository.findById(id).get();
	}

	@Override
	public Result findByUserAndQuiz(String username, String quizId) {
		User user = this.userService.getUser(username);
		Quiz quiz = this.quizService.getQuiz(Long.parseLong(quizId));
		Optional<Result> result = this.resultRepository.findByUserAndQuiz(user, quiz);
		if(result.isEmpty()) {
			return null;
		}
		return result.get();
	}

	@Override
	public List<Result> findResultOfQuiz(Long quizId) {
		Quiz quiz = this.quizService.getQuiz(quizId);
		List<Result> resutlts = this.resultRepository.findByQuiz(quiz);
		return resutlts;
	}

	
	@Override
	public List<Result> getAllResults() {
		List<Result> results = this.resultRepository.findAll();
		return results;
	}

	@Override
	public List<Result> searchResults(String quiz, String firstName, String lastName) {
		List<Result> results = this.resultRepository.searchResults(quiz, firstName, lastName);
		return results;
	}


}
