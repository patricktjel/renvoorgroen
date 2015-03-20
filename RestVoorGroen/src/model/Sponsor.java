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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getEmailadres() {
		return emailadres;
	}

	public void setEmailadres(String emailadres) {
		this.emailadres = emailadres;
	}
	
	
}
