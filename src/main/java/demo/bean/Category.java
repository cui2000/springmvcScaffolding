package demo.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name = "category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "category_id")
	private Integer categoryId;
	@Column(name = "name")
	private String name;
	@Column(name = "last_update")
	private Date lastUpdate;

	// 两个实体类互相设置这种关系会造成解析成json时死循环，要慎重
	// @ManyToMany(targetEntity = Film.class, mappedBy = "categories") // 由Film类维护外键关系
	// private Set<Film> films;

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	// public Set<Film> getFilms() {
	// return films;
	// }
	//
	// public void setFilms(Set<Film> films) {
	// this.films = films;
	// }

}