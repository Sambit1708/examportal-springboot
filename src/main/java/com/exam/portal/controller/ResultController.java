package com.exam.portal.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.exam.portal.dto.EvalQuizDto;
import com.exam.portal.entites.exam.Result;
import com.exam.portal.services.ResultService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/result")
public class ResultController {
	
	@Autowired
	private ResultService resultService;
	
	
	@GetMapping("/recent-result")
	public ResponseEntity<?> getRecentResult() {
		return ResponseEntity.ok(this.resultService.getRecentResult());
	}
	
	@PostMapping("/eval-quiz")
	public ResponseEntity<?> evaluateQuiz(Principal principal, @RequestBody EvalQuizDto evalQuizDto) {
		Result result = this.resultService.addResult(principal.getName(), evalQuizDto);
		return new ResponseEntity<>(result, HttpStatus.CREATED);
	}
	
	@GetMapping("/check-result")
	public ResponseEntity<?> getResultByQuizAndUser(Principal principal, @RequestParam("quizId") String quizId) {
		Result result = this.resultService.findByUserAndQuiz(principal.getName(), quizId);
		if(result == null) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getResultById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.resultService.findResultById(id));
	}
	
	@GetMapping("/by-quiz")
	public ResponseEntity<List<Result>> getResultOfQuiz(@RequestParam("quizId") Long quizId) {
		List<Result> results = this.resultService.findResultOfQuiz(quizId);
		if(results.size() == 0) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
	
	@GetMapping("/get")
	public ResponseEntity<List<Result>> getAllResult() {
		List<Result> results = this.resultService.getAllResults();
		if(results.size() == 0) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
	
	@GetMapping("/search")
	public ResponseEntity<List<Result>> search(
								@RequestParam(name="quiz", defaultValue ="UN-KNOWN") String quiz,
								@RequestParam(name="firstName", defaultValue ="UN-KNOWN") String firstName,
								@RequestParam(name="lastName", defaultValue ="UN-KNOWN") String lastName)
	{
		List<Result> results = this.resultService.searchResults(quiz, firstName, lastName);
		if(results.size() == 0) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(results, HttpStatus.OK);
	}
}
