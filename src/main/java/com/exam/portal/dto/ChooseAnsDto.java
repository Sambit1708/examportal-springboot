package com.exam.portal.dto;


public class ChooseAnsDto {

	private long questionId;
	
	private String choosedAnswer;

	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public String getChoosedAnswer() {
		return choosedAnswer;
	}

	public void setChoosedAnswer(String choosedAnswer) {
		this.choosedAnswer = choosedAnswer;
	}
}
