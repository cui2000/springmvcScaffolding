package demo.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "country")
public class Country  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="country_id")
	private int countryId;
	@Column(name="country")
	private String country;
	@Column(name="last_update")
	private Date lastUpdate;

	public int getCountryId(){
		return countryId;
	}

	public void setCountryId(int countryId){
		this.countryId = countryId;
	}
	public String getCountry(){
		return country;
	}

	public void setCountry(String country){
		this.country = country;
	}
	public Date getLastUpdate(){
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

}