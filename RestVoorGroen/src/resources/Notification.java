package resources;

import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/receiver")
public class Notification{

	@Context ServletContext context;
	
	@POST
	public Response getThis() {
		context.getAttribute("model");
		throw new WebApplicationException(Response.Status.NO_CONTENT);
	}
}
