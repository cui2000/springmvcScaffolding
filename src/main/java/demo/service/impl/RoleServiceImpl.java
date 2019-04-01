package demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import demo.bean.Role;
import demo.dao.IRoleDao;
import demo.service.IRoleService;

@Service("roleService")
public class RoleServiceImpl implements IRoleService {

	@Resource(name = "roleDao")
	private IRoleDao roleDao;

	@Override
	public void addRole(Role role) {
		roleDao.addRole(role);
	}

}
