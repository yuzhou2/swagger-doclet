package fixtures.issue17;

import javax.xml.bind.annotation.XmlElement;

/**
 * The User represents an example that uses xml element and attributes to customize field name
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class User {

	private String username;
	private String firstName;

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@XmlElement(name = "first_name")
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

}
