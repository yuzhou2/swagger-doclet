package com.carma.swagger.doclet.apidocs;

import static com.carma.swagger.doclet.apidocs.FixtureLoader.loadFixture;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.carma.swagger.doclet.DocletOptions;
import com.carma.swagger.doclet.Recorder;
import com.carma.swagger.doclet.model.ResourceListing;
import com.carma.swagger.doclet.parser.JaxRsAnnotationParser;
import com.sun.javadoc.RootDoc;

/**
 * The ResourceExclusionTest represents a test for resource class exclusion
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class ResourceExclusionTest {

	private Recorder recorderMock;
	private DocletOptions options;

	@Before
	public void setup() {
		this.recorderMock = mock(Recorder.class);
		this.options = new DocletOptions().setRecorder(this.recorderMock).setIncludeSwaggerUi(false).setSortResourcesByPath(true)
				.setExcludeResourcePrefixes(Arrays.asList(new String[] { "fixtures.resourceexclusion.pkg1", "fixtures.resourceexclusion.pkg2.Res2a" }));
	}

	@Test
	public void testStart() throws IOException {

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.resourceexclusion");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ResourceListing expectedListing = loadFixture("/fixtures/resourceexclusion/resourceexclusion.json", ResourceListing.class);
		verify(this.recorderMock).record(any(File.class), eq(expectedListing));
	}

}
