package demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bean.Actor;
import demo.dao.IActorDao;
import demo.dao.IFilmDao;
import demo.service.IInventoryService;

@Service("inventoryService")
public class InventoryServiceImpl implements IInventoryService {

	// @Autowired
	// private IBaseDao<Film> filmDao;
	@Autowired
	private IFilmDao filmDao;
	@Autowired
	private IActorDao actorDao;

	@Override
	public List<Actor> findActor(int startRow, int rowSize) {
		return actorDao.findAll(startRow, rowSize);
	}

	@Override
	public int getActorCount() {
		return actorDao.getTotalCount();
	}

}
