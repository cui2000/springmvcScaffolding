package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.Country;
import demo.dao.ICountryDao;

@Repository("countryDao")
public class CountryDaoImpl extends BaseDaoImpl<Country> implements ICountryDao {

}