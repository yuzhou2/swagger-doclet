package fixtures.modelinheritance;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ChildPayload represents the bottom level payload
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class ChildPayload extends ParentPayload {

	private String field1;
	private String field2;
	private String field3;

	public String getField1() {
		return this.field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	@XmlElement(name = "field2b")
	public String getField2() {
		return this.field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	// note that the json property should take precedence
	// here over the xml one
	@XmlElement(name = "field3c")
	@JsonProperty("field3b")
	public String getField3() {
		return this.field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getMethod1() {
		return "";
	}

	@XmlElement(name = "method2b")
	public String getMethod2() {
		return "";
	}

	// note that the json property should take precedence
	// here over the xml one
	@XmlElement(name = "method3c")
	@JsonProperty("method3b")
	public String getMethod3() {
		return "";
	}
}
