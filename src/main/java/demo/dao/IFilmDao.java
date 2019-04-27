package demo.dao;

import demo.bean.Film;

public interface IFilmDao extends IBaseDao<Film> {
	
	public Film findByInventoryId(int inventoryId);

}
