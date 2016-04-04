package com.tenxerconsulting.swagger.doclet.parser;

/**
 * The ResponseMessageSortMode represents the sort mode for response messages
 * @version $Id$
 * @author conor.roche
 */
public enum ResponseMessageSortMode {

	/**
	 * This is the default which means in the order they appear in the javadoc
	 */
	AS_APPEARS,
	/**
	 * This means in ascending order of the HTTP status code so success codes would come before error codes, this is the default
	 */
	CODE_ASC,
	/**
	 * This means in descending order of the HTTP status code so error codes would come before success codes
	 */
	CODE_DESC

}
