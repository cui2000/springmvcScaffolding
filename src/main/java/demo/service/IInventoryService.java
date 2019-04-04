package demo.service;

import java.util.List;

import demo.bean.Actor;

public interface IInventoryService {

	public List<Actor> findActor(int startRow, int rowSize);
	
	public int getActorCount();

}
