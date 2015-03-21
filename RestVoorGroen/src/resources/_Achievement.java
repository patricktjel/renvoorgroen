package resources;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Model;
import model.Achievement;
import database.DatabaseHelper;

@Path("/achievement")
public class _Achievement {
@Context ServletContext context;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public ArrayList<Achievement> getAchievements() {
		Model model = (Model) context.getAttribute("model");
		try {
			String sql = "SELECT * FROM  `achievements`;";
			PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
			ResultSet set = stat.executeQuery();
			return model.getAchievementsBySet(set);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new  WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
	}
}
