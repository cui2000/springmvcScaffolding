package demo.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "category")
public class Category  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="category_id")
	private byte categoryId;
	@Column(name="name")
	private String name;
	@Column(name="last_update")
	private Date lastUpdate;

	public byte getCategoryId(){
		return categoryId;
	}

	public void setCategoryId(byte categoryId){
		this.categoryId = categoryId;
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