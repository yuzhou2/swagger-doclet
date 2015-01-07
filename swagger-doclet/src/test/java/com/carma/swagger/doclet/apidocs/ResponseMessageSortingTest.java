package com.carma.swagger.doclet.apidocs;

import static com.carma.swagger.doclet.apidocs.FixtureLoader.loadFixture;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.carma.swagger.doclet.DocletOptions;
import com.carma.swagger.doclet.Recorder;
import com.carma.swagger.doclet.model.ApiDeclaration;
import com.carma.swagger.doclet.parser.JaxRsAnnotationParser;
import com.carma.swagger.doclet.parser.ResponseMessageSortMode;
import com.sun.javadoc.RootDoc;

@SuppressWarnings("javadoc")
public class ResponseMessageSortingTest {

	private Recorder recorderMock;
	private DocletOptions options;

	@Before
	public void setup() {
		this.recorderMock = mock(Recorder.class);
		this.options = new DocletOptions().setRecorder(this.recorderMock).setIncludeSwaggerUi(false);
	}

	@Test
	public void testDefaultSorting() throws IOException {
		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.responsemessagesorting");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ApiDeclaration api = loadFixture("/fixtures/responsemessagesorting/responsemessagesortingasc.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(api));
	}

	@Test
	public void testAscSortingViaOption() throws IOException {

		this.options.setResponseMessageSortMode(ResponseMessageSortMode.CODE_ASC);

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.responsemessagesorting");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ApiDeclaration api = loadFixture("/fixtures/responsemessagesorting/responsemessagesortingasc.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(api));
	}

	@Test
	public void testAsAppearsSorting() throws IOException {

		this.options.setResponseMessageSortMode(ResponseMessageSortMode.AS_APPEARS);

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.responsemessagesorting");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ApiDeclaration api = loadFixture("/fixtures/responsemessagesorting/responsemessagesortingasappears.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(api));
	}

	@Test
	public void testDescSorting() throws IOException {

		this.options.setResponseMessageSortMode(ResponseMessageSortMode.CODE_DESC);

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.responsemessagesorting");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ApiDeclaration api = loadFixture("/fixtures/responsemessagesorting/responsemessagesortingdesc.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(api));
	}

}
