package fixtures.genericresponse;

/**
 * The Parameterized2 represents another parameterized type for testing
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class Parameterized2<S> {

	private S typed;

	public S getTyped() {
		return this.typed;
	}

	public void setTyped(S typed) {
		this.typed = typed;
	}

}
