package com.exam.portal.dto;

import java.util.List;

public class EvalQuizDto {

	private long quizId;
	
	private List<ChooseAnsDto> questionAnswer;

	public long getQuizId() {
		return quizId;
	}

	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}

	public List<ChooseAnsDto> getQuestionAnswer() {
		return questionAnswer;
	}

	public void setQuestionAnswer(List<ChooseAnsDto> questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
}
