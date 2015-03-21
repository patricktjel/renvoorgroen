package nl.rrp.renvoorgroen;

import org.json.JSONException;
import org.json.JSONObject;

public class Archivement {
	
	private int id;
	private String name;
	
	private double value;
	public Archivement(JSONObject j) throws JSONException{
		value = j.getDouble("value");
		name = "JE MOEDER!!!!!";
		//name = j.getString("name");
		id = j.getInt("activity_id");
		
		
	}
	
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	// 1 floor
			// 2 steps
			// 3 distance
	public double progresProcent(){
		double progress;
		switch (id) {
		case 1: progress = Model.getInstance().getFloor();  break;
		case 2: progress = Model.getInstance().getSteps();  break;
		case 3: progress = Model.getInstance().getDistance();  break;
		default: progress = 0;
		}
		return (Math.round(((float) progress / value) * 100));
	}

	public String getShow() {
		double progress;
		switch (id) {
		case 1: progress = Model.getInstance().getFloor();  break;
		case 2: progress = Model.getInstance().getSteps();  break;
		case 3: progress = Model.getInstance().getDistance();  break;
		default: progress = 0;
		}
		if(id ==3){
			return progress + " / " + value + " km";	
		}
		return progress + " / " + value;
	}

	
	public int getRadian(){
		double progress;
		switch (id) {
		case 1: progress = Model.getInstance().getFloor();  break;
		case 2: progress = Model.getInstance().getSteps();  break;
		case 3: progress = Model.getInstance().getDistance();  break;
		default: progress = 0;
		}
		return (int) ((progress / value) * 100);
	}

}
