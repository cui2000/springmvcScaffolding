package demo.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import demo.bean.Role;
import demo.dao.IRoleDao;

@Repository("roleDao")
public class RoleDaoImpl implements IRoleDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addRole(Role role) {
		sessionFactory.getCurrentSession().save(role);
	}

}
