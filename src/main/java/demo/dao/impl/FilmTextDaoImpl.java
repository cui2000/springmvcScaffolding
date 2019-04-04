package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.FilmText;
import demo.dao.IFilmTextDao;

@Repository("filmTextDao")
public class FilmTextDaoImpl extends BaseDaoImpl<FilmText> implements IFilmTextDao {

}