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
	
	private ArrayList<Archivement> archivementsSteps, archivementsDistance, archivementsFloor;
	public static Model getInstance(){
		if(model == null){
			model = new Model();
		}
		return model;
	}
	
	public Model(){
		archivementsSteps = new ArrayList<Archivement>();
		archivementsDistance = new ArrayList<Archivement>();
		archivementsFloor = new ArrayList<Archivement>();
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

	public void addArchivement(Archivement archivement) {
		// 1 floor
		// 2 steps
		// 3 distance
		switch (archivement.getId()) {
		case 1: archivementsFloor.add(archivement); break;
		case 2: archivementsSteps.add(archivement); break;
		case 3: archivementsDistance.add(archivement); break;
			
		}
		
	}
	
	public ArrayList<Archivement> getArchivementsSteps() {
		return archivementsSteps;
	}

	public ArrayList<Archivement> getArchivementsDistance() {
		return archivementsDistance;
	}

	public ArrayList<Archivement> getArchivementsFloor() {
		return archivementsFloor;
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
	
	
	
	public Archivement getArchivementSteps() {
		for(Archivement a : archivementsSteps){
			if(steps < a.getValue()){
				return a;
			}
		}
		return archivementsSteps.get(archivementsSteps.size()-1);
	}

	public Archivement getArchivementDistance() {
		for(Archivement a : archivementsDistance){
			if(distance < a.getValue()){
				return a;
			}
		}
		return archivementsDistance.get(archivementsDistance.size()-1);
	}

	public Archivement getArchivementFloor() {
		for(Archivement a : archivementsFloor){
			if(floor < a.getValue()){
				return a;
			}
		}
		return archivementsFloor.get(archivementsFloor.size()-1);
	}

	
	
}
