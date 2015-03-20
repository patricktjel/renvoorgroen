//package resources;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import javax.servlet.ServletContext;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//import model.Model;
//import model.Sponsor;
//import database.DatabaseHelper;
//
//@Path("/sponsor")
//public class _Sponsor{
//
//	@Context ServletContext context;
//	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("/add")
//	public Response getUsers(Sponsor sponsor) {
//		String sql = "INSERT ignore INTO `sponsor` (id, naam, emailadres) VALUES (?,?,?);";
//		try {
//			PreparedStatement stat = DatabaseHelper.getConnection().prepareStatement(sql);
//			stat.setString(1, sponsor.));
//			stat.execute();
//			throw new WebApplicationException(Response.Status.CREATED);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		throw new  WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
//	}
//	
//}
