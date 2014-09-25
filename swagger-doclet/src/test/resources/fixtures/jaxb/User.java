package fixtures.jaxb;

import javax.xml.bind.annotation.XmlAttribute;
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
	private String middleName;
	private String title;

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

	@XmlAttribute(name = "middle_name")
	public String getMiddleName() {
		return this.middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getTitle() {
		return this.title;
	}

	@XmlElement(name = "ti")
	public void setTitle(String title) {
		this.title = title;
	}

}
