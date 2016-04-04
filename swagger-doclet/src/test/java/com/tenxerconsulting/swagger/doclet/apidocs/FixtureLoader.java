package com.tenxerconsulting.swagger.doclet.apidocs;

import java.io.IOException;
import java.io.InputStream;

import com.fasterxml.jackson.databind.ObjectMapper;

@SuppressWarnings("javadoc")
public class FixtureLoader {

	private FixtureLoader() {
	}

	public static <T> T loadFixture(String path, Class<T> resourceClass) throws IOException {
		InputStream is = null;
		try {
			is = FixtureLoader.class.getResourceAsStream(path);
			return new ObjectMapper().readValue(is, resourceClass);
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

}
