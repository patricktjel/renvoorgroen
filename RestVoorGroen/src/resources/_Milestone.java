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

import model.Milestone;
import model.Model;
import database.DatabaseHelper;

@Path("/milestone")
public class _Milestone{

	@Context ServletContext context;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/get")
	public ArrayList<Milestone> getMilestones() {
		Model model = (Model) context.getAttribute("model");
		try {
			String sql = "SELECT * FROM  `milestones`;";
			PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
			ResultSet set = stat.executeQuery();
			return model.getMilestonesBySet(set);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		throw new  WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response addSponsor(Milestone milestone) {
		String sql = "INSERT ignore INTO `milestones` (value, bedrag, activity_id, sponsor_id) VALUES (?,?,?,?);";
		try {
			PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
			stat.setDouble(1, milestone.getValue());
			stat.setDouble(2, milestone.getBedrag());
			stat.setInt(3, milestone.getActivity_id());
			stat.setInt(4, milestone.getSponsor_id());
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
	public Response deleteMilestone(@QueryParam("id") int id) {
		try {
			String sql = "DELETE FROM `milestones` WHERE id=?";
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