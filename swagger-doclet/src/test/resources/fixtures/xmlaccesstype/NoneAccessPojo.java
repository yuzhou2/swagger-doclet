package fixtures.xmlaccesstype;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlList;

@SuppressWarnings("javadoc")
@XmlAccessorType(XmlAccessType.NONE)
public class NoneAccessPojo {

	// 1-5 should NOT be included
	@SuppressWarnings("unused")
	private String value1;
	String value2;
	protected String value3;
	public String value4;

	public String getValue5() {
		return null;
	}

	// values 6-11 should be included
	@XmlAttribute
	private String value6;
	@XmlAttribute(name = "value7a")
	private String value7;
	@XmlElement
	private String value8;
	@XmlElement(name = "value9a")
	private String value9;
	@XmlList
	private List<String> value10;

	@XmlElement
	public String getValue11() {
		return null;
	}

}
