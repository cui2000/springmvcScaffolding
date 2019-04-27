package demo.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name = "store")
public class Store implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "store_id")
	private Integer storeId;
	@Column(name = "manager_staff_id")
	private Integer managerStaffId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "address_id")
	private Address address;
	@Column(name = "last_update")
	private Date lastUpdate;

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getManagerStaffId() {
		return managerStaffId;
	}

	public void setManagerStaffId(Integer managerStaffId) {
		this.managerStaffId = managerStaffId;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}