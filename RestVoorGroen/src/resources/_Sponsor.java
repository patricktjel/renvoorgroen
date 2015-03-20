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
import model.Sponsor;
import database.DatabaseHelper;

@Path("/sponsor")
public class _Sponsor{

	@Context ServletContext context;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public ArrayList<Sponsor> getSponsors() {
		Model model = (Model) context.getAttribute("model");
		try {
			String sql = "SELECT * FROM  `sponsor`;";
			PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
			ResultSet set = stat.executeQuery();
			return model.getSponsorsBySet(set);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new  WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response addSponsor(Sponsor sponsor) {
		String sql = "INSERT ignore INTO `sponsor` (naam, emailadres) VALUES (?,?);";
		try {
			PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
			stat.setString(1, sponsor.getNaam());
			stat.setString(2, sponsor.getEmailadres());
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
	public Response deleSponsor(@QueryParam("id") int id) {
		try {
			String sql = "DELETE FROM `sponsor` WHERE id=?";
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