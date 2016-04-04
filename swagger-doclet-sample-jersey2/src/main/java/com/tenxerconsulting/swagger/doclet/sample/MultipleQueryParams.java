package com.tenxerconsulting.swagger.doclet.sample;

import javax.ws.rs.QueryParam;

/**
 * The MultipleQueryParams represents a beanparam class with some query params on fields and some on getters
 * @version $Id$
 * @author conor.roche
 */
public class MultipleQueryParams {

	@QueryParam("p1")
	private String param1;

	private int param2;
	private String param3;

	/**
	 * This gets the param1
	 * @return the param1
	 */
	public String getParam1() {
		return this.param1;
	}

	/**
	 * This sets the param1
	 * @param param1 the param1 to set
	 */
	public void setParam1(String param1) {
		this.param1 = param1;
	}

	/**
	 * This gets the param2
	 * @return the param2
	 */
	// TODO jersey seems to ignore this, the jaxrs2.0 spec to me isn't clear on whether this is to be allowed
	// I will assume some impl may support this.
	@QueryParam("p2")
	public int getParam2() {
		return this.param2;
	}

	/**
	 * This sets the param2
	 * @param param2 the param2 to set
	 */
	public void setParam2(int param2) {
		this.param2 = param2;
	}

	/**
	 * This gets the param3
	 * @return the param3
	 */
	public String getParam3() {
		return this.param3;
	}

	/**
	 * This sets the param3
	 * @param param3 the param3 to set
	 */
	@QueryParam("p3")
	public void setParam3(String param3) {
		this.param3 = param3;
	}

}
