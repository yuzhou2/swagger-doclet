package fixtures.exclusion;

/**
 * The Data represents a test object for model field and method exclusion
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class Data extends DataParent {

	private String field;
	/**
	 * @hidden
	 */
	private String fieldExclusion;
	private String getterExclusion;

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getFieldExclusion() {
		return this.fieldExclusion;
	}

	public void setFieldExclusion(String fieldExclusion) {
		this.fieldExclusion = fieldExclusion;
	}

	/**
	 * @hidden
	 */
	public String getGetterExclusion() {
		return this.getterExclusion;
	}

	public void setGetterExclusion(String getterExclusion) {
		this.getterExclusion = getterExclusion;
	}

	public String getMethod() {
		return null;
	}

	/**
	 * @hidden
	 */
	public String methodExclusion() {
		return null;
	}

}
