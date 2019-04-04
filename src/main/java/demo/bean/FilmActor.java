package demo.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "film_actor")
public class FilmActor  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="actor_id")
	private int actorId;
	@Id
	@Column(name="film_id")
	private int filmId;
	@Column(name="last_update")
	private Date lastUpdate;

	public int getActorId(){
		return actorId;
	}

	public void setActorId(int actorId){
		this.actorId = actorId;
	}
	public int getFilmId(){
		return filmId;
	}

	public void setFilmId(int filmId){
		this.filmId = filmId;
	}
	public Date getLastUpdate(){
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

}