package model.entities;

public class User {
	private String name;
	private Integer points;
	
	public User() {
		this.name = "Desafiante";
		this.points = 0;
	}

	public Integer getPoints() {
		return points;
	}

	public void setPoints(Integer points) {
		this.points = points;
	}

	public String getName() {
		return name;
	}
	
}
