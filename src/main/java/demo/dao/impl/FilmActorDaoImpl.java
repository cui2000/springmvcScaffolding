package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.FilmActor;
import demo.dao.IFilmActorDao;

@Repository("filmActorDao")
public class FilmActorDaoImpl extends BaseDaoImpl<FilmActor> implements IFilmActorDao {

}