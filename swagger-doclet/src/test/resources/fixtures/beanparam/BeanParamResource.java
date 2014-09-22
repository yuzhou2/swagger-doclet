package fixtures.beanparam;

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
	public String getMultipleQueryParams(@BeanParam MultipleQueryParams bean) {
		String p1 = bean == null ? null : bean.getParam1();
		String p2 = bean == null ? null : "" + bean.getParam2();
		String p3 = bean == null ? null : bean.getParam3();
		return "get method, bean param fields are p1: " + p1 + ", p2: " + p2 + ", p3: " + p3;
	}

	@SuppressWarnings("javadoc")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/2")
	public String getMixedParams(@BeanParam MixedParams bean) {
		String p1 = bean == null ? null : bean.getParam1();
		String p2 = bean == null ? null : "" + bean.getParam2();
		String p3 = bean == null ? null : bean.getParam3();
		return "get method, bean param fields are p1: " + p1 + ", p2: " + p2 + ", p3: " + p3;
	}

}
