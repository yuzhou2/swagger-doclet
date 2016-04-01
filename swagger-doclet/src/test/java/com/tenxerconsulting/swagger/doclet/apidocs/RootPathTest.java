package com.tenxerconsulting.swagger.doclet.apidocs;

import static com.tenxerconsulting.swagger.doclet.apidocs.FixtureLoader.loadFixture;
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
public class RootPathTest {

	private Recorder recorderMock;
	private DocletOptions options;

	@Before
	public void setup() {
		this.recorderMock = mock(Recorder.class);
		this.options = new DocletOptions().setRecorder(this.recorderMock).setIncludeSwaggerUi(false).setExcludeDeprecatedParams(false);
	}

	// @Test
	public void testDefaultRoot() throws IOException {
		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.rootpath");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ResourceListing expectedListing = loadFixture("/fixtures/rootpath/service.json", ResourceListing.class);
		verify(this.recorderMock).record(any(File.class), eq(expectedListing));

		final ApiDeclaration expectedDeclaration = loadFixture("/fixtures/rootpath/root.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(expectedDeclaration));
	}

	// @Test
	public void testCustomRootViaDocletParam() throws IOException {
		this.options.setResourceRootPath("/customroot");
		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.rootpath");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ResourceListing expectedListing = loadFixture("/fixtures/rootpath/service2.json", ResourceListing.class);
		verify(this.recorderMock).record(any(File.class), eq(expectedListing));

		final ApiDeclaration expectedDeclaration = loadFixture("/fixtures/rootpath/customroot.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(expectedDeclaration));
	}

	@Test
	public void testCustomRootViaTag() throws IOException {
		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.rootpathtag");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ResourceListing expectedListing = loadFixture("/fixtures/rootpathtag/service.json", ResourceListing.class);
		verify(this.recorderMock).record(any(File.class), eq(expectedListing));

		final ApiDeclaration expectedDeclaration = loadFixture("/fixtures/rootpathtag/customroot.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(expectedDeclaration));
	}

}
