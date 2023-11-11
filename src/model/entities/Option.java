package model.entities;

public class Option {
	
	private String word;
	private Boolean answer;
	
	public Option(String word, Boolean answer) {
		this.word = word;
		this.answer = answer;
	}

	public String getWord() {
		return word;
	}

	public Boolean getAnswer() {
		return answer;
	}

	public void setAnswer(Boolean answer) {
		this.answer = answer;
	}
	
	
	
	
}
