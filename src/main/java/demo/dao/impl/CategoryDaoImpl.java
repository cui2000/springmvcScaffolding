package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.Category;
import demo.dao.ICategoryDao;

@Repository("categoryDao")
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements ICategoryDao {

}