package fixtures.issue17c;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("javadoc")
@Entity
@Table(name = "account_roles")
public class AccountRole {

	@Id
	@GeneratedValue
	private int id;

	private String name;

	public AccountRole() {
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof AccountRole && this.name != null && this.name.equals(((AccountRole) obj).getName());

	}

	public AccountRole(String name) {
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
