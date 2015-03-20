package resources;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.Model;
import model.User;
import database.DatabaseHelper;

@Path("/user")
public class _User{

	@Context ServletContext context;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public ArrayList<User> getUsers() {
		Model model = (Model) context.getAttribute("model");
		try {
			String sql = "SELECT * FROM  `users`;";
			PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
			ResultSet set = stat.executeQuery();
			return model.getUserBySet(set);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new  WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response addUser(User user) {
		context.getAttribute("model");		
		try {
			String sql = "INSERT ignore INTO `users` (inlognaam, wachtwoord, fitbitid, naam) VALUES (?,?,?,?);";
			PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
			stat.setString(1, user.getInlognaam());
			stat.setString(2, user.getWachtwoord());
			stat.setString(3, user.getFitbitid());
			stat.setString(4, user.getNaam());
			stat.execute();
			throw new WebApplicationException(Response.Status.CREATED);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new  WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
	}
	
	@DELETE
	@Path("/delete")
	public Response deleteUser(@QueryParam("id") int id) {
		try {
			String sql = "DELETE FROM `users` WHERE id=?";
			PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
			stat.setInt(1, id);
			stat.execute();
			throw new WebApplicationException(Response.Status.ACCEPTED);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new  WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
	}
}
