package fixtures.issue17c;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("javadoc")
@Entity
@Table(name = "accounts")
public class Account {

	@Id
	@GeneratedValue
	private int id;

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
