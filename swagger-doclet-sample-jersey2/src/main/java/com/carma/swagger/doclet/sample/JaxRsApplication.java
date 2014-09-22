package com.carma.swagger.doclet.sample;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

/**
 * The JaxRsApplication represents
 * @version $Id$
 * @author conor.roche
 */
public class JaxRsApplication extends Application {

	private final Set<Class<?>> classes;

	/**
	 * This creates a JaxRsApplication
	 */
	public JaxRsApplication() {
		HashSet<Class<?>> c = new HashSet<Class<?>>();
		c.add(BeanParamResource.class);
		this.classes = Collections.unmodifiableSet(c);
	}

	@Override
	public Set<Class<?>> getClasses() {
		return this.classes;
	}
}
