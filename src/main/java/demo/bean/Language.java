package demo.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "language")
public class Language  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="language_id")
	private Integer languageId;
	@Column(name="name")
	private String name;
	@Column(name="last_update")
	private Date lastUpdate;

	public Integer getLanguageId(){
		return languageId;
	}

	public void setLanguageId(Integer languageId){
		this.languageId = languageId;
	}
	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
	public Date getLastUpdate(){
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

}