package demo.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "inventory")
public class Inventory implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "inventory_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int inventoryId;
	@Column(name = "film_id")
	private int filmId;
	@Column(name = "store_id")
	private Integer storeId;
	@Column(name = "last_update")
	private Date lastUpdate;

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public int getFilmId() {
		return filmId;
	}

	public void setFilmId(int filmId) {
		this.filmId = filmId;
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Date getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}