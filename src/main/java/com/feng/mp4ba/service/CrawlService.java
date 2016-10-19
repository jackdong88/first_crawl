package com.feng.mp4ba.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.feng.mp4ba.dao.FilmDao;
import com.feng.mp4ba.entity.Film;
import com.feng.mp4ba.utils.ContentParser;
import com.feng.mp4ba.utils.FetchedPage;
import com.feng.mp4ba.utils.HtmlUtil;

@Component
@Transactional(readOnly=true)
public class CrawlService extends BaseService<Film> {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private FilmDao filmDao;

	@Transactional(readOnly=false)
	public void crawlData(){
		String url = "http://www.mp4ba.com/index.php?sort_id=2&page=2";
		String url_first = "http://www.mp4ba.com/index.php?sort_id=2";
		
		HtmlUtil starter = new HtmlUtil();
		FetchedPage result = starter.getContentFromUrl(url);
		
		if(result.getType() == 0){
			// 解析页面，获取目标数据
			ContentParser parser = new ContentParser();
			List<Film> targetData = parser.parser(result);
			for (Film film : targetData) {
//				film.setCat(cat);
				log.info(" 保存 film 实体");
				Film isExist = this.findByHashCode(film.getHashCode());
				if(isExist == null){
					filmDao.save(film);
				}else{
					log.info(" 数据库中已有记录 hashcode "+film.getHashCode());
//					if("f50178806e52d769e68e84a5944501e28606c2c5".equals(film.getHashCode())){
						log.info("更新ID为："+isExist.getId() + " 的 onshowdate字段 ");
//						isExist.setOnShowDate(film.getOnShowDate());
						isExist.setImgUrl(film.getImgUrl());
						this.update(isExist);
//					}
				}
			}
			log.info(" 爬虫完毕");
		}
		
	}
	
	
	public Film findByHashCode(String hashCode){
		return this.filmDao.findByHashCode(hashCode);
	}
	
	
}
