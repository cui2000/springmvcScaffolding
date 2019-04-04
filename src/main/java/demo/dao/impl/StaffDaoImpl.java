package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.Staff;
import demo.dao.IStaffDao;

@Repository("staffDao")
public class StaffDaoImpl extends BaseDaoImpl<Staff> implements IStaffDao {

}