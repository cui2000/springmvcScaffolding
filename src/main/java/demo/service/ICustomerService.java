package demo.service;

import java.util.List;

import javax.transaction.Transactional;

import demo.bean.City;
import demo.bean.Customer;

@Transactional
public interface ICustomerService {

	public List<Customer> findCustomer(int startRow, int rowSize);

	public int getCustomerCount();

	public void addCity(City city);

}
