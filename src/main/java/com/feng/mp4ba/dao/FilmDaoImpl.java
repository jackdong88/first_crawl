package com.feng.mp4ba.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.feng.mp4ba.entity.Film;

@Repository
public class FilmDaoImpl extends BaseDaoImpl<Film>  implements FilmDao {

	@SuppressWarnings("unchecked")
	@Override
	public Film findByHashCode(String hashCode) {
		Criteria cri = getSession().createCriteria(Film.class);
		cri.add(Restrictions.eq("hashCode", hashCode));
		List<Film> list = cri.list();
		if(list != null && list.size() == 1){
			return list.get(0);
		}
		return null;
	}

	
}
