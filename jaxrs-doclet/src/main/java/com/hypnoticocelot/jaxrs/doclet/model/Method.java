package com.hypnoticocelot.jaxrs.doclet.model;

import java.util.List;

public class Method {

	private HttpMethod method;
	private String methodName;
	private List<ApiParameter> apiParameters;
	private List<ApiResponseMessage> responseMessages;
	private String firstSentence;
	private String comment;
	private String returnType;
	private String path;

	private List<String> consumes;
	private List<String> produces;

	private OperationAuthorizations authorizations;

	@SuppressWarnings("unused")
	private Method() {
	}

	public Method(HttpMethod method, String methodName, String path, List<ApiParameter> apiParameters, List<ApiResponseMessage> responseMessages,
			String firstSentence, String comment, String returnType, List<String> consumes, List<String> produces, OperationAuthorizations authorizations) {
		this.method = method;
		this.methodName = methodName;
		this.path = path;
		this.apiParameters = apiParameters;
		this.responseMessages = responseMessages;
		this.firstSentence = firstSentence;
		this.comment = comment;
		this.returnType = returnType;
		this.consumes = consumes;
		this.produces = produces;
		this.authorizations = authorizations;
	}

	public HttpMethod getMethod() {
		return this.method;
	}

	public String getMethodName() {
		return this.methodName;
	}

	public String getPath() {
		return this.path;
	}

	public List<ApiParameter> getParameters() {
		return this.apiParameters;
	}

	public List<ApiResponseMessage> getResponseMessages() {
		return this.responseMessages;
	}

	public String getFirstSentence() {
		return this.firstSentence;
	}

	public String getComment() {
		return this.comment;
	}

	public String getReturnType() {
		return this.returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public boolean isSubResource() {
		return this.method == null;
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
		result = prime * result + ((this.apiParameters == null) ? 0 : this.apiParameters.hashCode());
		result = prime * result + ((this.authorizations == null) ? 0 : this.authorizations.hashCode());
		result = prime * result + ((this.comment == null) ? 0 : this.comment.hashCode());
		result = prime * result + ((this.consumes == null) ? 0 : this.consumes.hashCode());
		result = prime * result + ((this.firstSentence == null) ? 0 : this.firstSentence.hashCode());
		result = prime * result + ((this.method == null) ? 0 : this.method.hashCode());
		result = prime * result + ((this.methodName == null) ? 0 : this.methodName.hashCode());
		result = prime * result + ((this.path == null) ? 0 : this.path.hashCode());
		result = prime * result + ((this.produces == null) ? 0 : this.produces.hashCode());
		result = prime * result + ((this.responseMessages == null) ? 0 : this.responseMessages.hashCode());
		result = prime * result + ((this.returnType == null) ? 0 : this.returnType.hashCode());
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
		Method other = (Method) obj;
		if (this.apiParameters == null) {
			if (other.apiParameters != null) {
				return false;
			}
		} else if (!this.apiParameters.equals(other.apiParameters)) {
			return false;
		}
		if (this.authorizations == null) {
			if (other.authorizations != null) {
				return false;
			}
		} else if (!this.authorizations.equals(other.authorizations)) {
			return false;
		}
		if (this.comment == null) {
			if (other.comment != null) {
				return false;
			}
		} else if (!this.comment.equals(other.comment)) {
			return false;
		}
		if (this.consumes == null) {
			if (other.consumes != null) {
				return false;
			}
		} else if (!this.consumes.equals(other.consumes)) {
			return false;
		}
		if (this.firstSentence == null) {
			if (other.firstSentence != null) {
				return false;
			}
		} else if (!this.firstSentence.equals(other.firstSentence)) {
			return false;
		}
		if (this.method != other.method) {
			return false;
		}
		if (this.methodName == null) {
			if (other.methodName != null) {
				return false;
			}
		} else if (!this.methodName.equals(other.methodName)) {
			return false;
		}
		if (this.path == null) {
			if (other.path != null) {
				return false;
			}
		} else if (!this.path.equals(other.path)) {
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
		if (this.returnType == null) {
			if (other.returnType != null) {
				return false;
			}
		} else if (!this.returnType.equals(other.returnType)) {
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
		return "Method [method=" + this.method + ", methodName=" + this.methodName + ", apiParameters=" + this.apiParameters + ", responseMessages="
				+ this.responseMessages + ", firstSentence=" + this.firstSentence + ", comment=" + this.comment + ", returnType=" + this.returnType + ", path="
				+ this.path + ", consumes=" + this.consumes + ", produces=" + this.produces + ", authorizations=" + this.authorizations + "]";
	}

}
