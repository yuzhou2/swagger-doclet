package fixtures.xmlaccesstype;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlTransient;

@SuppressWarnings("javadoc")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class PropertyAccessPojo {

	// 1-7 should NOT be included
	@SuppressWarnings("unused")
	private String value1;
	String value2;
	protected String value3;
	public String value4;
	transient String value5;
	@XmlTransient
	String value6;
	static String value7;

	static String getValue7() {
		return value7;
	}

	// 8-9 should be included
	@XmlAttribute
	private String value8;

	public String getValue9() {
		return null;
	}

}
