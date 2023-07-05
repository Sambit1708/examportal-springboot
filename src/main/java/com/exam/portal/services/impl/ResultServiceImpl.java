package com.exam.portal.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.portal.entites.User;
import com.exam.portal.entites.exam.Quiz;
import com.exam.portal.entites.exam.Result;
import com.exam.portal.repository.ResultRepository;
import com.exam.portal.services.ResultService;

@Service
public class ResultServiceImpl implements ResultService {

	@Autowired
	private ResultRepository resultRepository;
	
	@Override
	public Result addResult(Result result) {
		return this.resultRepository.save(result);
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
	public Result findByUserAndQuiz(User user, Quiz quiz) {
		return this.resultRepository.findByUserAndQuiz(user, quiz);
	}


}
