package com.carma.swagger.doclet.translator;

import static com.carma.swagger.doclet.translator.Translator.OptionalName.presentOrMissing;

import com.carma.swagger.doclet.parser.ParserHelper;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Type;

public class NameBasedTranslator implements Translator {

	/**
	 * {@inheritDoc}
	 * @see com.carma.swagger.doclet.translator.Translator#typeName(com.sun.javadoc.Type, com.sun.javadoc.ClassDoc[])
	 */
	public OptionalName typeName(Type type, ClassDoc[] views) {
		String[] typeFormat = ParserHelper.typeOf(type.qualifiedTypeName());

		if (views != null && views.length > 0) {
			StringBuilder nameWithView = new StringBuilder(typeFormat[0]).append("-");
			for (ClassDoc view : views) {
				nameWithView.append(view.name());
			}
			return presentOrMissing(nameWithView.toString(), typeFormat[1]);
		}

		return presentOrMissing(typeFormat[0], typeFormat[1]);
	}

	public OptionalName typeName(Type type) {
		String[] typeFormat = ParserHelper.typeOf(type.qualifiedTypeName());
		return presentOrMissing(typeFormat[0], typeFormat[1]);
	}

	public OptionalName fieldName(FieldDoc field) {
		return presentOrMissing(field.name());
	}

	public OptionalName methodName(MethodDoc method) {
		String name = null;
		if (method.name().startsWith("get") && method.name().length() > 3) {
			name = method.name().substring(3);
			name = name.substring(0, 1).toLowerCase() + (name.length() > 1 ? name.substring(1) : "");
		}
		return presentOrMissing(name);
	}

}
