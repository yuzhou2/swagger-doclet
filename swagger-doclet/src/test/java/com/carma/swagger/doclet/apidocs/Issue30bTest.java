package com.carma.swagger.doclet.apidocs;

import static com.carma.swagger.doclet.apidocs.FixtureLoader.loadFixture;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.carma.swagger.doclet.DocletOptions;
import com.carma.swagger.doclet.Recorder;
import com.carma.swagger.doclet.model.ApiDeclaration;
import com.carma.swagger.doclet.parser.JaxRsAnnotationParser;
import com.carma.swagger.doclet.parser.ParserHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;

import fixtures.issue30b.Comment;
import fixtures.issue30b.CommentThread;
import fixtures.issue30b.User;

@SuppressWarnings("javadoc")
public class Issue30bTest {

	private Recorder recorderMock;
	private DocletOptions options;

	@Before
	public void setup() {
		this.recorderMock = mock(Recorder.class);
		this.options = new DocletOptions().setRecorder(this.recorderMock).setIncludeSwaggerUi(false);
	}

	// @Test
	public void testStart() throws IOException {

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.issue30b");
		new JaxRsAnnotationParser(this.options, rootDoc).run();

		final ApiDeclaration api = loadFixture("/fixtures/issue30b/issue30b.json", ApiDeclaration.class);
		verify(this.recorderMock).record(any(File.class), eq(api));

	}

	@Test
	public void testJackson() throws Exception {
		User u = new User();
		u.setName("UName");
		u.setComments(new ArrayList<Comment>(1));

		CommentThread t = new CommentThread();
		t.setName("TName");
		t.setComments(new ArrayList<Comment>(1));

		Comment c = new Comment();
		c.setText("Text");
		c.setUser(u);
		c.setCommentThread(t);

		u.getComments().add(c);
		t.getComments().add(c);

		ObjectMapper mapper = new ObjectMapper();
		String result = mapper.writerWithView(User.UserView.class).writeValueAsString(u);
		Assert.assertTrue(result.contains("name"));
		Assert.assertTrue(result.contains("comments"));
	}

	@Test
	public void testInheritance() throws IOException {

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.issue30b");
		ClassDoc fieldView = rootDoc.classNamed(Comment.UserView.class.getName());
		ClassDoc methodView = rootDoc.classNamed(User.UserView.class.getName());

		// field view should implement view class of the method view
		Assert.assertTrue(ParserHelper.isItemPartOfView(new ClassDoc[] { methodView }, new ClassDoc[] { fieldView }));
		// but not vice versa
		Assert.assertFalse(ParserHelper.isItemPartOfView(new ClassDoc[] { fieldView }, new ClassDoc[] { methodView }));

		// comment text does not implement the user view
		fieldView = rootDoc.classNamed(Comment.CommentView.class.getName());
		Assert.assertFalse(ParserHelper.isItemPartOfView(new ClassDoc[] { methodView }, new ClassDoc[] { fieldView }));

		fieldView = rootDoc.classNamed(User.CommentView.class.getName());
		Assert.assertFalse(ParserHelper.isItemPartOfView(new ClassDoc[] { methodView }, new ClassDoc[] { fieldView }));
		Assert.assertTrue(ParserHelper.isItemPartOfView(new ClassDoc[] { fieldView }, new ClassDoc[] { methodView }));

	}
}
