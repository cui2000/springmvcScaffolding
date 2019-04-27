package demo.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import demo.bean.Inventory;
import demo.dao.IInventoryDao;

@Repository("inventoryDao")
public class InventoryDaoImpl extends BaseDaoImpl<Inventory> implements IInventoryDao {

	@Override
	public List<Inventory> findInStock() {
		String hql = "select i from inventory i where not exists (select 1 from rental r where i.inventoryId = r.inventoryId and r.returnDate is null)";

		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createQuery(hql);
		return query.getResultList();
	}

	@Override
	public boolean inStock(int inventoryId) {
		if (getById(inventoryId) == null) {
			return false;
		}
		String sql = "select inventory_in_stock(?1)";
		Session session = getSessionFactory().getCurrentSession();
		Query query = session.createNativeQuery(sql);
		query.setParameter(1, inventoryId);
		return (Boolean) query.getSingleResult();
	}

}