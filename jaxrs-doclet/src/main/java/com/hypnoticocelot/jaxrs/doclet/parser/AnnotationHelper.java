package com.hypnoticocelot.jaxrs.doclet.parser;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;
import com.hypnoticocelot.jaxrs.doclet.DocletOptions;
import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.Type;

public class AnnotationHelper {

	private static final String JAX_RS_ANNOTATION_PACKAGE = "javax.ws.rs";
	private static final String JAX_RS_PATH = "javax.ws.rs.Path";
	private static final String JAX_RS_PATH_PARAM = "javax.ws.rs.PathParam";
	private static final String JAX_RS_QUERY_PARAM = "javax.ws.rs.QueryParam";
	private static final String JERSEY_MULTIPART_FORM_PARAM = "com.sun.jersey.multipart.FormDataParam";
	private static final String JAX_RS_CONSUMES = "javax.ws.rs.Consumes";
	private static final String JAX_RS_PRODUCES = "javax.ws.rs.Produces";

	@SuppressWarnings("serial")
	static final List<String> PRIMITIVES = new ArrayList<String>() {

		{
			add("byte");
			add("boolean");
			add("int");
			add("integer");
			add("long");
			add("float");
			add("double");
			add("short");
			add("string");
			add("date");
			add("number");
		}
	};

	public static String parsePath(AnnotationDesc[] annotations) {
		for (AnnotationDesc annotationDesc : annotations) {
			if (annotationDesc.annotationType().qualifiedTypeName().equals(JAX_RS_PATH)) {
				for (AnnotationDesc.ElementValuePair pair : annotationDesc.elementValues()) {
					if (pair.element().name().equals("value")) {
						String path = pair.value().value().toString();
						if (path.endsWith("/")) {
							path = path.substring(0, path.length() - 1);
						}
						return path.isEmpty() || path.startsWith("/") ? path : "/" + path;
					}
				}
			}
		}
		return null;
	}

	/**
	 * Determines the String representation of the object Type.
	 * This includes the type as the first item and the format as the 2nd.
	 * Common name Swagger spec 1.2
	 * integer integer, int32
	 * long integer, int64
	 * float number, float
	 * double number, double
	 * string string
	 * byte string, byte
	 * boolean boolean
	 * date string, date
	 * dateTime string, date-time
	 * @param javaType The java type to get the swagger type and format of
	 * @return An array with the type as the first item and the format as the 2nd.
	 */
	public static String[] typeOf(String javaType) {

		if (javaType.toLowerCase().equals("byte") || javaType.equalsIgnoreCase("java.lang.Byte")) {
			return new String[] { "string", "byte" };
		} else if (javaType.toLowerCase().equals("int") || javaType.toLowerCase().equals("integer") || javaType.equalsIgnoreCase("java.lang.Integer")) {
			return new String[] { "integer", "int32" };
		} else if (javaType.toLowerCase().equals("short") || javaType.equalsIgnoreCase("java.lang.Short")) {
			return new String[] { "integer", "int32" };
		} else if (javaType.toLowerCase().equals("long") || javaType.equalsIgnoreCase("java.lang.Long")) {
			return new String[] { "integer", "int64" };
		} else if (javaType.toLowerCase().equals("float") || javaType.equalsIgnoreCase("java.lang.Float")) {
			return new String[] { "number", "float" };
		} else if (javaType.toLowerCase().equals("double") || javaType.equalsIgnoreCase("java.lang.Double")) {
			return new String[] { "number", "double" };
		} else if (javaType.toLowerCase().equals("string") || javaType.equalsIgnoreCase("java.lang.String")) {
			return new String[] { "string", null };
		} else if (javaType.toLowerCase().equals("boolean") || javaType.equalsIgnoreCase("java.lang.Boolean")) {
			return new String[] { "boolean", null };
		} else if (javaType.toLowerCase().equals("date") || javaType.equalsIgnoreCase("java.util.Date")) {
			return new String[] { "string", "date-time" };
		} else if (isCollectionPackage(javaType)
				&& (javaType.toLowerCase().endsWith("list") || javaType.toLowerCase().endsWith("array") || javaType.toLowerCase().endsWith("collection"))) {
			return new String[] { "array", null };
		} else if (isSet(javaType)) {
			return new String[] { "array", null };
		} else {

			// must be a complex type, return class name
			int i = javaType.lastIndexOf(".");
			if (i >= 0) {
				return new String[] { javaType.substring(i + 1), null };
			} else {
				return new String[] { javaType, null };
			}
		}
	}

