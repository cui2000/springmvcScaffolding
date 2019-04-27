package demo.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity(name = "film")
public class Film implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "film_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int filmId;
	@Column(name = "title")
	private String title;
	@Column(name = "description")
	private String description;
	@Column(name = "release_year")
	private int releaseYear;
	@Column(name = "language_id")
	private Integer languageId;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "language_id", insertable = false, updatable = false)
	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }) // 避免转json时因为null而异常
	private Language language;
	@Column(name = "original_language_id")
	private Integer originalLanguageId;
	@Column(name = "rental_duration")
	private Integer rentalDuration;
	@Column(name = "rental_rate")
	private BigDecimal rentalRate;
	@Column(name = "length")
	private int length;
	@Column(name = "replacement_cost")
	private BigDecimal replacementCost;
	@Column(name = "rating")
	private String rating;
	@Column(name = "special_features")
	private String specialFeatures;
	@Column(name = "last_update")
	private Date lastUpdate;

	@ManyToMany(targetEntity = Category.class, fetch = FetchType.LAZY)
	@JoinTable(name = "film_category", joinColumns = @JoinColumn(name = "film_id", referencedColumnName = "film_id"), inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "category_id"))
//	@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" }) // 避免转json时因为null而异常
	private Set<Category> categories;
	// private Set<Category> categories = new HashSet();

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public Integer getLanguageId() {
		return languageId;
	}

	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

	public Integer getOriginalLanguageId() {
		return originalLanguageId;
	}

	public void setOriginalLanguageId(Integer originalLanguageId) {
		this.originalLanguageId = originalLanguageId;
	}

	public Integer getRentalDuration() {
		return rentalDuration;
	}

	public void setRentalDuration(Integer rentalDuration) {
		this.rentalDuration = rentalDuration;
	}

	public BigDecimal getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(BigDecimal rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public BigDecimal getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(BigDecimal replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

}