package com.carma.swagger.doclet.parser;

/**
 * The NamingConvention represents naming conventions for how items such as fields should be named
 * @version $Id$
 * @author conor.roche
 */
public enum NamingConvention {

	/**
	 * This means the item should use its default name e.g. if it was a field named firstName then this would be firstName.
	 * This may be overridden by annotations or tags
	 */
	DEFAULT_NAME,
	/**
	 * This means the item should use lowercase unless there is a naming annotation or javadoc tag for it
	 */
	LOWERCASE_UNLESS_OVERRIDDEN,
	/**
	 * This means the item should use uppercase unless there is a naming annotation or javadoc tag for it
	 */
	UPPERCASE_UNLESS_OVERRIDDEN,
	/**
	 * This means the item should use lowercase with underscores separating words, for example firstName would become first_name unless there is a naming
	 * annotation or javadoc tag for it
	 */
	LOWER_UNDERSCORE_UNLESS_OVERRIDDEN,
	/**
	 * This means the item should always use lowercase even if there is a naming annotation or javadoc tag for it
	 */
	LOWERCASE,
	/**
	 * This means the item should always use uppercase even if there is a naming annotation or javadoc tag for it
	 */
	UPPERCASE,
	/**
	 * This means the item should use always lowercase with underscores separating words, for example firstName would become first_name, it will use this even
	 * if there is a naming annotation or javadoc tag for it
	 */
	LOWER_UNDERSCORE;

	/**
	 * This gets a naming convention whose name matches the given string, if none matches it will return the given default value.
	 * @param value The value to match
	 * @param defaultValue The default value if none matches
	 * @return The matching naming convention or the default value if none matched
	 */
	public static final NamingConvention forValue(String value, NamingConvention defaultValue) {
		if (value != null) {
			value = value.trim().toLowerCase();
			for (NamingConvention conv : NamingConvention.values()) {
				if (conv.name().toLowerCase().equals(value)) {
					return conv;
				}
			}
		}
		return defaultValue;
	}

	/**
	 * This converts a camel case type value into lower underscore form
	 * @param value The value to convert
	 * @return The converted value
	 */
	public static final String toLowerUnderscore(String value) {
		if (value != null) {
			value = value.trim();

			StringBuilder res = new StringBuilder();
			char prev = 0;
			for (int i = 0; i < value.length(); i++) {
				char c = value.charAt(i);
				if (i == 0) {
					res.append(Character.toLowerCase(c));
				} else {
					if (Character.isWhitespace(prev)) {
						continue;
					}
					if (Character.isWhitespace(c)) {
						res.append('_');
					} else if (Character.isUpperCase(c) && Character.isLowerCase(prev)) {
						res.append('_').append(Character.toLowerCase(c));
					} else {
						res.append(Character.toLowerCase(c));
					}
				}

				prev = c;
			}
			return res.toString();
		}
		return value;
	}

}
