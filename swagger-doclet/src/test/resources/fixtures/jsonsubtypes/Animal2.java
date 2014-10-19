package fixtures.jsonsubtypes;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@SuppressWarnings("javadoc")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Cat2.class, name = "cat2"), @Type(value = Dog2.class, name = "dog2") })
public abstract class Animal2 {

	public String name;
	public String type;

}
