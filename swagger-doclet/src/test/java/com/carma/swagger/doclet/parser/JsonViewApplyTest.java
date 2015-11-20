package com.carma.swagger.doclet.parser;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.carma.swagger.doclet.apidocs.RootDocLoader;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.RootDoc;

import fixtures.jsonviewhierarchy.Views.InternalClass;
import fixtures.jsonviewhierarchy.Views.InternalView;
import fixtures.jsonviewhierarchy.Views.PrivateClass;
import fixtures.jsonviewhierarchy.Views.PrivateView;
import fixtures.jsonviewhierarchy.Views.PublicClass;
import fixtures.jsonviewhierarchy.Views.PublicView;

/**
 * The JsonViewApplyTest tests whether fields/methods should be part of a view
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class JsonViewApplyTest {

	@Test
	public void testIsItemPartOfView() throws IOException {

		final RootDoc rootDoc = RootDocLoader.fromPath("src/test/resources", "fixtures.jsonviewhierarchy");

		// if public we should only see the public field
		Assert.assertTrue(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(PublicClass.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(PublicClass.class.getName()) }));
		Assert.assertFalse(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(PublicClass.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(PublicView.class.getName()) }));
		Assert.assertFalse(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(PublicClass.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(InternalClass.class.getName()) }));
		Assert.assertFalse(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(PublicClass.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(InternalView.class.getName()) }));
		Assert.assertFalse(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(PublicClass.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(PrivateClass.class.getName()) }));
		Assert.assertFalse(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(PublicClass.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(PrivateView.class.getName()) }));

		// if internal we should see the internal and public field
		Assert.assertTrue(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(InternalClass.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(PublicClass.class.getName()) }));
		Assert.assertTrue(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(InternalClass.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(InternalClass.class.getName()) }));
		Assert.assertFalse(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(InternalClass.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(PrivateClass.class.getName()) }));
		Assert.assertTrue(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(InternalView.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(PublicView.class.getName()) }));
		Assert.assertTrue(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(InternalView.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(InternalView.class.getName()) }));
		Assert.assertFalse(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(InternalView.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(PrivateView.class.getName()) }));

		// if private we should see all
		Assert.assertTrue(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(PrivateClass.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(PublicClass.class.getName()) }));
		Assert.assertTrue(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(PrivateClass.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(InternalClass.class.getName()) }));
		Assert.assertTrue(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(PrivateClass.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(PrivateClass.class.getName()) }));
		Assert.assertTrue(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(PrivateView.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(PublicView.class.getName()) }));
		Assert.assertTrue(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(PrivateView.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(InternalView.class.getName()) }));
		Assert.assertTrue(ParserHelper.isItemPartOfView(new ClassDoc[] { rootDoc.classNamed(PrivateView.class.getName()) },
				new ClassDoc[] { rootDoc.classNamed(PrivateView.class.getName()) }));

	}

}
