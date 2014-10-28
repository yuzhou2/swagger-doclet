package fixtures.xmlaccesstype;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

@SuppressWarnings("javadoc")
@XmlAccessorType(XmlAccessType.FIELD)
public class FieldAccessPojo {

	// 1-4 should be included
	@SuppressWarnings("unused")
	private String value1;
	String value2;
	protected String value3;
	public String value4;

	// 5-8 should not be included
	transient String value5;
	@XmlTransient
	String value6;
	static String value7;

	public String getValue8() {
		return null;
	}

	// 9 should be included
	@XmlElement
	public String getValue9() {
		return null;
	}
}
