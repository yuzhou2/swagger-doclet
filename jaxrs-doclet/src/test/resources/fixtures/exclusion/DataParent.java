package fixtures.exclusion;

/**
 * The DataParent represents a parent object for testing inherited method exclusion
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class DataParent {

	protected String parentField;

	/**
	 * @hidden
	 */
	protected String parentFieldExclusion;

	private String parentMethodExclusion;

	/**
	 * @hidden
	 */
	public String getParentMethodExclusion() {
		return this.parentMethodExclusion;
	}

	public void setParentMethodExclusion(String parentMethodExclusion) {
		this.parentMethodExclusion = parentMethodExclusion;
	}

	public String getParentMethod() {
		return null;
	}

}
