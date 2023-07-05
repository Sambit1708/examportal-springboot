package com.exam.portal.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.entites.exam.Question;
import com.exam.portal.entites.exam.Quiz;
import com.exam.portal.services.QuestionService;
import com.exam.portal.services.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private QuizService quizService;
	
	
	@PostMapping("/add-question")
	public ResponseEntity<Question> add(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.addQuestion(question));
	}
	
	@PostMapping("/update-question")
	public ResponseEntity<Question> update(@RequestBody Question question) {
		return ResponseEntity.ok(this.questionService.udateQuestion(question));
	}
	
	@GetMapping("/{qestionid}")
	public Question getQuestion(@PathVariable("qestionid") Long qid) {
		return this.questionService.getQuestion(qid);
	}
	
	@GetMapping("/quiz/{qid}")
	public ResponseEntity<?> getQuestionsOfQuiz(@PathVariable("qid") Long qid) {
		
		Quiz quiz = this.quizService.getQuiz(qid);
		Set<Question> questions = quiz.getQuestions();
		List<Question> list = new ArrayList<>(questions);
		if(list.size() > Integer.parseInt(quiz.getNoOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNoOfQuestions() + 1));
		}
		
		list.forEach((q) -> {
			q.setAnswer("");
		});
		
		Collections.shuffle(Arrays.asList(list));
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/Admin/quiz/{qid}")
	public ResponseEntity<?> getQuestionsforAdmin(@PathVariable("qid") Long qid) {
		
		Quiz quiz = this.quizService.getQuiz(qid);
		Set<Question> questions = quiz.getQuestions();
		List<Question> list = new ArrayList<>(questions);
		if(list.size() > Integer.parseInt(quiz.getNoOfQuestions())) {
			list = list.subList(0, Integer.parseInt(quiz.getNoOfQuestions() + 1));
		}
		
		Collections.shuffle(Arrays.asList(list));
		return ResponseEntity.ok(list);
	}
	
	@DeleteMapping("/delete-question/{qestionid}")
	public void deleteQuestion(@PathVariable("qestionid") Long qid) {
		this.questionService.deleteQuestion(qid);
	}
}
