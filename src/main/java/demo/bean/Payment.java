package demo.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "payment")
public class Payment  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="payment_id")
	private int paymentId;
	@Column(name="customer_id")
	private int customerId;
	@Column(name="staff_id")
	private byte staffId;
	@Column(name="rental_id")
	private int rentalId;
	@Column(name="amount")
	private BigDecimal amount;
	@Column(name="payment_date")
	private Date paymentDate;
	@Column(name="last_update")
	private Date lastUpdate;

	public int getPaymentId(){
		return paymentId;
	}

	public void setPaymentId(int paymentId){
		this.paymentId = paymentId;
	}
	public int getCustomerId(){
		return customerId;
	}

	public void setCustomerId(int customerId){
		this.customerId = customerId;
	}
	public byte getStaffId(){
		return staffId;
	}

	public void setStaffId(byte staffId){
		this.staffId = staffId;
	}
	public int getRentalId(){
		return rentalId;
	}

	public void setRentalId(int rentalId){
		this.rentalId = rentalId;
	}
	public BigDecimal getAmount(){
		return amount;
	}

	public void setAmount(BigDecimal amount){
		this.amount = amount;
	}
	public Date getPaymentDate(){
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate){
		this.paymentDate = paymentDate;
	}
	public Date getLastUpdate(){
		return lastUpdate;
	}

	public void setLastUpdate(Date lastUpdate){
		this.lastUpdate = lastUpdate;
	}

}