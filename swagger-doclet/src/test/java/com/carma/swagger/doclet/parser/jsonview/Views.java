package com.carma.swagger.doclet.parser.jsonview;

/**
 * The Views represents a placeholder for json views
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class Views {

	// classes
	public static class PublicClass {
		// noop
	}

	public static class InternalClass extends PublicClass {
		// noop
	}

	public static class PrivateClass extends InternalClass {
		// noop
	}

	// interfaces
	public static interface PublicInterface {
		// noop
	}

	public static interface InternalInterface extends PublicInterface {
		// noop
	}

	public static interface PrivateInterface extends InternalInterface {
		// noop
	}

}
