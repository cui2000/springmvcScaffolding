package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.Film;
import demo.dao.IFilmDao;

@Repository("filmDao")
public class FilmDaoImpl extends BaseDaoImpl<Film> implements IFilmDao {

}