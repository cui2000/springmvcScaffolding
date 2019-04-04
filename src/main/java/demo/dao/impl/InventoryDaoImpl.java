package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.Inventory;
import demo.dao.IInventoryDao;

@Repository("inventoryDao")
public class InventoryDaoImpl extends BaseDaoImpl<Inventory> implements IInventoryDao {

}