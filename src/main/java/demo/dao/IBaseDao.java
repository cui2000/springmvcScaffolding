package demo.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDao<T> {

	public void add(T t);

	public void delete(T t);

	public void deleteById(Serializable id);

	public void update(T t);

	public List<T> findAll(int startRow, int rowSize);

	public T getById(Serializable id);
	
	public int getTotalCount();

}
