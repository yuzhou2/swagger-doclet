package fixtures.jsonview;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * The Person represents an entity that uses different jsonviews
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
public class Person {

	// define 2 json views using classes
	public static class SimplePersonView {
		// noop
	}

	public static class DetailedPersonView extends SimplePersonView {
		// noop
	}

	// define 2 more json views using interfaces
	public static interface SimplePersonView2 {
		// noop
	}

	public static interface DetailedPersonView2 extends SimplePersonView2 {
		// noop
	}

	public static class DetailedPersonView3 implements DetailedPersonView2 {
		// noop
	}

	String name;
	String address;
	int age;
	int height;

	String name2;
	int age2;

	String name3;

	@JsonView({ SimplePersonView.class })
	public String getName() {
		return this.name;
	}

	@JsonView({ DetailedPersonView.class })
	public String getAddress() {
		return this.address;
	}

	@JsonView({ DetailedPersonView.class })
	public int getAge() {
		return this.age;
	}

	@JsonView({ SimplePersonView2.class })
	public String getName2() {
		return this.name2;
	}

	@JsonView({ DetailedPersonView2.class })
	public int getAge2() {
		return this.age2;
	}

	@JsonView({ DetailedPersonView3.class })
	public String getName3() {
		return this.name3;
	}

	// height common to all views as no view on it
	public int getHeight() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public void setAge2(int age2) {
		this.age2 = age2;
	}

	public void setName3(String name3) {
		this.name3 = name3;
	}

}