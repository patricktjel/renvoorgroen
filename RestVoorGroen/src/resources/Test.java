package resources;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

@Path("/test")
public class Test {

	@Context ServletContext context;
	
	@GET
	@Path("/tests")
	public Response getThis() {
		context.getAttribute("model");
		throw new WebApplicationException(Response.Status.NOT_IMPLEMENTED);
	}
}
