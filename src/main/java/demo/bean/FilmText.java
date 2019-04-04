package demo.bean;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "film_text")
public class FilmText  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="film_id")
	private int filmId;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;

	public int getFilmId(){
		return filmId;
	}

	public void setFilmId(int filmId){
		this.filmId = filmId;
	}
	public String getTitle(){
		return title;
	}

	public void setTitle(String title){
		this.title = title;
	}
	public String getDescription(){
		return description;
	}

	public void setDescription(String description){
		this.description = description;
	}

}