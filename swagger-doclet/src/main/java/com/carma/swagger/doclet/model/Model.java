package com.carma.swagger.doclet.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Model represents a description of an input or output entity or sub entity.
 * @version $Id$
 */
public class Model {

	private String id;
	private Map<String, Property> properties;
	private List<String> requiredFields;

	/**
	 * This creates a Model
	 */
	public Model() {
		super();
	}

	/**
	 * This creates a Model
	 * @param id
	 * @param properties
	 * @param requiredFields
	 */
	public Model(String id, Map<String, Property> properties, List<String> requiredFields) {
		super();
		this.id = id;
		this.properties = properties;
		this.requiredFields = requiredFields;
	}

	/**
	 * This gets the id
	 * @return the id
	 */
	public String getId() {
		return this.id;
	}

	/**
	 * This sets the id
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * This gets the properties
	 * @return the properties
	 */
	public Map<String, Property> getProperties() {
		return this.properties;
	}

	/**
	 * This sets the properties
	 * @param properties the properties to set
	 */
	public void setProperties(Map<String, Property> properties) {
		this.properties = properties;
	}

	/**
	 * This gets the requiredFields
	 * @return the requiredFields
	 */
	@JsonProperty("required")
	public List<String> getRequiredFields() {
		return this.requiredFields;
	}

	/**
	 * This sets the requiredFields
	 * @param requiredFields the requiredFields to set
	 */
	public void setRequiredFields(List<String> requiredFields) {
		this.requiredFields = requiredFields;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
		result = prime * result + ((this.properties == null) ? 0 : this.properties.hashCode());
		result = prime * result + ((this.requiredFields == null) ? 0 : this.requiredFields.hashCode());
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
		Model other = (Model) obj;
		if (this.id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!this.id.equals(other.id)) {
			return false;
		}
		if (this.properties == null) {
			if (other.properties != null) {
				return false;
			}
		} else if (!this.properties.equals(other.properties)) {
			return false;
		}
		if (this.requiredFields == null) {
			if (other.requiredFields != null) {
				return false;
			}
		} else if (!this.requiredFields.equals(other.requiredFields)) {
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
		return "Model [id=" + this.id + ", properties=" + this.properties + ", requiredFields=" + this.requiredFields + "]";
	}

}
