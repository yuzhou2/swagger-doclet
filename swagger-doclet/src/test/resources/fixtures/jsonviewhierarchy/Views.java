package fixtures.jsonviewhierarchy;

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
	public static class PublicView {
		// noop
	}

	public static class InternalView extends PublicView {
		// noop
	}

	public static class PrivateView extends InternalView {
		// noop
	}

}
