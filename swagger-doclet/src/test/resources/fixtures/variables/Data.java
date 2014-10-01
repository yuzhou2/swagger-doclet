package fixtures.variables;

/**
 * The Data represents a simple pojo for testing variables
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class Data {

	private String value;

	/**
	 * ${v4}
	 */
	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
