package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseHelper;


public class Model {

	public Model(){
		DatabaseHelper.createDatabase();
	}

	/**
	 * Get the articles by the give result set
	 * 
	 * @param set
	 *            The result set
	 * @return the list with articles
	 * @throws SQLException
	 */
	public ArrayList<User> getUserBySet(ResultSet set) throws SQLException {
		// The list with articles
		ArrayList<User> users = new ArrayList<User>();
		while (set.next()) {
			// Set variables
			int id = set.getInt("id");
			String fitbitid = set.getString("fitbitid");
			String wachtwoord = set.getString("wachtwoord");
			String inlognaam = set.getString("inlognaam");
			String naam = set.getString("naam");
			users.add(new User(id, fitbitid, wachtwoord, inlognaam, naam));
		}

		return users;
	}
	
	/**
	 * Get the articles by the give result set
	 * 
	 * @param set
	 *            The result set
	 * @return the list with articles
	 * @throws SQLException
	 */
	public ArrayList<Sponsor> getSponsorsBySet(ResultSet set) throws SQLException {
		// The list with articles
		ArrayList<Sponsor> sponsors = new ArrayList<Sponsor>();
		while (set.next()) {
			// Set variables
			int id = set.getInt("id");
			String emailadres = set.getString("emailadres");
			String naam = set.getString("naam");
			sponsors.add(new Sponsor(id, naam, emailadres));
		}

		return sponsors;
	}
	
	public ArrayList<Activity> getActivitiesBySet(ResultSet set) throws SQLException {
		// The list with articles
		ArrayList<Activity> activities = new ArrayList<Activity>();
		while (set.next()) {
			// Set variables
			int id = set.getInt("id");
			String user_activity = set.getString("user_activity");
			activities.add(new Activity(id, user_activity));
		}

		return activities;
	}
}
