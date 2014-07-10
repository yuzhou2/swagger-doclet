package fixtures.modelinheritance;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The GrandParentResponse represents a top level response ancestor
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class GrandParentResponse {

	private String grandParentField1;
	private String grandParentField2;
	private String grandParentField3;

	public String getGrandParentField1() {
		return this.grandParentField1;
	}

	public void setGrandParentField1(String grandParentField1) {
		this.grandParentField1 = grandParentField1;
	}

	@XmlElement(name = "grandParentField2b")
	public String getGrandParentField2() {
		return this.grandParentField2;
	}

	public void setGrandParentField2(String grandParentField2) {
		this.grandParentField2 = grandParentField2;
	}

	// note that the json property should take precedence
	// here over the xml one
	@XmlElement(name = "grandParentField3c")
	@JsonProperty("grandParentField3b")
	public String getGrandParentField3() {
		return this.grandParentField3;
	}

	public void setGrandParentField3(String grandParentField3) {
		this.grandParentField3 = grandParentField3;
	}

	public String getGrandParentMethod1() {
		return "";
	}

	@XmlElement(name = "grandParentMethod2b")
	public String getGrandParentMethod2() {
		return "";
	}

	// note that the json property should take precedence
	// here over the xml one
	@XmlElement(name = "grandParentMethod3c")
	@JsonProperty("grandParentMethod3b")
	public String getGrandParentMethod3() {
		return "";
	}

}
