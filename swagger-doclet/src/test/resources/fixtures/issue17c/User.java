package fixtures.issue17c;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue
	private int id;
	private String username;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private String email;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private Set<UserAccountRole> accountRoles = new HashSet<UserAccountRole>();

	@ManyToMany
	@JoinTable(name = "user_system_roles", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(
			name = "system_role_id", referencedColumnName = "id") })
	@JsonIgnore
	private Set<SystemRole> systemRoles = new HashSet<SystemRole>();

	public User() {
	}

	public User(String username) {
		this.username = username;
	}

	public User(String username, String firstName, String lastName, String email) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@XmlElement(name = "first_name")
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@XmlElement(name = "last_name")
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@XmlTransient
	public Set<UserAccountRole> getAccountRoles() {
		return this.accountRoles;
	}

	public void setAccountRoles(Set<UserAccountRole> accountRoles) {
		this.accountRoles = accountRoles;
	}

	@XmlTransient
	public Set<SystemRole> getSystemRoles() {
		return this.systemRoles;
	}

	public void setSystemRoles(Set<SystemRole> systemRoles) {
		this.systemRoles = systemRoles;
	}
}
