package demo.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import demo.bean.Customer;
import demo.dao.ICustomerDao;

@Repository("customerDao")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements ICustomerDao {

	@Override
	public List<Customer> findAll(int startRow, int rowSize) {
		Session session = getSessionFactory().getCurrentSession();
		// join fetch直接查询出关联对象，不会采用懒加载
		Query query = session.createQuery("from customer c join fetch c.address");
		query.setFirstResult(startRow);
		query.setMaxResults(rowSize);
		return query.getResultList();
	}

}