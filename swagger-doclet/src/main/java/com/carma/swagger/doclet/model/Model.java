package com.carma.swagger.doclet.model;

import java.util.Map;

import com.google.common.base.Objects;

public class Model {

	private String id;
	private Map<String, Property> properties;

	// FIXME support required fields..

	public Model() {
	}

	public Model(String id, Map<String, Property> properties) {
		this.id = id;
		this.properties = properties;
	}

	public String getId() {
		return this.id;
	}

	public Map<String, Property> getProperties() {
		return this.properties;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Model other = (Model) o;
		return Objects.equal(this.id, other.id) && Objects.equal(this.properties, other.properties);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.id, this.properties);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("id", this.id).add("properties", this.properties).toString();
	}
}
