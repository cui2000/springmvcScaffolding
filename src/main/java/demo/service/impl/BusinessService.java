package demo.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bean.Film;
import demo.bean.Payment;
import demo.bean.Rental;
import demo.bean.Staff;
import demo.bean.Store;
import demo.dao.IPaymentDao;
import demo.dao.IRentalDao;
import demo.dao.IStaffDao;
import demo.service.IBusinessService;
import demo.service.IInventoryService;

@Service("businessService")
public class BusinessService implements IBusinessService {

	@Autowired
	private IPaymentDao paymentDao;
	@Autowired
	private IRentalDao rentalDao;
	@Autowired
	private IStaffDao staffDao;
	@Autowired
	private IInventoryService inventoryService;

	@Override
	public List<Staff> findStaff(int startRow, int rowSize) {
		return staffDao.findAll(startRow, rowSize);
	}

	@Override
	public List<Store> findStore(int startRow, int rowSize) {
		return null;
	}

	@Override
	public int getStaffCount() {
		return 0;
	}

	@Override
	public int getStoreCount() {
		return 0;
	}

	@Override
	public boolean rentFilm(int customerId, int staffId, int inventoryId, Date date) {
		// 查看是否已出租
		if (inventoryService.inventoryInStock(inventoryId)) {
			// 创建出租记录
			Rental r = new Rental();
			r.setCustomerId(customerId);
			r.setInventoryId(inventoryId);
			r.setLastUpdate(date);
			r.setRentalDate(date);
			r.setStaffId(staffId);
			rentalDao.add(r);

			// 获取电影
			Film film = inventoryService.findFilmByInventoryId(inventoryId);

			// 创建租金记录
			Payment payment = new Payment();
			payment.setAmount(film.getRentalRate());
			payment.setCustomerId(customerId);
			payment.setLastUpdate(date);
			payment.setPaymentDate(date);
			payment.setRentalId(r.getRentalId());
			payment.setStaffId(staffId);
			paymentDao.add(payment);
			return true;
		}
		return false;
	}

	@Override
	public boolean returnFilm(int rentalId, Date date) {
		Rental rental = rentalDao.getById(rentalId);
		rental.setReturnDate(date);
		rentalDao.update(rental);

		// 计算归还日期判断是否要多交钱
		// 租金规则，超过一天1块钱，超过是指租借当天日期加上film的默认租期
		Date d_rental = beginOfDate(rental.getRentalDate());

		Date d_return = beginOfDate(date);

		long days = (d_return.getTime() - d_rental.getTime()) / (1000 * 3600 * 24);

		Film film = inventoryService.findFilmByInventoryId(rental.getInventoryId());
		if (days > film.getRentalDuration()) {
			Payment payment = rental.getPayment();
			payment.setLastUpdate(date);
			BigDecimal amount = film.getRentalRate().add(new BigDecimal(days - film.getRentalDuration()));
			payment.setAmount(amount);
			paymentDao.update(payment);
		}
		return true;
	}

	private Date beginOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.HOUR, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return c.getTime();
	}

}
