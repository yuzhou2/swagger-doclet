package com.tenxerconsulting.swagger.doclet.sample.api;

import java.io.Serializable;

/**
 * The Translation represents a pojo for testing a file upload scenario
 * @version $Id$
 * @author conor.roche
 */
public class Translation implements Serializable {

	private static final long serialVersionUID = 1L;

	private String languageCode;
	private String text;

	/**
	 * This gets the languageCode
	 * @return the languageCode
	 */
	public String getLanguageCode() {
		return this.languageCode;
	}

	/**
	 * This sets the languageCode
	 * @param languageCode the languageCode to set
	 */
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}

	/**
	 * This gets the text
	 * @return the text
	 */
	public String getText() {
		return this.text;
	}

	/**
	 * This sets the text
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * {@inheritDoc}
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Translation [languageCode=" + this.languageCode + ", text=" + this.text + "]";
	}

}
