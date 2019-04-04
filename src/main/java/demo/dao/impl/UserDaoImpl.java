package demo.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import demo.bean.User;
import demo.dao.IUserDao;

@Transactional
@Repository("userDao")
public class UserDaoImpl extends BaseDaoImpl<User> implements IUserDao {

}
