package com.tenxerconsulting.swagger.doclet.apidocs;

import static com.tenxerconsulting.swagger.doclet.apidocs.FixtureLoader.loadFixture;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.sun.javadoc.RootDoc;
import com.tenxerconsulting.swagger.doclet.DocletOptions;
import com.tenxerconsulting.swagger.doclet.Recorder;
import com.tenxerconsulting.swagger.doclet.model.ApiDeclaration;
import com.tenxerconsulting.swagger.doclet.model.ResourceListing;
import com.tenxerconsulting.swagger.doclet.parser.JaxRsAnnotationParser;

@SuppressWarnings("javadoc")
public class ServiceDocletTest {

	private Recorder recorderMock;
	private DocletOptions options;

	@Before
	public void setup() {
		this.recorderMock = mock(Recorder.class);
		this.options = new DocletOptions().setRecorder(this.recorderMock).setIncludeSwaggerUi(false).setExcludeDeprecatedParams(false);
	}

	@Test
	public void testStart() throws IOException {
		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.sample");

		boolean parsingResult = new JaxRsAnnotationParser(this.options, rootDoc).run();
		assertThat("JavaDoc generation failed", parsingResult, equalTo(true));

		final ResourceListing expectedListing = loadFixture("/fixtures/sample/service.json", ResourceListing.class);
		verify(this.recorderMock).record(any(File.class), eq(expectedListing));

		final ApiDeclaration expectedDeclaration = loadFixture("/fixtures/sample/foo.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(expectedDeclaration));
	}

}
