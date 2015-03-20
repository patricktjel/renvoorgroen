package model;

public class Sponsor {
	private int id;
	private String naam;
	private String emailadres;
	
	public Sponsor(){}
	
	public Sponsor(String naam, String emailadres){
		this.naam = naam;
		this.emailadres = emailadres;
	}
	
	public Sponsor(int id, String naam, String emailadres){
		this.id = id;
		this.naam = naam;
		this.emailadres = emailadres;
	}
}
