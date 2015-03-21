package resources;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.BestUser;
import model.User;
import database.DatabaseHelper;

@Path("/best")
public class _Best {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/floors")
	public BestUser getBestFloorsUser() {
		try {
			String sql = "SELECT * FROM `user_activity` WHERE activity_id=? ORDER BY `value` DESC LIMIT 1";
			PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
			stat.setInt(1, 1);
			ResultSet set = stat.executeQuery();
			set.next();
			Double value = set.getDouble("value");
			int users_id = set.getInt("users_id");
			User user = getuserbyId(users_id);
			return new BestUser(user, value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new  WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/steps")
	public BestUser getBestStepsUser() {
		try {
			String sql = "SELECT * FROM `user_activity` WHERE activity_id=? ORDER BY `value` DESC LIMIT 1";
			PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
			stat.setInt(1, 2);
			ResultSet set = stat.executeQuery();
			set.next();
			Double value = set.getDouble("value");
			int users_id = set.getInt("users_id");
			User user = getuserbyId(users_id);
			return new BestUser(user, value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new  WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/distance")
	public BestUser getBestDistanceUser() {
		try {
			String sql = "SELECT * FROM `user_activity` WHERE activity_id=? ORDER BY `value` DESC LIMIT 1";
			PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
			stat.setInt(1, 3);
			ResultSet set = stat.executeQuery();
			set.next();
			Double value = set.getDouble("value");
			int users_id = set.getInt("users_id");
			User user = getuserbyId(users_id);
			return new BestUser(user, value);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new  WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
	}
	
	private User getuserbyId(int id) throws SQLException{
		String sql = "SELECT * FROM  `users` WHERE id=?;";
		PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
		stat.setInt(1, id);
		ResultSet set = stat.executeQuery();
		set.next();
		String fitbitid = set.getString("fitbitid");
		String wachtwoord = set.getString("wachtwoord");
		String inlognaam = set.getString("inlognaam");
		String naam = set.getString("naam");
		return new User(id, fitbitid, wachtwoord, inlognaam, naam);
	}
}
