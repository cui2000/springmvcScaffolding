package demo.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import demo.bean.User;
import demo.dao.IUserDao;

@Transactional
@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	@Transactional
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
//		Transaction t = session.beginTransaction();
		session.save(user);
//		session.flush();
//		t.commit();
	}

}
