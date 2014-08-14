package fixtures.jsonview;

import java.util.ArrayList;
import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fixtures.jsonview.Person.DetailedPersonView;
import fixtures.jsonview.Person.DetailedPersonView2;
import fixtures.jsonview.Person.DetailedPersonView3;
import fixtures.jsonview.Person.SimplePersonView;
import fixtures.jsonview.Person.SimplePersonView2;

/**
 * The PersonResource represents a variant of the jackson jsonview example from http://jira.codehaus.org/browse/JACKSON-578
 * @version $Id$
 * @author conor.roche
 */
@SuppressWarnings("javadoc")
@Path("/person/")
@Produces({ MediaType.APPLICATION_JSON })
public class PersonResource {

	@GET
	@com.fasterxml.jackson.annotation.JsonView(SimplePersonView.class)
	public Collection<Person> getAll() {
		Person person = new Person();
		person.setName("conor");
		person.setAddress("conor-address");
		person.setAge(35);
		Collection<Person> res = new ArrayList<Person>();
		res.add(person);
		return res;
	}

	@Path("/{name}")
	@GET
	@com.fasterxml.jackson.annotation.JsonView(DetailedPersonView.class)
	public Person getPerson(@PathParam(value = "name") String name) {
		Person person = new Person();
		person.setName(name);
		person.setAddress(name + "-address");
		person.setAge(35);
		return person;
	}

	@Path("/{name}/2")
	@GET
	@com.fasterxml.jackson.annotation.JsonView(SimplePersonView2.class)
	public Person getPerson2(@PathParam(value = "name") String name) {
		Person person = new Person();
		person.setName(name);
		person.setAddress(name + "-address");
		person.setAge(35);
		return person;
	}

	@Path("/{name}/3")
	@GET
	@com.fasterxml.jackson.annotation.JsonView(DetailedPersonView2.class)
	public Person getPerson3(@PathParam(value = "name") String name) {
		Person person = new Person();
		person.setName(name);
		person.setAddress(name + "-address");
		person.setAge(35);
		return person;
	}

	@Path("/{name}/4")
	@GET
	@com.fasterxml.jackson.annotation.JsonView(DetailedPersonView3.class)
	public Person getPerson4(@PathParam(value = "name") String name) {
		Person person = new Person();
		person.setName(name);
		person.setAddress(name + "-address");
		person.setAge(35);
		return person;
	}

}
