package demo.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "customer")
public class Customer  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="customer_id")
	private int customerId;
	@Column(name="store_id")
	private byte storeId;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="email")
	private String email;
	@Column(name="address_id")
	private int addressId;
	@Column(name="active")
	private byte active;
	@Column(name="create_date")
	private Date createDate;
	@Column(name="last_update")
	private Date lastUpdate;

	public int getCustomerId(){
		return customerId;
	}

	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}
	public byte getStoreId(){
		return storeId;
	}

	public void setStoreId(byte storeId){
		this.storeId = storeId;
	}
	public String getFirstName(){
		return firstName;
	}

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}
	public String getLastName(){
		return lastName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}
	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}
	public int getAddressId(){
		return addressId;
	}

	public void setAddressId(int addressId){
		this.addressId = addressId;
	}
	public byte getActive(){
		return active;
	}

	public void setActive(byte active){
		this.active = active;
	}
	public Date getCreateDate(){
		return createDate;
	}

	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	public Date getLastUpdate(){
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

}