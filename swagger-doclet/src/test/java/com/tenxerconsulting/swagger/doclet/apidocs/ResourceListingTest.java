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
import com.tenxerconsulting.swagger.doclet.model.ResourceListing;
import com.tenxerconsulting.swagger.doclet.parser.JaxRsAnnotationParser;

/**
 * The ResourceListingTest represents
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class ResourceListingTest {

	private Recorder recorderMock;
	private DocletOptions options;

	@Before
	public void setup() {
		this.recorderMock = mock(Recorder.class);
		this.options = new DocletOptions().setRecorder(this.recorderMock).setIncludeSwaggerUi(false);

	}

	@Test
	public void testPriorityOrder() throws IOException {

		this.options.getResourceDescriptionTags().add("resourceDescription");
		this.options.getResourcePriorityTags().add("resourcePriority");
		this.options.setSortResourcesByPath(false);
		this.options.setSortResourcesByPriority(true);

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.resourcelisting");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ResourceListing expectedListing = loadFixture("/fixtures/resourcelisting/service2.json", ResourceListing.class);
		verify(this.recorderMock).record(any(File.class), eq(expectedListing));

	}

	@Test
	public void testPathOrder() throws IOException {

		this.options.getResourceDescriptionTags().add("resourceDescription");
		this.options.getResourcePriorityTags().add("resourcePriority");
		this.options.setSortResourcesByPath(true);
		this.options.setSortResourcesByPriority(false);

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.resourcelisting");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ResourceListing expectedListing = loadFixture("/fixtures/resourcelisting/service3.json", ResourceListing.class);
		verify(this.recorderMock).record(any(File.class), eq(expectedListing));

	}

}
