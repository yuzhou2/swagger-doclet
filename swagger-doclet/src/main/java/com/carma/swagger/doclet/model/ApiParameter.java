package com.carma.swagger.doclet.model;

import static com.google.common.base.Strings.emptyToNull;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiParameter {

	private String paramType;
	private String name;
	private String description;
	private String type;
	private String format;
	private Boolean required;
	private List<String> allowableValues;
	private Boolean allowMultiple;

	// TODO support min max for non enum/array types

	/**
	 * This creates a ApiParameter
	 */
	public ApiParameter() {
		super();
	}

	/**
	 * This creates a ApiParameter
	 * @param paramType
	 * @param name
	 * @param description
	 * @param type
	 * @param format
	 * @param required
	 * @param allowableValues
	 * @param allowMultiple
	 */
	public ApiParameter(String paramType, String name, String description, String type, String format, Boolean required, List<String> allowableValues,
			Boolean allowMultiple) {
		this.paramType = paramType;
		this.name = name;
		this.description = emptyToNull(description);
		this.type = type;
		this.format = format;
		this.required = required;
		this.allowableValues = allowableValues;
		this.allowMultiple = allowMultiple;
	}

	/**
	 * This gets the paramType
	 * @return the paramType
	 */
	public String getParamType() {
		return this.paramType;
	}

	/**
	 * This gets the name
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This gets the description
	 * @return the description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * This gets the type
	 * @return the type
	 */
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
	 * This gets the required
	 * @return the required
	 */
	public Boolean getRequired() {
		return this.required;
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

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.allowMultiple == null) ? 0 : this.allowMultiple.hashCode());
		result = prime * result + ((this.allowableValues == null) ? 0 : this.allowableValues.hashCode());
		result = prime * result + ((this.description == null) ? 0 : this.description.hashCode());
		result = prime * result + ((this.format == null) ? 0 : this.format.hashCode());
		result = prime * result + ((this.name == null) ? 0 : this.name.hashCode());
		result = prime * result + ((this.paramType == null) ? 0 : this.paramType.hashCode());
		result = prime * result + ((this.required == null) ? 0 : this.required.hashCode());
		result = prime * result + ((this.type == null) ? 0 : this.type.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		ApiParameter other = (ApiParameter) obj;
		if (this.allowMultiple == null) {
			if (other.allowMultiple != null) {
				return false;
			}
		} else if (!this.allowMultiple.equals(other.allowMultiple)) {
			return false;
		}
		if (this.allowableValues == null) {
			if (other.allowableValues != null) {
				return false;
			}
		} else if (!this.allowableValues.equals(other.allowableValues)) {
			return false;
		}
		if (this.description == null) {
			if (other.description != null) {
				return false;
			}
		} else if (!this.description.equals(other.description)) {
			return false;
		}
		if (this.format == null) {
			if (other.format != null) {
				return false;
			}
		} else if (!this.format.equals(other.format)) {
			return false;
		}
		if (this.name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!this.name.equals(other.name)) {
			return false;
		}
		if (this.paramType == null) {
			if (other.paramType != null) {
				return false;
			}
		} else if (!this.paramType.equals(other.paramType)) {
			return false;
		}
		if (this.required == null) {
			if (other.required != null) {
				return false;
			}
		} else if (!this.required.equals(other.required)) {
			return false;
		}
		if (this.type == null) {
			if (other.type != null) {
				return false;
			}
		} else if (!this.type.equals(other.type)) {
			return false;
		}
		return true;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ApiParameter [paramType=" + this.paramType + ", name=" + this.name + ", description=" + this.description + ", type=" + this.type + ", format="
				+ this.format + ", required=" + this.required + ", allowableValues=" + this.allowableValues + ", allowMultiple=" + this.allowMultiple + "]";
	}

}
