package com.frostia.coursera.computrivia;

public class AnswerQuestionPair {
	private String question;
	private String answer;
	private Category category;
	
	public AnswerQuestionPair(String question, String answer, Category category){
		this.question=question;
		this.answer=answer;
		this.category=category;
	}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "AnswerQuestionPair [question=" + question + ", answer="
				+ answer + ", category=" + category + "]";
	}
	
	
	
}
