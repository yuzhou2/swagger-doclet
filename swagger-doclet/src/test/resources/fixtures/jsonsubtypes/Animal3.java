package fixtures.jsonsubtypes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@SuppressWarnings("javadoc")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Cat3.class, name = "cat3"), @Type(value = Dog3.class, name = "dog3") })
public abstract class Animal3 {

	public String name;
	public String type;

}
