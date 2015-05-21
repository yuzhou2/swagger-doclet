package com.carma.swagger.doclet.parser;

import junit.framework.TestCase;

/**
 * The PathSanitizingTest represents a test case of api and resource path sanitizing
 * @version $Id$
 * @author conor.roche
 */
public class PathSanitizingTest extends TestCase {

	/**
	 * This tests the api path sanitization
	 */
	public void testApiPath() {

		assertEquals("/api", ParserHelper.sanitizeApiPath("/api"));
		assertEquals("/api/test", ParserHelper.sanitizeApiPath("/api/test"));
		assertEquals("/api/test{id}", ParserHelper.sanitizeApiPath("/api/test{id}"));
		assertEquals("/api/test/{id}", ParserHelper.sanitizeApiPath("/api/test/{id}"));
		assertEquals("/api/test/{id}", ParserHelper.sanitizeApiPath("/api/test/{id }"));
		assertEquals("/api/test/{id}", ParserHelper.sanitizeApiPath("/api/test/{id:}"));
		assertEquals("/api/test/{id}", ParserHelper.sanitizeApiPath("/api/test/{id:[0-9]+}"));
		assertEquals("/api/test/{id}", ParserHelper.sanitizeApiPath("/api/test/{id: [0-9]+}"));
	}

	/**
	 * This tests the resource path sanitization
	 */
	public void testResourcePath() {
		assertEquals("/api", ParserHelper.sanitizeResourcePath("/api"));
		assertEquals("/api_test", ParserHelper.sanitizeResourcePath("/api/test"));
		assertEquals("/api_test", ParserHelper.sanitizeResourcePath("/api/test/"));
		assertEquals("/api_test", ParserHelper.sanitizeResourcePath("/api/test{id}"));
		assertEquals("/api_test", ParserHelper.sanitizeResourcePath("/api/test/{id}"));
		assertEquals("/api_test", ParserHelper.sanitizeResourcePath("/api/test/{id }"));
		assertEquals("/api_test", ParserHelper.sanitizeResourcePath("/api/test/{id:}"));
		assertEquals("/api_test", ParserHelper.sanitizeResourcePath("/api/test/{id:[0-9]+}"));
		assertEquals("/api_test", ParserHelper.sanitizeResourcePath("/api/test/{id: [0-9]+}"));
	}

}
