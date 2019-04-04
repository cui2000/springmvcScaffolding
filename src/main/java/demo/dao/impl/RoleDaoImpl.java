package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.Role;
import demo.dao.IRoleDao;

@Repository("roleDao")
public class RoleDaoImpl extends BaseDaoImpl<Role> implements IRoleDao {

}
