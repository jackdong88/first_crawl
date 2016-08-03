package com.feng.mp4ba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.feng.mp4ba.dao.BaseDao;
import com.feng.mp4ba.entity.BaseEntity;

@Component
@Transactional(readOnly=true)
public class BaseService<C extends BaseEntity> {

	@Autowired
	@Qualifier("baseDaoImpl")
	private BaseDao<C> baseDao;
	
	
	public C findById(Object id, Class<C> cls)
	{
		return (C) this.baseDao.findById(id, cls);
	}
	
	@Transactional(readOnly=false)
	public void save(C obj)
	{
		this.baseDao.save(obj);
	}
	
	@Transactional(readOnly=false)
	public void update(C obj)
	{
		this.baseDao.update(obj);
	}
	
	@Transactional(readOnly=false)
	public void delete(C obj)
	{
		this.baseDao.delete(obj);
	}
	
	
	
}
