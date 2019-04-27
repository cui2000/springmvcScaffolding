package demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bean.City;
import demo.bean.Customer;
import demo.dao.ICityDao;
import demo.dao.ICustomerDao;
import demo.service.ICustomerService;

@Service("customerService")
public class CustomerService implements ICustomerService {

	@Autowired
	private ICustomerDao customerDao;
	@Autowired
	private ICityDao cityDao;

	@Override
	public List<Customer> findCustomer(int startRow, int rowSize) {
		return customerDao.findAll(startRow, rowSize);
	}

	@Override
	public int getCustomerCount() {
		return customerDao.getTotalCount();
	}
	
	@Override
	public void addCity(City city) {
		cityDao.add(city);
	}

}