	/**
	 * This gets whether the given type is a Set
	 * @param javaType The java type
	 * @return True if this is a Set
	 */
	public static boolean isSet(String javaType) {
		// TODO: check if implements the Set interface
		return isCollectionPackage(javaType) && javaType.toLowerCase().endsWith("set");
	}

	static boolean isCollectionPackage(String javaType) {
		// TODO: support other packages where the classes extend collections
		return javaType.toLowerCase().startsWith("java.util") || javaType.toLowerCase().startsWith("java.util.concurrent")
				|| javaType.toLowerCase().startsWith("org.apache.commons.collection") || javaType.toLowerCase().startsWith("gnu.trove")
				|| javaType.toLowerCase().startsWith("com.google.common.collect");
	}

	/**
	 * Determines the string representation of the parameter type.
	 */
	public static String paramTypeOf(Parameter parameter) {
		AnnotationParser p = new AnnotationParser(parameter);
		if (p.isAnnotatedBy(JAX_RS_PATH_PARAM)) {
			return "path";
		} else if (p.isAnnotatedBy(JAX_RS_QUERY_PARAM)) {
			return "query";
		} else if (p.isAnnotatedBy(JERSEY_MULTIPART_FORM_PARAM)) {
			// TODO: support resteasy form params...
			return "form";
		}
		return "body";
	}

	/**
	 * Determines the string representation of the parameter name.
	 */
	public static String paramNameOf(Parameter parameter) {
		// TODO (DL): make this part of Translator?
		AnnotationParser p = new AnnotationParser(parameter);
		String name = p.getAnnotationValue(JAX_RS_PATH_PARAM, "value");
		if (name == null) {
			name = p.getAnnotationValue(JAX_RS_QUERY_PARAM, "value");
		}
		if (name == null) {
			name = parameter.name();
		}
		return name;
	}

	/**
	 * This gets the list of consumes mime types from the given method
	 * @param methodDoc The method javadoc
	 * @return The list or null if none were found
	 */
	public static List<String> getConsumes(MethodDoc methodDoc) {
		return listValues(methodDoc, JAX_RS_CONSUMES, "value");
	}

	/**
	 * This gets the list of produces mime types from the given method
	 * @param methodDoc The method javadoc
	 * @return The list or null if none were found
	 */
	public static List<String> getProduces(MethodDoc methodDoc) {
		return listValues(methodDoc, JAX_RS_PRODUCES, "value");
	}

	/**
	 * This gets a list of values from an annotation that uses a string array value
	 * @param methodDoc The method doc
	 * @param qualifiedAnnotationType The FQN of the annotation
	 * @param annotationValueName The name of the value field of the annotation to use
	 * @return A list of values or null if none were found
	 */
	public static List<String> listValues(MethodDoc methodDoc, String qualifiedAnnotationType, String annotationValueName) {
		AnnotationParser p = new AnnotationParser(methodDoc);
		String[] vals = p.getAnnotationValues(qualifiedAnnotationType, annotationValueName);
		if (vals != null && vals.length > 0) {
			List<String> res = new ArrayList<String>(vals.length);
			for (String val : vals) {
				if (val != null && val.trim().length() > 0) {
					res.add(val.trim());
				}
			}
			if (!res.isEmpty()) {
				return res;
			}
		}
		return null;
	}

	public static boolean isPrimitive(Type type) {
		if (type == null) {
			return false;
		}
		return PRIMITIVES.contains(typeOf(type.qualifiedTypeName())[0]);
	}

	public static class ExcludedAnnotations implements Predicate<AnnotationDesc> {

		private final DocletOptions options;

		public ExcludedAnnotations(DocletOptions options) {
			this.options = options;
		}

		public boolean apply(AnnotationDesc annotationDesc) {
			String annotationClass = annotationDesc.annotationType().qualifiedTypeName();
			return this.options.getExcludeAnnotationClasses().contains(annotationClass);
		}
	}

	public static class JaxRsAnnotations implements Predicate<AnnotationDesc> {

		public boolean apply(AnnotationDesc annotationDesc) {
			String annotationClass = annotationDesc.annotationType().qualifiedTypeName();
			return annotationClass.startsWith(JAX_RS_ANNOTATION_PACKAGE);
		}
	}

}
