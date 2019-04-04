package demo.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "staff")
public class Staff  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="staff_id")
	private byte staffId;
	@Column(name="first_name")
	private String firstName;
	@Column(name="last_name")
	private String lastName;
	@Column(name="address_id")
	private int addressId;
	@Column(name="picture")
	private byte[] picture;
	@Column(name="email")
	private String email;
	@Column(name="store_id")
	private byte storeId;
	@Column(name="active")
	private byte active;
	@Column(name="username")
	private String username;
	@Column(name="password")
	private String password;
	@Column(name="last_update")
	private Date lastUpdate;

	public byte getStaffId(){
		return staffId;
	}

	public void setStaffId(byte staffId){
		this.staffId = staffId;
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
	public int getAddressId(){
		return addressId;
	}

	public void setAddressId(int addressId){
		this.addressId = addressId;
	}
	public byte[] getPicture(){
		return picture;
	}

	public void setPicture(byte[] picture){
		this.picture = picture;
	}
	public String getEmail(){
		return email;
	}

	public void setEmail(String email){
		this.email = email;
	}
	public byte getStoreId(){
		return storeId;
	}

	public void setStoreId(byte storeId){
		this.storeId = storeId;
	}
	public byte getActive(){
		return active;
	}

	public void setActive(byte active){
		this.active = active;
	}
	public String getUsername(){
		return username;
	}

	public void setUsername(String username){
		this.username = username;
	}
	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}
	public Date getLastUpdate(){
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

}