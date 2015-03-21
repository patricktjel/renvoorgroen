package model;

public class Milestone {
	private int id;
	private double value;
	private double bedrag;
	private int activity_id;
	private int sponsor_id;
	
	private String activityNaam;
	private String sponsorNaam;
	
	public Milestone(){}
	
	public Milestone(double value, double bedrag, int activity_id, int sponsor_id) {
		super();
		this.value = value;
		this.bedrag = bedrag;
		this.activity_id = activity_id;
		this.sponsor_id = sponsor_id;
	}

	public Milestone(int id, double value, double bedrag, int activity_id, int sponsor_id) {
		this.id = id;
		this.value = value;
		this.bedrag = bedrag;
		this.activity_id = activity_id;
		this.sponsor_id = sponsor_id;
	}

	public Milestone(int id, double value, double bedrag, int activity_id,
			int sponsor_id, String activityNaam, String sponsorNaam) {
		super();
		this.id = id;
		this.value = value;
		this.bedrag = bedrag;
		this.activity_id = activity_id;
		this.sponsor_id = sponsor_id;
		this.activityNaam = activityNaam;
		this.sponsorNaam = sponsorNaam;
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

	public double getBedrag() {
		return bedrag;
	}

	public void setBedrag(double bedrag) {
		this.bedrag = bedrag;
	}

	public int getActivity_id() {
		return activity_id;
	}

	public void setActivity_id(int activity_id) {
		this.activity_id = activity_id;
	}

	public int getSponsor_id() {
		return sponsor_id;
	}

	public void setSponsor_id(int sponsor_id) {
		this.sponsor_id = sponsor_id;
	}

	public String getActivityNaam() {
		return activityNaam;
	}

	public void setActivityNaam(String activityNaam) {
		this.activityNaam = activityNaam;
	}

	public String getSponsorNaam() {
		return sponsorNaam;
	}

	public void setSponsorNaam(String sponsorNaam) {
		this.sponsorNaam = sponsorNaam;
	}
	
}
