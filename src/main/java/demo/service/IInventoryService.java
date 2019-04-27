package demo.service;

import java.util.List;

import javax.transaction.Transactional;

import demo.bean.Actor;
import demo.bean.Film;
import demo.bean.Inventory;

@Transactional
public interface IInventoryService {

	public List<Actor> findActor(int startRow, int rowSize);

	public int getActorCount();

	public List<Film> findFilm(int startRow, int rowSize);

	public int getFilmCount();
	
	public Film findFilmByInventoryId(int inventoryId);
	
	public List<Inventory> findInventoryInStock();
	
	public boolean inventoryInStock(int inventoryId);

}
