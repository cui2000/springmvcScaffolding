package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.Store;
import demo.dao.IStoreDao;

@Repository("storeDao")
public class StoreDaoImpl extends BaseDaoImpl<Store> implements IStoreDao {

}