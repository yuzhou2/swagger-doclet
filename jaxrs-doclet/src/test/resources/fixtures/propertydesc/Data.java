package fixtures.propertydesc;

/**
 * The Data represents an object with different field types to test documentation
 * of their description in the model
 * @version $Id$
 * @author conor.roche
 */
public class Data {

	/**
	 * desc_fieldComment
	 */
	private String fieldComment;

	private String getterComment;
	private String getterReturn;

	private String fieldTag;
	private String getterTag;

	/**
	 * This creates a Data
	 */
	public Data() {
	}

	/**
	 * This creates a Data
	 * @param fieldComment
	 * @param getterComment
	 * @param getterReturn
	 * @param fieldTag
	 * @param getterTag
	 */
	public Data(String fieldComment, String getterComment, String getterReturn, String fieldTag, String getterTag) {
		super();
		this.fieldComment = fieldComment;
		this.getterComment = getterComment;
		this.getterReturn = getterReturn;
		this.fieldTag = fieldTag;
		this.getterTag = getterTag;
	}

	public String getFieldComment() {
		return this.fieldComment;
	}

	/**
	 * desc_getterComment
	 */
	public String getGetterComment() {
		return this.getterComment;
	}

	/**
	 * @return desc_getterReturn
	 */
	public String getGetterReturn() {
		return this.getterReturn;
	}

	/**
	 * <should not be used>
	 * @description desc_fieldTag
	 * @return <should not be used>
	 */
	public String getFieldTag() {
		return this.fieldTag;
	}

	/**
	 * <should not be used>
	 * @description desc_getterTag
	 * @return <should not be used>
	 */
	public String getGetterTag() {
		return this.getterTag;
	}

}
