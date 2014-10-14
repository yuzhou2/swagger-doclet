package fixtures.issue17c;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@Entity
@Table(name = "user_account_roles")
@IdClass(UserAccountRole.UserAccountRolePK.class)
public class UserAccountRole {

	@Id
	private int userId;
	@Id
	private int accountId;
	@Id
	private int accountRoleId;

	@ManyToOne
	@JoinColumn(name = "user_id", updatable = false, insertable = false)
	private User user;

	@ManyToOne
	@JoinColumn(name = "account_id", updatable = false, insertable = false)
	private Account account;

	@ManyToOne
	@JoinColumn(name = "account_role_id", updatable = false, insertable = false)
	private AccountRole accountRole;

	public UserAccountRole() {
	}

	public UserAccountRole(int userId, int accountId, int accountRoleId) {
		this.userId = userId;
		this.accountId = accountId;
		this.accountRoleId = accountRoleId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Account getAccount() {
		return this.account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@XmlElement(name = "account_role")
	public AccountRole getAccountRole() {
		return this.accountRole;
	}

	public void setAccountRole(AccountRole accountRole) {
		this.accountRole = accountRole;
	}

	@XmlElement(name = "user_id")
	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@XmlElement(name = "account_id")
	public int getAccountId() {
		return this.accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	@XmlElement(name = "account_role_id")
	public int getAccountRoleId() {
		return this.accountRoleId;
	}

	public void setAccountRoleId(int accountRoleId) {
		this.accountRoleId = accountRoleId;
	}

	static class UserAccountRolePK implements Serializable {

		@Column(name = "user_id")
		private int userId;
		@Column(name = "account_id")
		private int accountId;
		@Column(name = "account_role_id")
		private int accountRoleId;

		@Override
		public boolean equals(Object o) {
			if (this == o) {
				return true;
			}
			if (!(o instanceof UserAccountRolePK)) {
				return false;
			}

			UserAccountRolePK that = (UserAccountRolePK) o;

			return this.accountId == that.accountId && this.accountRoleId == that.accountRoleId && this.userId == that.userId;
		}

		@Override
		public int hashCode() {
			int result = this.userId;
			result = 31 * result + this.accountId;
			result = 31 * result + this.accountRoleId;
			return result;
		}

		public int getUserId() {
			return this.userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public int getAccountId() {
			return this.accountId;
		}

		public void setAccountId(int accountId) {
			this.accountId = accountId;
		}

		public int getAccountRoleId() {
			return this.accountRoleId;
		}

		public void setAccountRoleId(int accountRoleId) {
			this.accountRoleId = accountRoleId;
		}
	}
}
