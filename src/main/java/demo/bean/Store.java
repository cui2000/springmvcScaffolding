package demo.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "store")
public class Store  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="store_id")
	private byte storeId;
	@Column(name="manager_staff_id")
	private byte managerStaffId;
	@Column(name="address_id")
	private int addressId;
	@Column(name="last_update")
	private Date lastUpdate;

	public byte getStoreId(){
		return storeId;
	}

	public void setStoreId(byte storeId){
		this.storeId = storeId;
	}
	public byte getManagerStaffId(){
		return managerStaffId;
	}

	public void setManagerStaffId(byte managerStaffId){
		this.managerStaffId = managerStaffId;
	}
	public int getAddressId(){
		return addressId;
	}

	public void setAddressId(int addressId){
		this.addressId = addressId;
	}
	public Date getLastUpdate(){
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

}