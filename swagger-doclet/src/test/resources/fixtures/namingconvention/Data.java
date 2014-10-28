package fixtures.namingconvention;

import javax.xml.bind.annotation.XmlElement;

/**
 * The Data class represents a test object for naming conventions
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class Data {

	private String firstField;
	@XmlElement(name = "2ndfield")
	private String secondField;

	public String getFirstField() {
		return this.firstField;
	}

	public void setFirstField(String firstField) {
		this.firstField = firstField;
	}

	public String getSecondField() {
		return this.secondField;
	}

	public void setSecondField(String secondField) {
		this.secondField = secondField;
	}

}
