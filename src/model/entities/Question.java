package model.entities;

public class Question {

	
	private String question;
	private Option option1;
	private Option option2;
	private Option option3;
	private Option option4;
	private String engTrad;
	private String ptTrad;
	
	public Question(String question, Option option1, Option option2, Option option3, Option option4, String engTrad,
			String ptTrad) {
		this.question = question;
		this.option1 = option1;
		this.option2 = option2;
		this.option3 = option3;
		this.option4 = option4;
		this.engTrad = engTrad;
		this.ptTrad = ptTrad;
	}

	public String getQuestion() {
		return question;
	}

	public Option getOption1() {
		return option1;
	}

	public Option getOption2() {
		return option2;
	}

	public Option getOption3() {
		return option3;
	}

	public Option getOption4() {
		return option4;
	}

	public String getEngTrad() {
		return engTrad;
	}

	public String getPtTrad() {
		return ptTrad;
	}
	
	
	
	
	
	
}
