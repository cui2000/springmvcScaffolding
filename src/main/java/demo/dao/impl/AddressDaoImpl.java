package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.Address;
import demo.dao.IAddressDao;

@Repository("addressDao")
public class AddressDaoImpl extends BaseDaoImpl<Address> implements IAddressDao {

}