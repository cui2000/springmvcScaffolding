package demo.dao.impl;

import org.springframework.stereotype.Repository;

import demo.bean.Actor;
import demo.dao.IActorDao;

@Repository("actorDao")
public class ActorDaoImpl extends BaseDaoImpl<Actor> implements IActorDao {

}