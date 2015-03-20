package model;

public class DataFitbit {
	private String fitbitid;
	private double floors;
	private double steps;
	private double distance;
	
	public DataFitbit(){}
	
	public DataFitbit(String fitbitid, double floors, double steps, double distance){
		this.fitbitid = fitbitid;
		this.floors = floors;
		this.steps = steps;
		this.distance = distance;
	}

	public String getFitbitid() {
		return fitbitid;
	}

	public void setFitbitid(String fitbitid) {
		this.fitbitid = fitbitid;
	}

	public double getFloors() {
		return floors;
	}

	public void setFloors(int floors) {
		this.floors = floors;
	}

	public double getSteps() {
		return steps;
	}

	public void setSteps(int steps) {
		this.steps = steps;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
}