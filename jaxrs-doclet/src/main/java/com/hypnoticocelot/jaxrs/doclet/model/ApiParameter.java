package com.hypnoticocelot.jaxrs.doclet.model;

import static com.google.common.base.Strings.emptyToNull;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Objects;

public class ApiParameter {

	private String paramType;
	private String name;
	private String description;
	private String type;
	private String format;
	private List<String> allowableValues;
	private Boolean allowMultiple;

	// TODO support min max for non enum/array types

	@SuppressWarnings("unused")
	private ApiParameter() {
	}

	public ApiParameter(String paramType, String name, String description, String type, String format, List<String> allowableValues, Boolean allowMultiple) {
		this.paramType = paramType;
		this.name = name;
		this.description = emptyToNull(description);
		this.type = type;
		this.format = format;
		this.allowableValues = allowableValues;
		this.allowMultiple = allowMultiple;
	}

	public String getParamType() {
		return this.paramType;
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public String getType() {
		return this.type;
	}

	/**
	 * This gets the format
	 * @return the format
	 */
	public String getFormat() {
		return this.format;
	}

	/**
	 * This gets whether the parameter is required
	 * @return Whether the parameter is required
	 *         TODO: may want to be more explicit here
	 */
	public boolean getRequired() {
		return !this.paramType.equals("query");
	}

	/**
	 * This gets the allowableValues
	 * @return the allowableValues
	 */
	@JsonProperty("enum")
	public List<String> getAllowableValues() {
		return this.allowableValues;
	}

	/**
	 * This gets the allowMultiple
	 * @return the allowMultiple
	 */
	public Boolean getAllowMultiple() {
		return this.allowMultiple;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ApiParameter that = (ApiParameter) o;
		return Objects.equal(this.paramType, that.paramType) && Objects.equal(this.name, that.name) && Objects.equal(this.description, that.description)
				&& Objects.equal(this.type, that.type) && Objects.equal(this.format, that.format) && Objects.equal(this.allowableValues, that.allowableValues)
				&& Objects.equal(this.allowMultiple, that.allowMultiple);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.paramType, this.name, this.description, this.type, this.format, this.allowableValues, this.allowMultiple);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("paramType", this.paramType).add("name", this.name).add("description", this.description).add("type", this.type)
				.add("format", this.format).add("enum", this.allowableValues).add("allowMultiple", this.allowMultiple).toString();
	}
}
