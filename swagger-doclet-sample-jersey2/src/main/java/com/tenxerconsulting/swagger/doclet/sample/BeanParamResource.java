package com.tenxerconsulting.swagger.doclet.sample;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * The BeanParamResource represents a resource that uses a beanparam aggregate
 * @version $Id$
 * @author conor.roche
 */
@Path("beanparam")
public class BeanParamResource {

	@SuppressWarnings("javadoc")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/1")
	public String get1(@BeanParam MultipleQueryParams bean) {
		if (bean == null) {
			return "no bean was passed in";
		} else {
			return "get method, bean param fields are p1: " + bean.getParam1() + ", p2: " + bean.getParam2() + ", p3: " + bean.getParam3();
		}
	}

}
