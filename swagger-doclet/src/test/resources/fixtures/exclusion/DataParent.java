package fixtures.exclusion;

/**
 * The DataParent represents a parent object for testing inherited method exclusion
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class DataParent {

	protected String parentField = "1";

	/**
	 * @hidden
	 */
	protected String parentFieldExclusion = "2";

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

	protected final void setParentField(String parentField) {
		this.parentField = parentField;
	}

	protected final void setParentFieldExclusion(String parentFieldExclusion) {
		this.parentFieldExclusion = parentFieldExclusion;
	}

}
