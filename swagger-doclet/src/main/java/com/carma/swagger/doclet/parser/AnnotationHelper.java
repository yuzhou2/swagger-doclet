package com.carma.swagger.doclet.parser;

import static com.google.common.collect.Collections2.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Predicate;
import com.sun.javadoc.AnnotationDesc;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Parameter;
import com.sun.javadoc.ParameterizedType;
import com.sun.javadoc.SeeTag;
import com.sun.javadoc.Tag;
import com.sun.javadoc.Type;
import com.sun.javadoc.TypeVariable;

public class AnnotationHelper {

	private static final String JAX_RS_ANNOTATION_PACKAGE = "javax.ws.rs";
	private static final String JAX_RS_PATH = "javax.ws.rs.Path";
	private static final String JAX_RS_PATH_PARAM = "javax.ws.rs.PathParam";
	private static final String JAX_RS_QUERY_PARAM = "javax.ws.rs.QueryParam";
	private static final String JAX_RS_HEADER_PARAM = "javax.ws.rs.HeaderParam";
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
			add("char");
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
		} else if (javaType.toLowerCase().equals("char") || javaType.equalsIgnoreCase("java.lang.Character")) {
			return new String[] { "string", null };
		} else if (javaType.toLowerCase().equals("boolean") || javaType.equalsIgnoreCase("java.lang.Boolean")) {
			return new String[] { "boolean", null };
		} else if (javaType.toLowerCase().equals("date") || javaType.equalsIgnoreCase("java.util.Date")) {
			return new String[] { "string", "date-time" };
		} else if (isCollection(javaType)
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
	 * This gets the type that a container holds
	 * @param type The raw type like Collection<String>
	 * @param varsToTypes A map of variables to types for parameterized types, optional if null parameterized types
	 *            will not be handled
	 * @return The container type or null if not a collection
	 */
	public static Type getContainerType(Type type, Map<String, Type> varsToTypes) {
		Type result = null;
		ParameterizedType pt = type.asParameterizedType();
		if (pt != null && AnnotationHelper.isCollection(type.qualifiedTypeName())) {
			Type[] typeArgs = pt.typeArguments();
			if (typeArgs != null && typeArgs.length > 0) {
				result = typeArgs[0];
			}
		}
		// if its a ref to a param type replace with the type impl
		if (result != null) {
			Type paramType = getVarType(result.asTypeVariable(), varsToTypes);
			if (paramType != null) {
				return paramType;
			}
		}
		return result;
	}

	/**
	 * This finds a variable type and returns its impl from the given map
	 * @param var The variable type to find
	 * @param varsToTypes The map of variables to types
	 * @return The result.
	 */
	public static Type getVarType(TypeVariable var, Map<String, Type> varsToTypes) {
		Type res = null;
		if (var != null && varsToTypes != null) {
			Type type = varsToTypes.get(var.qualifiedTypeName());
			while (type != null) {
				res = type;
				type = varsToTypes.get(type.qualifiedTypeName());
			}
		}
		return res;
	}

	/**
	 * This gets whether the given type is a Set
	 * @param javaType The java type
	 * @return True if this is a Set
	 */
	public static boolean isSet(String javaType) {
		try {
			return java.util.Set.class.isAssignableFrom(Class.forName(javaType));
		} catch (ClassNotFoundException ex) {
			return false;
		}
	}

	/**
	 * This gets whether the given type is a Collection
	 * @param javaType The java type
	 * @return True if this is a collection
	 */
	public static boolean isCollection(String javaType) {
		try {
			return java.util.Collection.class.isAssignableFrom(Class.forName(javaType));
		} catch (ClassNotFoundException ex) {
			return false;
		}
	}

	/**
	 * This gets whether the given type is a Map
	 * @param javaType The java type
	 * @return True if this is a map
	 */
	public static boolean isMap(String javaType) {
		try {
			return java.util.Map.class.isAssignableFrom(Class.forName(javaType));
		} catch (ClassNotFoundException ex) {
			return false;
		}
	}

	/**
	 * Determines the string representation of the parameter type.
	 */
	public static String paramTypeOf(Parameter parameter) {
		AnnotationParser p = new AnnotationParser(parameter);
		if (p.isAnnotatedBy(JAX_RS_PATH_PARAM)) {
			return "path";
		} else if (p.isAnnotatedBy(JAX_RS_HEADER_PARAM)) {
			return "header";
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
			name = p.getAnnotationValue(JAX_RS_HEADER_PARAM, "value");
		}
		if (name == null) {
			name = parameter.name();
		}
		return name;
	}

	/**
	 * This gets the json views for the given method/field
	 * @param doc The method/field to get the json views of
	 * @return The json views for the given method/field or null if there were none
	 */
	public static ClassDoc[] getJsonViews(com.sun.javadoc.ProgramElementDoc doc) {
		AnnotationParser p = new AnnotationParser(doc);
		ClassDoc[] viewClasses = p.getAnnotationClassDocValues("com.fasterxml.jackson.annotation.JsonView", "value");
		if (viewClasses == null) {
			viewClasses = p.getAnnotationClassDocValues("org.codehaus.jackson.map.annotate.JsonView", "value");
		}
		return viewClasses;
	}

	/**
	 * This checks if an item view e.g optional json view that can be on a getter/field match any of the
	 * given operation views, that is it can be the same or extend/implement one of the operation views.
	 * @param operationViews The operation views that indicate which views apply to the operation.
	 * @param itemsViews The views that are on the getter/field
	 * @return True if the field/getter is part of the view
	 */
	public static boolean isItemPartOfView(ClassDoc[] operationViews, ClassDoc[] itemsViews) {
		if (operationViews != null && itemsViews != null) {
			// check that one of the operation views is a subclass of an item view
			for (ClassDoc operationView : operationViews) {
				if (isAssignableFrom(itemsViews, operationView)) {
					return true;
				}
			}
			return false;
		}
		return true;
	}

	/**
	 * This checks if the given clazz is the same as or implments or is a subclass/sub interface of
	 * any of the given classes
	 * @param superClasses the classes to check if they are super classes/super interfaces of the given class
	 * @param clazz The class to check if it extends/implements any of the given classes
	 * @return True if the given class extends/implements any of the given classes/interfaces
	 */
	public static boolean isAssignableFrom(ClassDoc[] superClasses, ClassDoc clazz) {
		if (superClasses != null) {
			for (ClassDoc superClazz : superClasses) {
				if (isAssignableFrom(superClazz, clazz)) {
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isAssignableFrom(ClassDoc superClass, ClassDoc clazz) {
		if (clazz.subclassOf(superClass)) {
			return true;
		}
		if (superClass.isInterface()) {
			// if one of the classes interfaces is the super class interface
			// or a subclass interface of the super class then its assignable
			ClassDoc[] subInterfaces = clazz.interfaces();
			if (subInterfaces != null) {
				for (ClassDoc subInterface : subInterfaces) {
					if (subInterface.subclassOf(superClass)) {
						return true;
					}
				}
			}
		}
		return false;
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
	 * @param doc The method/field doc
	 * @param qualifiedAnnotationType The FQN of the annotation
	 * @param annotationValueName The name of the value field of the annotation to use
	 * @return A list of values or null if none were found
	 */
	public static List<String> listValues(com.sun.javadoc.ProgramElementDoc doc, String qualifiedAnnotationType, String annotationValueName) {
		AnnotationParser p = new AnnotationParser(doc);
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

	/**
	 * This gets whether the given type is primitive
	 * @param type The type to check
	 * @return True if the given type is primitive
	 */
	public static boolean isPrimitive(Type type) {
		if (type == null) {
			return false;
		}
		return PRIMITIVES.contains(typeOf(type.qualifiedTypeName())[0]);
	}

	/**
	 * This gets whether the given item has any of the given tags
	 * @param item The javadoc item
	 * @param matchTags The names of the tags to look for
	 * @return True if the item has any of the given tags
	 */
	public static boolean hasTag(com.sun.javadoc.ProgramElementDoc item, Collection<String> matchTags) {
		if (matchTags != null) {
			for (String matchTag : matchTags) {
				Tag[] tags = item.tags(matchTag);
				if (tags != null && tags.length > 0) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * This gets a csv javadoc tag value as a list of the values in the csv for the first matched tag.
	 * e.g. @myTag 1,2,3 would return a list of 1,2,3 assuming matchTags contained myTag
	 * @param item The javadoc item
	 * @param matchTags The tags to match
	 * @return The csv values of the first matching tags value or null if there were none.
	 */
	public static List<String> getTagCsvValues(com.sun.javadoc.ProgramElementDoc item, Collection<String> matchTags) {
		String value = getTagValue(item, matchTags);
		if (value != null) {
			String[] vals = value.split(",");
			if (vals != null && vals.length > 0) {
				List<String> res = new ArrayList<String>();
				for (String val : vals) {
					if (val != null && val.trim().length() > 0) {
						res.add(val.trim());
					}
				}
				return res.isEmpty() ? null : res;
			}
		}
		return null;
	}

	/**
	 * This gets the value of the first tag found from the given collection of tag names
	 * @param item The item to get the tag value of
	 * @param matchTags The collection of tag names of the tag to get a value of
	 * @return The value of the first tag found with the name in the given collection or null if either the tag
	 *         was not present or had no value
	 */
	public static String getTagValue(com.sun.javadoc.ProgramElementDoc item, Collection<String> matchTags) {
		String customValue = null;
		if (matchTags != null) {
			for (String matchTag : matchTags) {
				Tag[] tags = item.tags(matchTag);
				if (tags != null && tags.length > 0) {
					customValue = tags[0].text().trim();
					if (customValue.length() == 0) {
						customValue = null;
					}
					break;
				}
			}
		}
		return customValue;
	}

	private static final Set<String> DEPRECATED_TAGS = new HashSet<String>();
	private static final Set<String> DEPRECATED_ANNOTATIONS = new HashSet<String>();
	static {
		DEPRECATED_TAGS.add("deprecated");
		DEPRECATED_TAGS.add("Deprecated");
		DEPRECATED_ANNOTATIONS.add("java.lang.Deprecated");
	}

	/**
	 * This gets whether the given annotations have one of the deprecated ones
	 * @param annotations The annotations to check
	 * @return True if the annotations array contains a deprecated one
	 */
	public static boolean hasDeprecated(AnnotationDesc[] annotations) {
		if (annotations != null && annotations.length > 0) {
			List<AnnotationDesc> allAnnotations = Arrays.asList(annotations);
			Collection<AnnotationDesc> excluded = filter(allAnnotations, new AnnotationHelper.ExcludedAnnotations(DEPRECATED_ANNOTATIONS));
			if (!excluded.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	/**
	 * This gets whether the given item is marked as deprecated either via a javadoc tag
	 * or an annotation
	 * @param item The item to check
	 * @return True if the item is flagged as deprecated
	 */
	public static boolean isDeprecated(com.sun.javadoc.ProgramElementDoc item) {
		if (hasTag(item, DEPRECATED_TAGS)) {
			return true;
		}
		return hasDeprecated(item.annotations());
	}

	/**
	 * This gets values of any of the javadoc tags that are in the given collection
	 * @param item The javadoc item to get the tags of
	 * @param matchTags The names of the tags to get
	 * @return A list of tag values or null if none were found
	 */
	public static List<String> getTagValues(com.sun.javadoc.ProgramElementDoc item, Collection<String> matchTags) {
		List<String> res = null;
		if (matchTags != null) {
			for (String matchTag : matchTags) {
				Tag[] tags = item.tags(matchTag);
				if (tags != null && tags.length > 0) {
					for (Tag tag : tags) {
						if (res == null) {
							res = new ArrayList<String>();
						}
						String customValue = tag.text().trim();
						if (customValue.length() > 0) {
							res.add(customValue);
						}
					}
				}
			}
		}
		return res == null || res.isEmpty() ? null : res;
	}

	/**
	 * This builds a map of FQN to type for all see annotations
	 * on the given items javadoc
	 * @param item The item to get the see types of
	 * @return A map of see types or an empty map if there were no see tags.
	 */
	public static Map<String, Type> readSeeTypes(com.sun.javadoc.ProgramElementDoc item) {
		Map<String, Type> types = new HashMap<String, Type>();
		SeeTag[] seeTags = item.seeTags();
		if (seeTags != null) {
			for (SeeTag seeTag : seeTags) {
				Type type = seeTag.referencedClass();
				types.put(seeTag.referencedClassName(), type);
			}
		}
		return types;
	}

	/**
	 * This finds a model class by the given name
	 * @param classes The model classes
	 * @param qualifiedClassName The FQN of the class
	 * @return {@link ClassDoc} found among all classes processed by the doclet based on a given <code>qualifiedClassName</code>; <code>null</code> if not found
	 */
	public static ClassDoc findModel(Collection<ClassDoc> classes, String qualifiedClassName) {
		if (classes != null && qualifiedClassName != null) {
			for (ClassDoc cls : classes) {
				if (qualifiedClassName.equals(cls.qualifiedName())) {
					return cls;
				}
			}
		}
		return null;
	}

	public static class ExcludedAnnotations implements Predicate<AnnotationDesc> {

		private final Collection<String> annotationClasses;

		public ExcludedAnnotations(Collection<String> annotationClasses) {
			this.annotationClasses = annotationClasses;
		}

		public boolean apply(AnnotationDesc annotationDesc) {
			String annotationClass = annotationDesc.annotationType().qualifiedTypeName();
			return this.annotationClasses.contains(annotationClass);
		}
	}

	public static class JaxRsAnnotations implements Predicate<AnnotationDesc> {

		public boolean apply(AnnotationDesc annotationDesc) {
			String annotationClass = annotationDesc.annotationType().qualifiedTypeName();
			return annotationClass.startsWith(JAX_RS_ANNOTATION_PACKAGE);
		}
	}

}
