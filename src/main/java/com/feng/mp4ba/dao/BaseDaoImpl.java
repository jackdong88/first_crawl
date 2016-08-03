package com.feng.mp4ba.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDaoImpl<C> implements BaseDao<C> {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public C findById(Object id, Class<C> cls)
	{
		Criteria cri = this.getSession().createCriteria(cls).add(Restrictions.idEq(id));
		List list = cri.list();
		if(list != null && list.size() == 1){
			return (C) list.get(0);
		}
		return null;
	}

	@Override
	public void save(C obj) 
	{
		this.getSession().save(obj);
	}

	@Override
	public void update(C obj) 
	{
		this.getSession().update(obj);
	}

	@Override
	public void delete(C obj) 
	{
		this.getSession().delete(obj);
	}

}
