package model.entities;

public class OptionRandom {

	private Option option;
	private Integer random;
	
	public OptionRandom(Option option, Integer random) {
		this.option = option;
		this.random = random;
	}

	public Option getOption() {
		return option;
	}

	public Integer getRandom() {
		return random;
	}
	
}
