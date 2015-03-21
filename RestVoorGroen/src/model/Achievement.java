package model;

public class Achievement {
	private int id;
	private double value;
	private int activity_id;
	private Activity activity;
	private String naam;
	
	public Achievement(){}
	
	public Achievement(int id, double value, int activity_id, Activity activity, String naam) {
		super();
		this.id = id;
		this.value = value;
		this.activity_id = activity_id;
		this.activity = activity;
		this.naam = naam;
	}
	
	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public int getActivity_id() {
		return activity_id;
	}
	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}
	public Activity getActivity() {
		return activity;
	}
	public void setActivity(Activity activity) {
		this.activity = activity;
	}
}
