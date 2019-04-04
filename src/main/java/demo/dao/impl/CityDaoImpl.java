package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.City;
import demo.dao.ICityDao;

@Repository("cityDao")
public class CityDaoImpl extends BaseDaoImpl<City> implements ICityDao {

}