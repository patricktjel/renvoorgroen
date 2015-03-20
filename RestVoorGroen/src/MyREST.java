

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("api")
public class MyREST extends ResourceConfig {
	public MyREST() {
		packages("resources");
		for(Class c:this.getClasses()){
			System.out.println(c.getName());
		}
	}
}
