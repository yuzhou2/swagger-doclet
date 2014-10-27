package fixtures.issue17d.data;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;

@SuppressWarnings("javadoc")
@Entity
@Table(name = "blacklist")
public class BlacklistedDevice {

	@EmbeddedId
	private BlacklistedDeviceId id;
	private String reason;
	private Date timestamp;

	public BlacklistedDevice() {
		this.id = new BlacklistedDeviceId();
	}

	public BlacklistedDevice(String deviceName, String deviceNameType, String reason, Date timestamp) {
		this.id = new BlacklistedDeviceId(deviceName, deviceNameType);
		this.reason = reason;
		this.timestamp = timestamp;
	}

	public String getId() {
		return getDeviceNameType() + "-" + getDeviceName();
	}

	public void setId(String id) {
		String[] name = id.split("-");
		setDeviceNameType(name[0]);
		setDeviceName(name[1]);
	}

	@XmlElement(name = "device_name")
	public String getDeviceName() {
		return this.id.getDeviceName();
	}

	public void setDeviceName(String deviceName) {
		this.id.setDeviceName(deviceName);
	}

	@XmlElement(name = "device_name_type")
	public String getDeviceNameType() {
		return this.id.getDeviceNameType();
	}

	public void setDeviceNameType(String deviceNameType) {
		this.id.setDeviceNameType(deviceNameType);
	}

	public String getReason() {
		return this.reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	@SuppressWarnings({ "serial" })
	@Embeddable
	public static class BlacklistedDeviceId implements Serializable {

		@Column(name = "device_name")
		private String deviceName;
		@Column(name = "device_name_type")
		private String deviceNameType;

		public BlacklistedDeviceId() {

		}

		public BlacklistedDeviceId(String deviceName, String deviceNameType) {
			this.deviceName = deviceName;
			this.deviceNameType = deviceNameType;
		}

		public String getDeviceName() {
			return this.deviceName;
		}

		public void setDeviceName(String deviceName) {
			this.deviceName = deviceName;
		}

		public String getDeviceNameType() {
			return this.deviceNameType;
		}

		public void setDeviceNameType(String deviceNameType) {
			this.deviceNameType = deviceNameType;
		}
	}
}
