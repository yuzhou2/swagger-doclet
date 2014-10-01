package com.carma.swagger.doclet.parser;

import java.util.Properties;

import junit.framework.TestCase;

/**
 * The VariableReplacerTest represents
 * @version $Id$
 * @author conor.roche
 */
public class VariableReplacerTest extends TestCase {

	/**
	 * This tests simple e.g. single level replacement
	 */
	public void testSimpleReplacement() {

		Properties props = new Properties();
		props.setProperty("a", "aval");
		props.setProperty("b", "bval");

		String val = "a ${a}";
		super.assertEquals("a aval", VariableReplacer.replaceVariables(props, val));
		val = "${a}";
		super.assertEquals("aval", VariableReplacer.replaceVariables(props, val));
		val = "$a";
		super.assertEquals("$a", VariableReplacer.replaceVariables(props, val));
		val = "${a}${b}";
		super.assertEquals("avalbval", VariableReplacer.replaceVariables(props, val));
		val = "${a} $${b}";
		super.assertEquals("aval $bval", VariableReplacer.replaceVariables(props, val));

	}

	/**
	 * This tests multi level replacement where one value refers to other variables
	 */
	public void testMultiLevelReplacement() {

		Properties props = new Properties();
		props.setProperty("a", "aval");
		props.setProperty("b", "${a}");
		props.setProperty("c", "c${b}");
		props.setProperty("d", "a:${a} b:${b} c:${c} e:${e}");

		String val = "res${d}";
		super.assertEquals("resa:aval b:aval c:caval e:${e}", VariableReplacer.replaceVariables(props, val));
	}

}
