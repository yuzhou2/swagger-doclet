package com.hypnoticocelot.jaxrs.doclet.translator;

import static com.hypnoticocelot.jaxrs.doclet.translator.Translator.OptionalName.ignored;
import static com.hypnoticocelot.jaxrs.doclet.translator.Translator.OptionalName.presentOrMissing;

import java.util.HashMap;
import java.util.Map;

import com.hypnoticocelot.jaxrs.doclet.parser.AnnotationHelper;
import com.hypnoticocelot.jaxrs.doclet.parser.AnnotationParser;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.ProgramElementDoc;
import com.sun.javadoc.Type;

public class AnnotationAwareTranslator implements Translator {

	private final Map<OptionalName, Type> reverseIndex;
	private final Map<Type, OptionalName> namedTypes;

	private String ignore;
	private String element;
	private String elementProperty;
	private String rootElement;
	private String rootElementProperty;

	public AnnotationAwareTranslator() {
		this.reverseIndex = new HashMap<OptionalName, Type>();
		this.namedTypes = new HashMap<Type, OptionalName>();
	}

	public AnnotationAwareTranslator ignore(String qualifiedAnnotationType) {
		this.ignore = qualifiedAnnotationType;
		return this;
	}

	public AnnotationAwareTranslator element(String qualifiedAnnotationType, String property) {
		this.element = qualifiedAnnotationType;
		this.elementProperty = property;
		return this;
	}

	public AnnotationAwareTranslator rootElement(String qualifiedAnnotationType, String property) {
		this.rootElement = qualifiedAnnotationType;
		this.rootElementProperty = property;
		return this;
	}

	public OptionalName typeName(Type type) {
		if (this.namedTypes.containsKey(type)) {
			return this.namedTypes.get(type);
		}
		if (AnnotationHelper.isPrimitive(type) || type.asClassDoc() == null) {
			return null;
		}

		OptionalName name = nameFor(this.rootElement, this.rootElementProperty, type.asClassDoc());
		if (name.isPresent()) {
			StringBuilder nameBuilder = new StringBuilder(name.value());
			while (this.reverseIndex.containsKey(name)) {
				nameBuilder.append('_');
				name = presentOrMissing(nameBuilder.toString());
			}
			this.namedTypes.put(type, name);
			this.reverseIndex.put(name, type);
		}
		return name;
	}

	public OptionalName fieldName(FieldDoc field) {
		return nameFor(this.element, this.elementProperty, field);
	}

	public OptionalName methodName(MethodDoc method) {
		return nameFor(this.element, this.elementProperty, method);
	}

	private OptionalName nameFor(String annotation, String property, ProgramElementDoc doc) {
		AnnotationParser element = new AnnotationParser(doc);
		if (element.isAnnotatedBy(this.ignore)) {
			return ignored();
		}
		return presentOrMissing(element.getAnnotationValue(annotation, property));
	}

}
