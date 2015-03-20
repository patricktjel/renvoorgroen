package model;

public class User {
	private int id;
	private String fitbitid;
	private String wachtwoord;
	private String inlognaam;
	private String naam;
	
	public User(){}
	
	public User(String fitbitid, String wachtwoord, String inlognaam, String naam){
		this.fitbitid = fitbitid;
		this.wachtwoord = wachtwoord;
		this.inlognaam = inlognaam;
		this.naam = naam;
	}
	
	public User(int id, String fitbitid, String wachtwoord, String inlognaam, String naam){
		this.id = id;
		this.fitbitid = fitbitid;
		this.wachtwoord = wachtwoord;
		this.inlognaam = inlognaam;
		this.naam = naam;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public void setFitbitid(String fitbitid) {
		this.fitbitid = fitbitid;
	}
	public void setWachtwoord(String wachtwoord) {
		this.wachtwoord = wachtwoord;
	}
	public void setInlognaam(String inlognaam) {
		this.inlognaam = inlognaam;
	}
	public int getId() {
		return id;
	}
	public String getFitbitid() {
		return fitbitid;
	}
	public String getWachtwoord() {
		return wachtwoord;
	}
	public String getInlognaam() {
		return inlognaam;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}
	
}