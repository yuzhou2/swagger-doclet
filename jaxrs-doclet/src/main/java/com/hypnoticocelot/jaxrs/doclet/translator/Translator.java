package com.hypnoticocelot.jaxrs.doclet.translator;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.sun.javadoc.ClassDoc;
import com.sun.javadoc.FieldDoc;
import com.sun.javadoc.MethodDoc;
import com.sun.javadoc.Type;

public interface Translator {

	OptionalName typeName(Type type, ClassDoc[] views);

	OptionalName typeName(Type type);

	OptionalName fieldName(FieldDoc field);

	OptionalName methodName(MethodDoc method);

	class OptionalName {

		private final Status status;
		private final String name;
		private final String format;

		private OptionalName(Status status, String name, String format) {
			this.status = status;
			this.name = name;
			this.format = format;
		}

		public static OptionalName presentOrMissing(String name) {
			if (!Strings.isNullOrEmpty(name)) {
				return new OptionalName(Status.PRESENT, name, null);
			} else {
				return new OptionalName(Status.MISSING, null, null);
			}
		}

		public static OptionalName presentOrMissing(String name, String format) {
			if (!Strings.isNullOrEmpty(name)) {
				return new OptionalName(Status.PRESENT, name, format);
			} else {
				return new OptionalName(Status.MISSING, null, format);
			}
		}

		public static OptionalName ignored() {
			return new OptionalName(Status.IGNORED, null, null);
		}

		public String value() {
			return name;
		}

		/**
		 * This gets the format
		 * @return the format
		 */
		public String getFormat() {
			return this.format;
		}

		public boolean isPresent() {
			return status == Status.PRESENT;
		}

		public boolean isMissing() {
			return status == Status.MISSING;
		}

		private static enum Status {
			PRESENT,
			IGNORED,
			MISSING
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (o == null || getClass() != o.getClass()) {
				return false;
			}
			OptionalName that = (OptionalName) o;
			return Objects.equal(status, that.status) && Objects.equal(name, that.name);
		}

		@Override
		public int hashCode() {
			return Objects.hashCode(status, name);
		}

		@Override
		public String toString() {
			return Objects.toStringHelper(this).add("status", status).add("name", name).toString();
		}
	}

}
