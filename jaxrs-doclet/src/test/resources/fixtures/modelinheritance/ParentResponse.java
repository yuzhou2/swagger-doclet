package fixtures.modelinheritance;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The ParentResponse represents a 2nd level response ancestor
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class ParentResponse extends GrandParentResponse {

	private String parentField1;
	private String parentField2;
	private String parentField3;

	public String getParentField1() {
		return this.parentField1;
	}

	public void setParentField1(String parentField1) {
		this.parentField1 = parentField1;
	}

	@XmlElement(name = "parentField2b")
	public String getParentField2() {
		return this.parentField2;
	}

	public void setParentField2(String parentField2) {
		this.parentField2 = parentField2;
	}

	// note that the json property should take precedence
	// here over the xml one
	@XmlElement(name = "parentField3c")
	@JsonProperty("parentField3b")
	public String getParentField3() {
		return this.parentField3;
	}

	public void setParentField3(String parentField3) {
		this.parentField3 = parentField3;
	}

	public String getParentMethod1() {
		return "";
	}

	@XmlElement(name = "parentMethod2b")
	public String getParentMethod2() {
		return "";
	}

	// note that the json property should take precedence
	// here over the xml one
	@XmlElement(name = "parentMethod3c")
	@JsonProperty("parentMethod3b")
	public String getParentMethod3() {
		return "";
	}
}
