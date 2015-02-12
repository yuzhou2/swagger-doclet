package com.carma.swagger.doclet.model;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.MethodDoc;

public enum HttpMethod {

	GET("javax.ws.rs.GET"),
	PUT("javax.ws.rs.PUT"),
	POST("javax.ws.rs.POST"),
	DELETE("javax.ws.rs.DELETE"),
	HEAD("javax.ws.rs.HEAD"),
	OPTIONS("javax.ws.rs.OPTIONS"),
	PATCH(".PATCH", true);

	// NOTE Patch is not part of JAXRS 1 or 2 as it stands (people can add it but it will have an arbitrary package)
	// so we will look for any annotation ending in .PATCH

	private final String className;
	private final boolean useContains;

	private HttpMethod(String className) {
		this.className = className;
		this.useContains = false;
	}

	private HttpMethod(String className, boolean useContains) {
		this.className = className;
		this.useContains = useContains;
	}

	/**
	 * This finds a HTTP method for the given method
	 * @param method The java method to check
	 * @return The HTTP method or null if there is not HTTP method annotation on the java method
	 */
	public static HttpMethod fromMethod(MethodDoc method) {
		for (AnnotationDesc annotation : method.annotations()) {
			String qName = annotation.annotationType().qualifiedTypeName();
			for (HttpMethod value : HttpMethod.values()) {
				if (value.useContains && qName.contains(value.className)) {
					return value;
				} else if (!value.useContains && qName.equals(value.className)) {
					return value;
				}
			}
		}
		return null;
	}
}
