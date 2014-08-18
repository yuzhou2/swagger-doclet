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
import com.sun.javadoc.RootDoc;

/**
 * The ModelFieldRequiredTest represents a test for required vs optional model fields
 * @version $Id$
 * @author conor.roche
 */
public class ModelFieldRequiredTest {

	private Recorder recorderMock;
	private DocletOptions options;

	@Before
	public void setup() {
		this.recorderMock = mock(Recorder.class);
		this.options = new DocletOptions().setRecorder(this.recorderMock).setIncludeSwaggerUi(false);
	}

	@Test
	public void testModelFieldNotRequiredByDefault() throws IOException {
		this.options.setModelFieldsRequiredByDefault(false);

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.modelfieldrequired");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ApiDeclaration api = loadFixture("/fixtures/modelfieldrequired/modelfieldrequired.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(api));
	}

	@Test
	public void testModelFieldRequiredByDefault() throws IOException {
		this.options.setModelFieldsRequiredByDefault(true);

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.modelfieldrequired");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ApiDeclaration api = loadFixture("/fixtures/modelfieldrequired/modelfieldrequired2.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(api));
	}

}
