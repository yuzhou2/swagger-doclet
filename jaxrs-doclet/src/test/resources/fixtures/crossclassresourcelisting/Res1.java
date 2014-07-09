package fixtures.crossclassresourcelisting;

import javax.ws.rs.GET;

@SuppressWarnings("javadoc")
public class Res1 {

	/**
	 * @resourcePath a
	 * @priority 2
	 * @resourceDescription A
	 */
	@GET
	public void getX() {
		// noop
	}

	/**
	 * @resource b
	 * @resourcePriority 1
	 */
	@GET
	public void getY() {
		// noop
	}

}
