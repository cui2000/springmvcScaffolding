package demo.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "film")
public class Film  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="film_id")
	private int filmId;
	@Column(name="title")
	private String title;
	@Column(name="description")
	private String description;
	@Column(name="release_year")
	private int releaseYear;
	@Column(name="language_id")
	private byte languageId;
	@Column(name="original_language_id")
	private byte originalLanguageId;
	@Column(name="rental_duration")
	private byte rentalDuration;
	@Column(name="rental_rate")
	private BigDecimal rentalRate;
	@Column(name="length")
	private int length;
	@Column(name="replacement_cost")
	private BigDecimal replacementCost;
	@Column(name="rating")
	private String rating;
	@Column(name="special_features")
	private String specialFeatures;
	@Column(name="last_update")
	private Date lastUpdate;

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
	public int getReleaseYear(){
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear){
		this.releaseYear = releaseYear;
	}
	public byte getLanguageId(){
		return languageId;
	}

	public void setLanguageId(byte languageId){
		this.languageId = languageId;
	}
	public byte getOriginalLanguageId(){
		return originalLanguageId;
	}

	public void setOriginalLanguageId(byte originalLanguageId){
		this.originalLanguageId = originalLanguageId;
	}
	public byte getRentalDuration(){
		return rentalDuration;
	}

	public void setRentalDuration(byte rentalDuration){
		this.rentalDuration = rentalDuration;
	}
	public BigDecimal getRentalRate(){
		return rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate){
		this.rentalRate = rentalRate;
	}
	public int getLength(){
		return length;
	}

	public void setLength(int length){
		this.length = length;
	}
	public BigDecimal getReplacementCost(){
		return replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost){
		this.replacementCost = replacementCost;
	}
	public String getRating(){
		return rating;
	}

	public void setRating(String rating){
		this.rating = rating;
	}
	public String getSpecialFeatures(){
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures){
		this.specialFeatures = specialFeatures;
	}
	public Date getLastUpdate(){
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

}