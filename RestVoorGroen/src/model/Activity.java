package model;

public class Activity {
	private int id;
	private String user_activity;
	
	public Activity(){}
	
	public Activity(String user_activity){
		this.user_activity = user_activity;
	}
	
	public Activity(int id, String user_activity){
		this.id = id;
		this.user_activity = user_activity;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_activity() {
		return user_activity;
	}
	public void setUser_activity(String user_activity) {
		this.user_activity = user_activity;
	}	
}