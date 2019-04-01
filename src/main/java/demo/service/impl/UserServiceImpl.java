package demo.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import demo.bean.User;
import demo.dao.IUserDao;
import demo.service.IUserService;

@Service("userService")
public class UserServiceImpl implements IUserService {

	@Resource(name = "userDao")
	private IUserDao userDao;

	@Override
	public void addUser(User user) {
		userDao.addUser(user);
	}

}
