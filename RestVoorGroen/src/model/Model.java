package model;

import java.sql.PreparedStatement;
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
	
	public ArrayList<Milestone> getMilestonesBySet(ResultSet set) throws SQLException{
		ArrayList<Milestone> milestones = new ArrayList<Milestone>();
		while(set.next()){
			int id = set.getInt("id");
			double value = set.getDouble("value");
			double bedrag = set.getDouble("bedrag");
			int activity_id = set.getInt("activity_id");
			int sponsor_id = set.getInt("sponsor_id");
			
			//get activity name
			String sql = "SELECT * FROM  `activity` WHERE id=?;";
			PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
			stat.setInt(1, activity_id);
			ResultSet activityset = stat.executeQuery();
			activityset.next();
			String activityName = activityset.getString("user_activity");
			
			//get sponsor name
			sql = "SELECT * FROM  `sponsor` WHERE id=?;";
			stat = DatabaseHelper.getConnection().prepareStatement(sql);
			stat.setInt(1, activity_id);
			activityset = stat.executeQuery();
			activityset.next();
			String sponsornaam = activityset.getString("naam");
			
			milestones.add(new Milestone(id, value, bedrag, activity_id, sponsor_id, activityName, sponsornaam));
		}
		return milestones;
	}
	
	public ArrayList<Milestone> filterMilestones(ArrayList<Milestone> milestones) throws SQLException{
		//total stats
		int totalsteps = getUserActivityTotal("steps");
		int totalfloors = getUserActivityTotal("floors");
		int totaldistance = getUserActivityTotal("distance");
		
		//selectmetn of milestones
		ArrayList<Milestone> results = new ArrayList<Milestone>();
		System.out.println(totalsteps + " " + totalfloors + " " + totaldistance);
		for (Milestone stone : milestones) {
			int activityid = stone.getActivity_id();
			if(activityid == 1 && stone.getValue() <= totalfloors){
				results.add(stone);
			} else if(activityid == 2 && stone.getValue() <= totalsteps){
				results.add(stone);
			} else if(activityid == 3 && stone.getValue() <= totaldistance){
				results.add(stone);
			}
		}
		
		return results;
	}
	
	private int getUserActivityTotal(String activity) throws SQLException{
		//get the activity id
		int activity_id = getActivityId(activity);
		
		//sum the amount
		String sql = "SELECT SUM(value) AS sum FROM  `user_activity` WHERE id=?;";
		PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
		stat.setInt(1, activity_id);
		ResultSet activityset = stat.executeQuery();
		activityset.next();
		return activityset.getInt("sum");
	}
	
	public int getActivityId(String activity) throws SQLException{
		String sql = "SELECT * FROM  `activity` WHERE user_activity=?;";
		PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
		stat.setString(1, activity);
		ResultSet set = stat.executeQuery();
		set.next();
		return set.getInt("id");
	}
	
	public ArrayList<Achievement> getAchievementsBySet(ResultSet set) throws SQLException{
		ArrayList<Achievement> achievements = new ArrayList<Achievement>();
		
		while(set.next()){
			int id = set.getInt("id");
			double value = set.getDouble("value");
			int activity_id = set.getInt("activity_id");
			
			//gets the activity name
			String sql = "SELECT * FROM  `activity` WHERE id=?;";
			PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
			stat.setInt(1, activity_id);
			ResultSet activityset = stat.executeQuery();
			activityset.next();
			String activity = activityset.getString("user_activity");
			String naam = set.getString("naam");

			achievements.add(new Achievement(id, value, activity_id, new Activity(activity_id, activity), naam));
		}
		
		return achievements;
	}
}
