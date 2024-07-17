package com.exam.portal.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.entites.exam.Category;
import com.exam.portal.entites.exam.Quiz;
import com.exam.portal.services.CategoryService;
import com.exam.portal.services.QuizService;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {

	@Autowired
	private QuizService quizService;
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping("/add-quiz")
	public ResponseEntity<Quiz> add(@RequestBody Quiz quiz) {
		return new ResponseEntity<>(this.quizService.addQuiz(quiz), HttpStatus.CREATED);
	}
	
	@PutMapping("/update-quiz/{quizId}")
	public ResponseEntity<Quiz> update(	@PathVariable("quizId") Long quizId,
										@RequestBody Quiz quiz) {
		quiz = this.quizService.udateQuiz(quizId, quiz);
		if(quiz == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Quiz>(quiz, HttpStatus.CREATED);
	}
	
	@GetMapping("/{qid}")
	public Quiz getQuiz(@PathVariable("qid") Long qid) {
		return this.quizService.getQuiz(qid);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getQuizes() {
		return ResponseEntity.ok(this.quizService.getQuizs());
	}
	
	@DeleteMapping("/delete-quiz/{qid}")
	public void deleteQuiz(@PathVariable("qid") Long qid) {
		this.quizService.deleteQuiz(qid);
	}
	
	@GetMapping("/get-quiz/{cId}")
	public ResponseEntity<?> getQuizByCategory(@PathVariable("cId") Long cid) {
		
		Category cat = this.categoryService.getCategory(cid);
		Set<Quiz> quiz = cat.getQizes();
		List<Quiz> quizList = new ArrayList<>(quiz);
		return ResponseEntity.ok(quizList);
	}
	
	@GetMapping("/active")
	public ResponseEntity<List<Quiz>> getActiveQuiz() {
		return new ResponseEntity<>(this.quizService.getActiveQuizes(), HttpStatus.OK);
	}
	
	@GetMapping("/category/active/{cid}")
	public List<Quiz> getActiveQuizByCategory(@PathVariable("cid") Long cid) {

		Category category = new Category();
		category.setId(cid);
		return this.quizService.getActiveQuizesOfCategory(category);
	}
}
