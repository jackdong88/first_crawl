package com.feng.mp4ba.dao;

public interface BaseDao<C> {

	public C findById(Object id, Class<C> cls);
	
	public void save(C obj);
	
	public void update(C obj);
	
	public void delete(C obj);
	
}
