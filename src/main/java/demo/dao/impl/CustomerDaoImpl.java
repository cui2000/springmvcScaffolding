package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.Customer;
import demo.dao.ICustomerDao;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements ICustomerDao {

}