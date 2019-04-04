package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.FilmCategory;
import demo.dao.IFilmCategoryDao;

@Repository("filmCategoryDao")
public class FilmCategoryDaoImpl extends BaseDaoImpl<FilmCategory> implements IFilmCategoryDao {

}