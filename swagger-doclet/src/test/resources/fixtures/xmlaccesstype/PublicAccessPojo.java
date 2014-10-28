package fixtures.xmlaccesstype;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@SuppressWarnings("javadoc")
@XmlAccessorType(XmlAccessType.PUBLIC_MEMBER)
public class PublicAccessPojo {

	// 1 - 6 should NOT be included

	@SuppressWarnings("unused")
	private String value1;
	protected String value2;
	String value3;

	@SuppressWarnings("unused")
	private String getValue4() {
		return null;
	}

	protected String getValue5() {
		return null;
	}

	String getValue6() {
		return null;
	}

	// 7 - 11 should be included

	private String value7;

	public String getValue7() {
		return this.value7;
	}

	public String value8;

	public String getValue9() {
		return null;
	}

	public String value10;

	public String getValue10() {
		return this.value10;
	}

	@XmlAttribute
	private String value11;

}
