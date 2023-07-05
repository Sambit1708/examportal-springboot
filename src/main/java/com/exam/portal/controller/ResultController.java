package com.exam.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.entites.User;
import com.exam.portal.entites.exam.Question;
import com.exam.portal.entites.exam.Quiz;
import com.exam.portal.entites.exam.Result;
import com.exam.portal.services.QuestionService;
import com.exam.portal.services.ResultService;

@CrossOrigin("*")
@RestController
@RequestMapping("/result")
public class ResultController {
	
	@Autowired
	private ResultService resultService;
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/recent-result")
	public ResponseEntity<?> getRecentResult() {
		return ResponseEntity.ok(this.resultService.getRecentResult());
	}
	
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evaluateQuiz(@RequestBody List<Question> questions) {
		
		var wrapper = new Object() { 
			int correctAns = 0; 
			int attempted = questions.size();
			double markGot = 0;
		};
		
		questions.forEach(q -> {
			// Single Question
			Question question = this.questionService.getQuestion(q.getQuesid());
			if(question.getAnswer().trim().equals(q.getChoosedAnswer())) {
				wrapper.correctAns++;
				double singleMark = Double.parseDouble(questions.get(0).getQuiz().getMaxMark()) / questions.size();
				wrapper.markGot += singleMark;	
			}
			
			if(q.getChoosedAnswer() == null || q.getChoosedAnswer().equals("")) {
				wrapper.attempted--;
			}
		});
		
		User user = new User();
		String uid = questions.get(0).getDefaultUserId();
		user.setId(Long.parseLong(uid));
		
		Result result = new Result(String.valueOf(wrapper.correctAns), String.valueOf(wrapper.attempted), wrapper.markGot, user, questions.get(0).getQuiz());
		this.resultService.addResult(result);
		
		return ResponseEntity.ok(result);
	}
	
	@PostMapping("/check-result")
	public Result getResultByQuizAndUser(@RequestBody Result result) {
		User user = result.getUser();
		Quiz quiz = result.getQuiz();
		return this.resultService.findByUserAndQuiz(user, quiz);
	}
	
	@GetMapping("/{id}")
	public Result getResultById(@PathVariable("id") Long id) {
		return this.resultService.findResultById(id);
	}
}
