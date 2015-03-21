package model;

public class BestUser {
	private User user;
	private double value;
	
	public BestUser(){}
	
	public BestUser(User user, double value) {
		super();
		this.user = user;
		this.value = value;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	
	
}
