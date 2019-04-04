package demo.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "city")
public class City  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="city_id")
	private int cityId;
	@Column(name="city")
	private String city;
	@Column(name="country_id")
	private int countryId;
	@Column(name="last_update")
	private Date lastUpdate;

	public int getCityId(){
		return cityId;
	}

	public void setCityId(int cityId){
		this.cityId = cityId;
	}
	public String getCity(){
		return city;
	}

	public void setCity(String city){
		this.city = city;
	}
	public int getCountryId(){
		return countryId;
	}

	public void setCountryId(int countryId){
		this.countryId = countryId;
	}
	public Date getLastUpdate(){
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

}