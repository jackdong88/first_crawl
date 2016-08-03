package com.feng.mp4ba.dao;

import com.feng.mp4ba.entity.Film;

public interface FilmDao extends BaseDao<Film> {

	
	public Film findByHashCode(String hashCode);
		
}
