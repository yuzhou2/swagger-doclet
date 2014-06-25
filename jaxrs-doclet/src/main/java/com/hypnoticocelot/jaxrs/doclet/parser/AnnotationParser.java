package com.hypnoticocelot.jaxrs.doclet.parser;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationValue;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.ProgramElementDoc;

public class AnnotationParser {

	private final AnnotationDesc[] annotations;

	public AnnotationParser(ProgramElementDoc element) {
		this.annotations = element.annotations();
	}

	public AnnotationParser(Parameter parameter) {
		this.annotations = parameter.annotations();
	}

	public String getAnnotationValue(String qualifiedAnnotationType, String key) {
		AnnotationDesc annotation = getAnnotation(qualifiedAnnotationType);
		if (annotation == null) {
			return null;
		}
		for (AnnotationDesc.ElementValuePair evp : annotation.elementValues()) {
			if (evp.element().name().equals(key)) {
				return evp.value().value().toString();
			}
		}
		return null;
	}

	/**
	 * This gets the values of an annotation
	 * @param qualifiedAnnotationType The FQN of the annotation
	 * @param key The field name of the annotation to get
	 * @return The values or null if none were found
	 */
	public String[] getAnnotationValues(String qualifiedAnnotationType, String key) {
		AnnotationDesc annotation = getAnnotation(qualifiedAnnotationType);
		if (annotation == null) {
			return null;
		}
		for (AnnotationDesc.ElementValuePair evp : annotation.elementValues()) {
			if (evp.element().name().equals(key)) {
				Object val = evp.value().value();
				AnnotationValue[] vals = (AnnotationValue[]) val;
				if (vals != null && vals.length > 0) {
					String[] res = new String[vals.length];
					int i = 0;
					for (AnnotationValue annotationVal : vals) {
						res[i] = annotationVal.value().toString();
						i++;
					}
					return res;
				}
			}
		}
		return null;
	}

	public boolean isAnnotatedBy(String qualifiedAnnotationType) {
		return getAnnotation(qualifiedAnnotationType) != null;
	}

	private AnnotationDesc getAnnotation(String qualifiedAnnotationType) {
		AnnotationDesc found = null;
		for (AnnotationDesc annotation : this.annotations) {
			try {
				if (annotation.annotationType().qualifiedTypeName().equals(qualifiedAnnotationType)) {
					found = annotation;
					break;
				}
			} catch (RuntimeException e) {
				System.err.println(annotation + " has invalid javadoc: " + e.getClass() + ": " + e.getMessage());
			}
		}
		return found;
	}

}
