package fixtures.valueconstraints;

/**
 * The Data represents a simple pojo for testing produces/consumes
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class Data {

	private EnumValue value;

	/**
	 * @min 1
	 */
	private int minFieldTag;
	/**
	 * @max 10
	 */
	private int maxFieldTag;
	/**
	 * @min 2
	 * @max 8
	 */
	private int minMaxFieldTag;

	private int minGetterTag;
	private int maxGetterTag;
	private int minMaxGetterTag;

	/**
	 * This creates a Input
	 */
	public Data() {
	}

	/**
	 * This creates a Data
	 * @param value
	 * @param minFieldTag
	 * @param maxFieldTag
	 * @param minMaxFieldTag
	 * @param minGetterTag
	 * @param maxGetterTag
	 * @param minMaxGetterTag
	 */
	public Data(EnumValue value, int minFieldTag, int maxFieldTag, int minMaxFieldTag, int minGetterTag, int maxGetterTag, int minMaxGetterTag) {
		super();
		this.value = value;
		this.minFieldTag = minFieldTag;
		this.maxFieldTag = maxFieldTag;
		this.minMaxFieldTag = minMaxFieldTag;
		this.minGetterTag = minGetterTag;
		this.maxGetterTag = maxGetterTag;
		this.minMaxGetterTag = minMaxGetterTag;
	}

	@SuppressWarnings("javadoc")
	public EnumValue getValue() {
		return this.value;
	}

	@SuppressWarnings("javadoc")
	public int getMinFieldTag() {
		return this.minFieldTag;
	}

	@SuppressWarnings("javadoc")
	public int getMaxFieldTag() {
		return this.maxFieldTag;
	}

	@SuppressWarnings("javadoc")
	public int getMinMaxFieldTag() {
		return this.minMaxFieldTag;
	}

	/**
	 * @min 1
	 */
	public int getMinGetterTag() {
		return this.minGetterTag;
	}

	/**
	 * @max 10
	 */
	public int getMaxGetterTag() {
		return this.maxGetterTag;
	}

	/**
	 * @min 2
	 * @max 8
	 */
	public int getMinMaxGetterTag() {
		return this.minMaxGetterTag;
	}

}
