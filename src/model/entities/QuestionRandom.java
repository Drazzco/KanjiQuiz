package model.entities;

public class QuestionRandom {

	private Question question;
	private Integer random;
	
	public QuestionRandom(Question question, Integer random) {
		this.question = question;
		this.random = random;
	}

	public Question getQuestion() {
		return question;
	}

	public Integer getRandom() {
		return random;
	}
	
}
