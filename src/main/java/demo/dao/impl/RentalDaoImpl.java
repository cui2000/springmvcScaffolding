package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.Rental;
import demo.dao.IRentalDao;

@Repository("rentalDao")
public class RentalDaoImpl extends BaseDaoImpl<Rental> implements IRentalDao {

}