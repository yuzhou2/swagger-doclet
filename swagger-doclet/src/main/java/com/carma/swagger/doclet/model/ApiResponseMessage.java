package com.carma.swagger.doclet.model;

import com.google.common.base.Objects;

public class ApiResponseMessage {

	private int code;

	private String message;

	private String responseModel;

	@SuppressWarnings("unused")
	private ApiResponseMessage() {
	}

	/**
	 * This creates a ApiResponseMessage with no specific response model for this error code
	 * @param code
	 * @param message
	 */
	public ApiResponseMessage(int code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * This creates a ApiResponseMessage with support for a custom model for this response code
	 * @param code
	 * @param message
	 * @param responseModel
	 */
	public ApiResponseMessage(int code, String message, String responseModel) {
		super();
		this.code = code;
		this.message = message;
		this.responseModel = responseModel;
	}

	/**
	 * This gets the code
	 * @return the code
	 */
	public int getCode() {
		return this.code;
	}

	/**
	 * This gets the message
	 * @return the message
	 */
	public String getMessage() {
		return this.message;
	}

	/**
	 * This gets the responseModel
	 * @return the responseModel
	 */
	public String getResponseModel() {
		return this.responseModel;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		ApiResponseMessage that = (ApiResponseMessage) o;
		return Objects.equal(this.code, that.code) && Objects.equal(this.message, that.message) && Objects.equal(this.responseModel, that.responseModel);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(this.code, this.message, this.responseModel);
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this).add("code", this.code).add("message", this.message).add("responseModel", this.responseModel).toString();
	}
}
