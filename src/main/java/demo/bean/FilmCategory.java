package demo.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "film_category")
public class FilmCategory  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="film_id")
	private int filmId;
	@Id
	@Column(name="category_id")
	private Integer categoryId;
	@Column(name="last_update")
	private Date lastUpdate;

	public int getFilmId(){
		return filmId;
	}

	public void setFilmId(int filmId){
		this.filmId = filmId;
	}
	public Integer getCategoryId(){
		return categoryId;
	}

	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}
	public Date getLastUpdate(){
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

}