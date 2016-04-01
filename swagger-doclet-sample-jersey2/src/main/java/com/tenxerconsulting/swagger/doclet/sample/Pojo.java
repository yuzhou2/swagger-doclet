package com.tenxerconsulting.swagger.doclet.sample;

/**
 * The Pojo represents
 * @version $Id$
 * @author conor.roche
 */
public class Pojo {

	/**
	 * This creates a Pojo
	 */
	public Pojo() {
	}

	/**
	 * This creates a Pojo
	 * @param name
	 */
	public Pojo(String name) {
		super();
		this.name = name;
	}

	private String name;

	/**
	 * This gets the name
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * This sets the name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
