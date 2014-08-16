package com.carma.swagger.doclet.parser;

import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationValue;
import com.sun.javadoc.ClassDoc;
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
				String val = evp.value().value().toString();
				return val.trim();
			}
		}
		return null;
	}

	/**
	 * This gets the values of an annotation as class docs
	 * @param qualifiedAnnotationType The FQN of the annotation
	 * @param key The field name of the annotation to get
	 * @return The values or null if none were found
	 */
	public ClassDoc[] getAnnotationClassDocValues(String qualifiedAnnotationType, String key) {
		AnnotationDesc annotation = getAnnotation(qualifiedAnnotationType);
		if (annotation == null) {
			return null;
		}
		for (AnnotationDesc.ElementValuePair evp : annotation.elementValues()) {
			if (evp.element().name().equals(key)) {
				Object val = evp.value().value();
				AnnotationValue[] vals = (AnnotationValue[]) val;
				if (vals != null && vals.length > 0) {
					ClassDoc[] res = new ClassDoc[vals.length];
					int i = 0;
					for (AnnotationValue annotationVal : vals) {
						ClassDoc classDoc = (ClassDoc) annotationVal.value();
						res[i++] = classDoc;
					}
					return res;
				}
			}
		}
		return null;
	}

	/**
	 * This gets the values of an annotation as strings
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
						res[i] = annotationVal.value().toString().trim();
						i++;
					}
					return res;
				}
			}
		}
		return null;
	}

	/**
	 * This gets whether this is annotated by the given annotation
	 * @param qualifiedAnnotationType The annotation type to check for
	 * @return True if this is annotated by the given annotation
	 */
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
