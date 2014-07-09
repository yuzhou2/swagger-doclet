package com.hypnoticocelot.jaxrs.doclet.apidocs;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.hypnoticocelot.jaxrs.doclet.DocletOptions;
import com.hypnoticocelot.jaxrs.doclet.Recorder;
import com.hypnoticocelot.jaxrs.doclet.parser.JaxRsAnnotationParser;
import com.sun.javadoc.RootDoc;

/**
 * The CrossClassTest represents a test of the cross class test which uses the v2 cross class supporting parser.
 * @version $Id$
 * @author conor.roche
 */
public class CrossClassTest {

	private Recorder recorderMock;
	private DocletOptions options;

	@Before
	public void setup() {
		this.recorderMock = mock(Recorder.class);
		this.options = new DocletOptions().setRecorder(this.recorderMock).setIncludeSwaggerUi(false).setCrossClassResources(true);
	}

	@Test
	public void testStart() throws IOException {
		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.crossclass");

		boolean parsingResult = new JaxRsAnnotationParser(this.options, rootDoc).run();
		assertThat("JavaDoc generation failed", parsingResult, equalTo(true));

	}

}
