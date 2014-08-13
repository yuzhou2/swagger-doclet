package fixtures.deprecation;

/**
 * The Data represents test model for this fixture
 * @version $Id$
 * @author conor.roche
 */

public class Data {

	/**
	 * This creates a Data
	 */
	public Data() {
	}

	private String value; // should be present in the resulting model

	// these 4 fields should NOT be present in the resulting model
	/**
	 * @deprecated
	 */
	@SuppressWarnings("dep-ann")
	private String fieldTag;

	private String getterTag;

	@Deprecated
	private String fieldAnnotation;

	private String getterAnnotation;

	@SuppressWarnings("javadoc")
	public String getValue() {
		return this.value;
	}

	@SuppressWarnings("javadoc")
	public void setValue(String value) {
		this.value = value;
	}

	@SuppressWarnings("javadoc")
	public String getFieldTag() {
		return this.fieldTag;
	}

	@SuppressWarnings("javadoc")
	public void setFieldTag(String fieldTag) {
		this.fieldTag = fieldTag;
	}

	/**
	 * @deprecated
	 */
	@SuppressWarnings({ "javadoc", "dep-ann" })
	public String getGetterTag() {
		return this.getterTag;
	}

	@SuppressWarnings("javadoc")
	public void setGetterTag(String getterTag) {
		this.getterTag = getterTag;
	}

	@SuppressWarnings("javadoc")
	public String getFieldAnnotation() {
		return this.fieldAnnotation;
	}

	@SuppressWarnings("javadoc")
	public void setFieldAnnotation(String fieldAnnotation) {
		this.fieldAnnotation = fieldAnnotation;
	}

	@SuppressWarnings("javadoc")
	@Deprecated
	public String getGetterAnnotation() {
		return this.getterAnnotation;
	}

	@SuppressWarnings("javadoc")
	public void setGetterAnnotation(String getterAnnotation) {
		this.getterAnnotation = getterAnnotation;
	}

}
