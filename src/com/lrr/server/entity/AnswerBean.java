package com.lrr.server.entity;

public class AnswerBean {

	private int answerId;		//属性名小写！！！
	private int quesId;
	private String teaContent;
	
	
	public AnswerBean(int answerId, int quesId, String teaContent) {
		super();
		this.answerId = answerId;
		this.quesId = quesId;
		this.teaContent = teaContent;
	}

	public AnswerBean(int quesId, String teaContent) {
		super();
		this.quesId = quesId;
		this.teaContent = teaContent;
	}

	public AnswerBean() {
		// TODO Auto-generated constructor stub
	}
	
	public int getAnswerId() {
		return answerId;
	}


	public void setAnswerId(int answerId) {
		this.answerId = answerId;
	}


	public int getQuesId() {
		return quesId;
	}


	public void setQuesId(int quesId) {
		this.quesId = quesId;
	}


	public String getTeaContent() {
		return teaContent;
	}


	public void setTeaContent(String teaContent) {
		this.teaContent = teaContent;
	}
   
	
    
    
}
