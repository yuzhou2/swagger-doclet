package com.hypnoticocelot.jaxrs.doclet.model;

import static com.google.common.base.Strings.emptyToNull;

import java.util.List;

import com.hypnoticocelot.jaxrs.doclet.parser.AnnotationHelper;

public class Operation {

	private HttpMethod httpMethod;
	private String nickname;
	private String type; // void, primitive, complex or a container
	private List<ApiParameter> parameters;
	private String summary; // cap at 60 characters for readability in the UI
	private String notes;

	private List<ApiResponseMessage> responseMessages;

	private List<String> consumes;
	private List<String> produces;

	private OperationAuthorizations authorizations;

	@SuppressWarnings("unused")
	private Operation() {
	}

	public Operation(Method method) {
		this.httpMethod = method.getMethod();
		this.nickname = emptyToNull(method.getMethodName());
		this.type = emptyToNull(AnnotationHelper.typeOf(method.getReturnType())[0]);
		this.parameters = method.getParameters().isEmpty() ? null : method.getParameters();
		this.responseMessages = method.getResponseMessages().isEmpty() ? null : method.getResponseMessages();
		this.summary = emptyToNull(method.getSummary());
		this.notes = emptyToNull(method.getNotes());
		this.consumes = method.getConsumes() == null || method.getConsumes().isEmpty() ? null : method.getConsumes();
		this.produces = method.getProduces() == null || method.getProduces().isEmpty() ? null : method.getProduces();
		this.authorizations = method.getAuthorizations();
	}

	public HttpMethod getHttpMethod() {
		return this.httpMethod;
	}

	public String getNickname() {
		return this.nickname;
	}

	public String getType() {
		return this.type;
	}

	public List<ApiParameter> getParameters() {
		return this.parameters;
	}

	public List<ApiResponseMessage> getResponseMessages() {
		return this.responseMessages;
	}

	public String getSummary() {
		return this.summary;
	}

	public String getNotes() {
		return this.notes;
	}

	/**
	 * This gets the consumes
	 * @return the consumes
	 */
	public List<String> getConsumes() {
		return this.consumes;
	}

	/**
	 * This gets the produces
	 * @return the produces
	 */
	public List<String> getProduces() {
		return this.produces;
	}

	/**
	 * This gets the authorizations
	 * @return the authorizations
	 */
	public OperationAuthorizations getAuthorizations() {
		return this.authorizations;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.authorizations == null) ? 0 : this.authorizations.hashCode());
		result = prime * result + ((this.consumes == null) ? 0 : this.consumes.hashCode());
		result = prime * result + ((this.httpMethod == null) ? 0 : this.httpMethod.hashCode());
		result = prime * result + ((this.nickname == null) ? 0 : this.nickname.hashCode());
		result = prime * result + ((this.notes == null) ? 0 : this.notes.hashCode());
		result = prime * result + ((this.parameters == null) ? 0 : this.parameters.hashCode());
		result = prime * result + ((this.produces == null) ? 0 : this.produces.hashCode());
		result = prime * result + ((this.responseMessages == null) ? 0 : this.responseMessages.hashCode());
		result = prime * result + ((this.summary == null) ? 0 : this.summary.hashCode());
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
		Operation other = (Operation) obj;
		if (this.authorizations == null) {
			if (other.authorizations != null) {
				return false;
			}
		} else if (!this.authorizations.equals(other.authorizations)) {
			return false;
		}
		if (this.consumes == null) {
			if (other.consumes != null) {
				return false;
			}
		} else if (!this.consumes.equals(other.consumes)) {
			return false;
		}
		if (this.httpMethod != other.httpMethod) {
			return false;
		}
		if (this.nickname == null) {
			if (other.nickname != null) {
				return false;
			}
		} else if (!this.nickname.equals(other.nickname)) {
			return false;
		}
		if (this.notes == null) {
			if (other.notes != null) {
				return false;
			}
		} else if (!this.notes.equals(other.notes)) {
			return false;
		}
		if (this.parameters == null) {
			if (other.parameters != null) {
				return false;
			}
		} else if (!this.parameters.equals(other.parameters)) {
			return false;
		}
		if (this.produces == null) {
			if (other.produces != null) {
				return false;
			}
		} else if (!this.produces.equals(other.produces)) {
			return false;
		}
		if (this.responseMessages == null) {
			if (other.responseMessages != null) {
				return false;
			}
		} else if (!this.responseMessages.equals(other.responseMessages)) {
			return false;
		}
		if (this.summary == null) {
			if (other.summary != null) {
				return false;
			}
		} else if (!this.summary.equals(other.summary)) {
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
		return "Operation [httpMethod=" + this.httpMethod + ", nickname=" + this.nickname + ", type=" + this.type + ", parameters=" + this.parameters
				+ ", summary=" + this.summary + ", notes=" + this.notes + ", responseMessages=" + this.responseMessages + ", consumes=" + this.consumes
				+ ", produces=" + this.produces + ", authorizations=" + this.authorizations + "]";
	}

}
