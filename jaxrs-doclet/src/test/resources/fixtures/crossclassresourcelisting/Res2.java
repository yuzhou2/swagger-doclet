package fixtures.crossclassresourcelisting;

import javax.ws.rs.GET;

@SuppressWarnings("javadoc")
public class Res2 {

	/**
	 * @resourcePath c
	 * @priority 3
	 * @resourceDescription C
	 */
	@GET
	public void getC() {
		// noop
	}

	/**
	 * @parentEndpoint b
	 * @resourceDescription B
	 */
	@GET
	public void getB() {
		// noop
	}

}
