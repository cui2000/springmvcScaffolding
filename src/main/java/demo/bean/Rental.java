package demo.bean;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "rental")
public class Rental  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="rental_id")
	private int rentalId;
	@Column(name="rental_date")
	private Date rentalDate;
	@Column(name="inventory_id")
	private int inventoryId;
	@Column(name="customer_id")
	private int customerId;
	@Column(name="return_date")
	private Date returnDate;
	@Column(name="staff_id")
	private byte staffId;
	@Column(name="last_update")
	private Date lastUpdate;

	public int getRentalId(){
		return rentalId;
	}

	public void setRentalId(int rentalId){
		this.rentalId = rentalId;
	}
	public Date getRentalDate(){
		return rentalDate;
	}

	public void setRentalDate(Date rentalDate){
		this.rentalDate = rentalDate;
	}
	public int getInventoryId(){
		return inventoryId;
	}

	public void setInventoryId(int inventoryId){
		this.inventoryId = inventoryId;
	}
	public int getCustomerId(){
		return customerId;
	}

	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}
	public Date getReturnDate(){
		return returnDate;
	}

	public void setReturnDate(Date returnDate){
		this.returnDate = returnDate;
	}
	public byte getStaffId(){
		return staffId;
	}

	public void setStaffId(byte staffId){
		this.staffId = staffId;
	}
	public Date getLastUpdate(){
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

}