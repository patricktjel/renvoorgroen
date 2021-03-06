package resources;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.DataFitbit;
import model.Model;
import database.DatabaseHelper;

@Path("/data")
public class _Data {
	
	@Context ServletContext context;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public DataFitbit getUserByFitbitid(@QueryParam("id") String fitbitid) {
		try {
			int id = getuserId(fitbitid);
			String sql = "SELECT * FROM `user_activity` WHERE  users_id = ?";
			PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
			stat.setInt(1, id);
			ResultSet set = stat.executeQuery();
			double steps = 0;
			double floors = 0;
			double distance = 0;
			while(set.next()){
				int activity_id = set.getInt("activity_id");
				double value = set.getDouble("value");
				if(activity_id == 1){
					floors = value;
				} else if(activity_id == 2){
					steps = value;
				} else if(activity_id == 3){
					distance = value;
				}
			}
			return new DataFitbit(fitbitid, floors, steps, distance);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new  WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
	}
	
	private int getuserId(String fitbitid) throws SQLException{
		String sql = "SELECT * FROM  `users` WHERE fitbitid=?;";
		PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
		stat.setString(1, fitbitid);
		ResultSet set = stat.executeQuery();
		set.next();
		return set.getInt("id");
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/put")
	public Response putData(DataFitbit data) {
		Model model = (Model) context.getAttribute("model");
		try {
			//gets the user id
			int userid = getuserId(data.getFitbitid());
			
			//gets activity ids
			//gets floor id
			int floorsid = model.getActivityId("floors");
			insertData(userid, floorsid, data.getFloors());
			
			//gets steps id
			int stepsid = model.getActivityId("steps");
			insertData(userid, stepsid, data.getSteps());
			
			//gets distance id
			int distanceid = model.getActivityId("distance");
			insertData(userid, distanceid, data.getDistance());
			throw new WebApplicationException(Response.Status.CREATED);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new  WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
	}
	
	private void insertData(int userid, int activityid, double value) throws SQLException{
		String sql =  "SELECT * FROM `user_activity` WHERE users_id=? AND activity_id=?";
		PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
		stat.setInt(1, userid);
		stat.setInt(2, activityid);
		ResultSet set = stat.executeQuery();
		
		if(set.next()){
			//update
			int id = set.getInt("id");
			String update = "UPDATE `user_activity` SET value = ? WHERE id=?";
			PreparedStatement updatestat = DatabaseHelper.getConnection().prepareStatement(update);
			updatestat.setDouble(1, value);
			updatestat.setInt(2, id);
			updatestat.execute();
		} else {
			//insert
			String insert = "INSERT INTO `user_activity` (activity_id, users_id, value) VALUES (?,?,?);";
			PreparedStatement insertstat = DatabaseHelper.getConnection().prepareStatement(insert);
			insertstat.setInt(1, activityid);
			insertstat.setInt(2, userid);
			insertstat.setDouble(3, value);
			insertstat.execute();
		}
	}
}
