package fixtures.issue17c;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("javadoc")
@Entity
@Table(name = "system_roles")
public class SystemRole {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	public SystemRole() {
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof SystemRole && this.name != null && this.name.equals(((SystemRole) obj).getName());

	}

	public SystemRole(String name) {
		this.name = name;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
