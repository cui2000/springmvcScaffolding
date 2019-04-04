package demo.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import demo.dao.IBaseDao;

@Repository("baseDao")
public class BaseDaoImpl<T> implements IBaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;

	public void add(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.save(t);
	}

	public void delete(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(t);
	}

	public void deleteById(Serializable id) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(getClassName(), id);
	}

	public void update(T t) {
		Session session = sessionFactory.getCurrentSession();
		session.update(t);
	}

	public List<T> findAll(int startRow, int rowSize) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from " + getClassName());
		query.setFirstResult(startRow);
		query.setMaxResults(rowSize);
		return query.getResultList();
	}

	public T getById(Serializable id) {
		Session session = sessionFactory.getCurrentSession();
		return (T) session.get(getClassName(), id);
	}

	public int getTotalCount() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("select count(*) from " + getClassName());
		return Integer.valueOf(query.getSingleResult().toString());
	}

	public String getClassName() {
		return ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]).getName();
	}

}
