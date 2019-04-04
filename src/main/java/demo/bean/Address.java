package demo.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "address")
public class Address  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="address_id")
	private int addressId;
	@Column(name="address")
	private String address;
	@Column(name="address2")
	private String address2;
	@Column(name="district")
	private String district;
	@Column(name="city_id")
	private int cityId;
	@Column(name="postal_code")
	private String postalCode;
	@Column(name="phone")
	private String phone;
	@Column(name="location")
	private String location;
	@Column(name="last_update")
	private Date lastUpdate;

	public int getAddressId(){
		return addressId;
	}

	public void setAddressId(int addressId){
		this.addressId = addressId;
	}
	public String getAddress(){
		return address;
	}

	public void setAddress(String address){
		this.address = address;
	}
	public String getAddress2(){
		return address2;
	}

	public void setAddress2(String address2){
		this.address2 = address2;
	}
	public String getDistrict(){
		return district;
	}

	public void setDistrict(String district){
		this.district = district;
	}
	public int getCityId(){
		return cityId;
	}

	public void setCityId(int cityId){
		this.cityId = cityId;
	}
	public String getPostalCode(){
		return postalCode;
	}

	public void setPostalCode(String postalCode){
		this.postalCode = postalCode;
	}
	public String getPhone(){
		return phone;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}
	public String getLocation(){
		return location;
	}

	public void setLocation(String location){
		this.location = location;
	}
	public Date getLastUpdate(){
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

}