package com.exam.portal.entites.exam;

import java.util.Date;

import com.exam.portal.entites.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "quiz_result")
public class Result {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="result_id")
	long id;
	private String correct;
	private String attempted;
	private double mark;
	
	@Column(name="create_date")
	private Date createDate = new Date();
	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Quiz quiz;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCorrect() {
		return correct;
	}

	public void setCorrect(String correct) {
		this.correct = correct;
	}

	public String getAttempted() {
		return attempted;
	}

	public void setAttempted(String attempted) {
		this.attempted = attempted;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Result() {}

	public Result(String correct, String attempted, double mark, User user, Quiz quiz) {
		this.correct = correct;
		this.attempted = attempted;
		this.mark = mark;
		this.user = user;
		this.quiz = quiz;
	}
}
