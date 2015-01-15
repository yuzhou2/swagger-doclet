package com.carma.swagger.doclet.sample;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * The SimpleResource represents
 * @version $Id$
 * @author conor.roche
 */
@Path("simple")
public class SimpleResource {

	/**
	 * This gets a pojo
	 * @return
	 */
	@Path("/1")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Pojo getPojo() {
		return new Pojo("test");
	}

}
