package com.carma.swagger.doclet.parser;

import com.carma.swagger.doclet.DocletOptions;
import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.AnnotationValue;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.ProgramElementDoc;

/**
 * The AnnotationParser represents a utility class for reading values from annotations
 * @version $Id$
 */
public class AnnotationParser {

	private final AnnotationDesc[] annotations;
	private final DocletOptions options;

	/**
	 * This creates an AnnotationParser for a method/field
	 * @param element The method/field javadoc item
	 * @param options The doclet options
	 */
	public AnnotationParser(ProgramElementDoc element, DocletOptions options) {
		this.annotations = element.annotations();
		this.options = options;
	}

	/**
	 * This creates an AnnotationParser for a parameter
	 * @param parameter The parameter javadoc item
	 * @param options The doclet options
	 */
	public AnnotationParser(Parameter parameter, DocletOptions options) {
		this.annotations = parameter.annotations();
		this.options = options;
	}

	/**
	 * This gets the value of the annotation with the given FQN and attribute named key
	 * @param qualifiedAnnotationType The FQN of the annotation to get the value of
	 * @param key The name of the attribute of the annotation to get the value of
	 * @return The value of the given named attribute of the given annotation
	 */
	public String getAnnotationValue(String qualifiedAnnotationType, String key) {
		AnnotationDesc annotation = getAnnotation(qualifiedAnnotationType);
		if (annotation == null) {
			return null;
		}
		for (AnnotationDesc.ElementValuePair evp : annotation.elementValues()) {
			if (evp.element().name().equals(key)) {
				String val = evp.value().value().toString();
				val = val.trim();
				return this.options.replaceVars(val);
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
						String str = annotationVal.value().toString().trim();
						str = this.options.replaceVars(str);
						res[i] = str;
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
