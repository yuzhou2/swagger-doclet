package fixtures.modelfieldrequired;

/**
 * The Data represents a pojo for testing field required
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class Data {

	private String defaultField;
	/**
	 * @required
	 */
	private String requiredField;
	/**
	 * @optional
	 */
	private String optionalField;

	private String defaultGetterTag;
	private String requiredGetterTag;
	private String optionalGetterTag;

	public String getDefaultGetterTag() {
		return this.defaultGetterTag;
	}

	/**
	 * @required
	 */
	public String getRequiredGetterTag() {
		return this.requiredGetterTag;
	}

	/**
	 * @optional
	 */
	public String getOptionalGetterTag() {
		return this.optionalGetterTag;
	}

	/**
	 * @optional
	 */
	public String getOptionalMethodTag() {
		return null;
	}

	/**
	 * @required
	 */
	public String getRequiredMethodTag() {
		return null;
	}

	public String getDefaultMethodTag() {
		return null;
	}

}
