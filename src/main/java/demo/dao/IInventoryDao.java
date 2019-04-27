package demo.dao;

import java.util.List;

import demo.bean.Inventory;

public interface IInventoryDao extends IBaseDao<Inventory> {

	public List<Inventory> findInStock();
	
	public boolean inStock(int inventoryId);

}