package nl.rrp.renvoorgroen;

import java.util.ArrayList;
import java.util.Observable;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class Model extends Observable {

	private final String CONSUMER = "8a2971d28d0f42eb85772988cf2d9796";
	

	private final String CONSUMER_SECRET = "758cf173fac54467832191a2aed23ff2";
	
	private String accesToken;
	private String accesTokenS;
	private String UserId;
	
	private double steps,distance,floor;
	
	
	private static Model model;
	
	private ArrayList<Achievement> achievementsSteps, achievementsDistance, achievementsFloor;
	public static Model getInstance(){
		if(model == null){
			model = new Model();
		}
		return model;
	}
	
	public Model(){
		achievementsSteps = new ArrayList<Achievement>();
		achievementsDistance = new ArrayList<Achievement>();
		achievementsFloor = new ArrayList<Achievement>();
		steps =0;
		distance = 0;
		floor=0;
	}
	
	public void init(Context c){
		SharedPreferences p = c.getSharedPreferences("Groen", Context.MODE_PRIVATE);
		accesToken = p.getString("accesToken","");
		accesTokenS = p.getString("accesTokenS","");
		UserId = p.getString("UserID","");
		
		Log.d("P", accesTokenS);
		Log.d("a", accesToken);
	}
	
	public String getCONSUMER() {
		return CONSUMER;
	}

	public String getCONSUMER_SECRET() {
		return CONSUMER_SECRET;
	}

	public String getAccesToken() {
		return accesToken;
	}

	public String getAccesTokenS() {
		return accesTokenS;
	}

	public String getUserId() {
		return UserId;
	}

	public void addArchivement(Achievement archivement) {
		// 1 floor
		// 2 steps
		// 3 distance
		switch (archivement.getId()) {
		case 1: achievementsFloor.add(archivement); break;
		case 2: achievementsSteps.add(archivement); break;
		case 3: achievementsDistance.add(archivement); break;
			
		}
		
	}
	
	public ArrayList<Achievement> getAchievementsSteps() {
		return achievementsSteps;
	}

	public ArrayList<Achievement> getAchievementsDistance() {
		return achievementsDistance;
	}

	public ArrayList<Achievement> getAchievementsFloor() {
		return achievementsFloor;
	}

	public double getSteps() {
		return steps;
	}

	public void setSteps(double steps) {
		this.steps = steps;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getFloor() {
		return floor;
	}

	public void setFloor(double floor) {
		this.floor = floor;
	}
	
	public void change(){
		setChanged();
		notifyObservers();
		Log.d("change", "ah");
	}
	
	
	
	public Achievement getAchievementSteps() {
		for(Achievement a : achievementsSteps){
			if(steps < a.getValue()){
				return a;
			}
		}
		return achievementsSteps.get(achievementsSteps.size()-1);
	}

	public Achievement getArchivementDistance() {
		for(Achievement a : achievementsDistance){
			if(distance < a.getValue()){
				return a;
			}
		}
		return achievementsDistance.get(achievementsDistance.size()-1);
	}

	public Achievement getArchivementFloor() {
		for(Achievement a : achievementsFloor){
			if(floor < a.getValue()){
				return a;
			}
		}
		return achievementsFloor.get(achievementsFloor.size()-1);
	}

	
	
}
