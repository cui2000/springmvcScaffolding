package demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import demo.bean.Staff;
import demo.bean.Store;

@Transactional
public interface IBusinessService {

	public List<Staff> findStaff(int startRow, int rowSize);

	public int getStaffCount();

	public List<Store> findStore(int startRow, int rowSize);

	public int getStoreCount();

	// public List<Staff> findStaff(int startRow, int rowSize);
	//
	// public int getStaffCount();
	//
	// public List<Staff> findStaff(int startRow, int rowSize);
	//
	// public int getStaffCount();
	//
	// public List<Staff> findStaff(int startRow, int rowSize);
	//
	// public int getStaffCount();

	// public boolean rentFilm(int customerId,int storeId,int filmId);

	public boolean rentFilm(int customerId, int staffId, int inventoryId, Date date);

	public boolean returnFilm(int rentalId, Date date);

}
