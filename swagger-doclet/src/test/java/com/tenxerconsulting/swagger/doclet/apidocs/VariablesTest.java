package com.tenxerconsulting.swagger.doclet.apidocs;

import static com.tenxerconsulting.swagger.doclet.apidocs.FixtureLoader.loadFixture;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.sun.javadoc.RootDoc;
import com.tenxerconsulting.swagger.doclet.DocletOptions;
import com.tenxerconsulting.swagger.doclet.Recorder;
import com.tenxerconsulting.swagger.doclet.model.ApiDeclaration;
import com.tenxerconsulting.swagger.doclet.parser.JaxRsAnnotationParser;

/**
 * The VariablesTest represents a test case for replacing variables in the javadoc with values sourced from an external properties
 * file.
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class VariablesTest {

	private Recorder recorderMock;
	private DocletOptions options;

	@Before
	public void setup() {
		this.recorderMock = mock(Recorder.class);
		Properties variableReplacements = new Properties();
		variableReplacements.setProperty("v1", "v1val");
		variableReplacements.setProperty("v2", "v2val");
		variableReplacements.setProperty("v3", "VALUE1");
		variableReplacements.setProperty("v4", "v4val");
		this.options = new DocletOptions().setRecorder(this.recorderMock).setIncludeSwaggerUi(false).setVariableReplacements(variableReplacements);
	}

	@Test
	public void testStart() throws IOException {
		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.variables");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ApiDeclaration api = loadFixture("/fixtures/variables/variables.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(api));
	}

}
