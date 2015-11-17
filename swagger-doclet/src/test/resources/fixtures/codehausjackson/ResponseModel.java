package fixtures.codehausjackson;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonRootName;

@JsonRootName("response")
@SuppressWarnings("javadoc")
public class ResponseModel {

	public String getVisibleField() {
		return "";
	}

	@JsonProperty("odd-name")
	public String oddlyNamedField() {
		return "";
	}

	@JsonIgnore
	public String getInvisibleField() {
		return "";
	}

}
