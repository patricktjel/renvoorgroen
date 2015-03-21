package resources;

import java.sql.SQLException;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import database.DatabaseHelper;

@Path("/event")
public class _Event {

	@DELETE
	@Path("/reset")
	public Response reset(@QueryParam("id") int id) {
		try {
			if(id == 1){
				DatabaseHelper.reset();
			} else {
				throw new WebApplicationException(Response.Status.FORBIDDEN);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new WebApplicationException(Response.Status.INTERNAL_SERVER_ERROR);
		}
		throw new WebApplicationException(Response.Status.ACCEPTED);
	}
}
