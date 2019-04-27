package demo.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.bean.Actor;
import demo.bean.Film;
import demo.bean.Inventory;
import demo.dao.IActorDao;
import demo.dao.IFilmDao;
import demo.dao.IInventoryDao;
import demo.service.IInventoryService;

@Service("inventoryService")
public class InventoryServiceImpl implements IInventoryService {

	// @Autowired
	// private IBaseDao<Film> filmDao;
	@Resource(name = "filmDao")
	private IFilmDao filmDao;
	@Autowired
	private IActorDao actorDao;
	@Autowired
	private IInventoryDao inventoryDao;

	@Override
	public List<Actor> findActor(int startRow, int rowSize) {
		return actorDao.findAll(startRow, rowSize);
	}

	@Override
	public int getActorCount() {
		return actorDao.getTotalCount();
	}

	@Override
	public List<Film> findFilm(int startRow, int rowSize) {
		return filmDao.findAll(startRow, rowSize);
	}

	@Override
	public int getFilmCount() {
		return filmDao.getTotalCount();
	}
	
	@Override
	public Film findFilmByInventoryId(int inventoryId) {
		return filmDao.findByInventoryId(inventoryId);
	}
	
	@Override
	public List<Inventory> findInventoryInStock() {
		return inventoryDao.findInStock();
	}
	
	@Override
	public boolean inventoryInStock(int inventoryId) {
		return inventoryDao.inStock(inventoryId);
	}

}
