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
import com.carma.swagger.doclet.parser.NamingConvention;
import com.sun.javadoc.RootDoc;

@SuppressWarnings("javadoc")
public class NamingConventionTest {

	private Recorder recorderMock;
	private DocletOptions options;

	@Before
	public void setup() {
		this.recorderMock = mock(Recorder.class);
	}

	@Test
	public void testLowerUnderscore() throws IOException {

		this.options = new DocletOptions().setRecorder(this.recorderMock).setIncludeSwaggerUi(false)
				.setModelFieldsNamingConvention(NamingConvention.LOWER_UNDERSCORE);

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.namingconvention.lowerunderscore");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ApiDeclaration api = loadFixture("/fixtures/namingconvention/lowerunderscore/lowerunderscore.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(api));
	}

	@Test
	public void testLowerUnderscoreUnless() throws IOException {

		this.options = new DocletOptions().setRecorder(this.recorderMock).setIncludeSwaggerUi(false)
				.setModelFieldsNamingConvention(NamingConvention.LOWER_UNDERSCORE_UNLESS_OVERRIDDEN);

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.namingconvention.lowerunderscoreunless");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ApiDeclaration api = loadFixture("/fixtures/namingconvention/lowerunderscoreunless/lowerunderscoreunless.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(api));
	}

	@Test
	public void testLower() throws IOException {

		this.options = new DocletOptions().setRecorder(this.recorderMock).setIncludeSwaggerUi(false).setModelFieldsNamingConvention(NamingConvention.LOWERCASE);

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.namingconvention.lower");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ApiDeclaration api = loadFixture("/fixtures/namingconvention/lower/lower.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(api));
	}

	@Test
	public void testLowerUnless() throws IOException {

		this.options = new DocletOptions().setRecorder(this.recorderMock).setIncludeSwaggerUi(false)
				.setModelFieldsNamingConvention(NamingConvention.LOWERCASE_UNLESS_OVERRIDDEN);

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.namingconvention.lowerunless");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ApiDeclaration api = loadFixture("/fixtures/namingconvention/lowerunless/lowerunless.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(api));
	}

	@Test
	public void testUpper() throws IOException {

		this.options = new DocletOptions().setRecorder(this.recorderMock).setIncludeSwaggerUi(false).setModelFieldsNamingConvention(NamingConvention.UPPERCASE);

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.namingconvention.upper");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ApiDeclaration api = loadFixture("/fixtures/namingconvention/upper/upper.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(api));
	}

	@Test
	public void testUpperUnless() throws IOException {

		this.options = new DocletOptions().setRecorder(this.recorderMock).setIncludeSwaggerUi(false)
				.setModelFieldsNamingConvention(NamingConvention.UPPERCASE_UNLESS_OVERRIDDEN);

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.namingconvention.upperunless");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ApiDeclaration api = loadFixture("/fixtures/namingconvention/upperunless/upperunless.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(api));
	}

}
