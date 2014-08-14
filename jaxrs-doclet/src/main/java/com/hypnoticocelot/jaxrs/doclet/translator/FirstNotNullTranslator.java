package com.hypnoticocelot.jaxrs.doclet.translator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.google.common.base.Function;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Type;

public class FirstNotNullTranslator implements Translator {

	private final List<Translator> chain;

	public FirstNotNullTranslator() {
		this.chain = new ArrayList<Translator>();
	}

	public FirstNotNullTranslator addNext(Translator link) {
		this.chain.add(link);
		return this;
	}

	/**
	 * {@inheritDoc}
	 * @see com.hypnoticocelot.jaxrs.doclet.translator.Translator#typeName(com.sun.javadoc.Type, com.sun.javadoc.ClassDoc[])
	 */
	public OptionalName typeName(final Type type, final ClassDoc[] views) {
		return firstNotNullOf(new Function<Translator, OptionalName>() {

			public OptionalName apply(Translator translator) {
				return translator.typeName(type, views);
			}
		});
	}

	public OptionalName typeName(final Type type) {
		return firstNotNullOf(new Function<Translator, OptionalName>() {

			public OptionalName apply(Translator translator) {
				return translator.typeName(type);
			}
		});
	}

	public OptionalName fieldName(final FieldDoc field) {
		return firstNotNullOf(new Function<Translator, OptionalName>() {

			public OptionalName apply(Translator translator) {
				return translator.fieldName(field);
			}
		});
	}

	public OptionalName methodName(final MethodDoc method) {
		return firstNotNullOf(new Function<Translator, OptionalName>() {

			public OptionalName apply(Translator translator) {
				return translator.methodName(method);
			}
		});
	}

	private OptionalName firstNotNullOf(Function<Translator, OptionalName> function) {
		OptionalName name = null;
		Iterator<Translator> iterator = this.chain.iterator();
		while ((name == null || name.isMissing()) && iterator.hasNext()) {
			name = function.apply(iterator.next());
		}
		return name;
	}

}
