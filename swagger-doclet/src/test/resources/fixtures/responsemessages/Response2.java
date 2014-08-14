package fixtures.responsemessages;

/**
 * The Response2 represents a custom response object which may be used for either all responses
 * of a method e.g. to allow jaxrs Response return object in the java interface but document that a different
 * entity is returned.
 * @version $Id$
 * @author conor.roche
 */
public class Response2 {

	private String value;

	/**
	 * This creates a Response1
	 */
	public Response2() {
		super();
	}

	/**
	 * This creates a Response1
	 * @param value
	 */
	public Response2(String value) {
		super();
		this.value = value;
	}

	@SuppressWarnings("javadoc")
	public String getValue() {
		return this.value;
	}

	@SuppressWarnings("javadoc")
	public void setValue(String value) {
		this.value = value;
	}

}
