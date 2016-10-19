package com.feng.mp4ba.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feng.mp4ba.entity.Film;
import com.feng.mp4ba.service.CrawlService;

@Controller
@RequestMapping("crawl")
public class CrawlController {
	
	@Autowired
	private CrawlService crawlService;
	
	Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping("crawlAction")
	public String action(){
		String result = "";
		try{
			log.info(" CrawlController action method");
			crawlService.crawlData();
			result = "success";
		}catch(Exception e){
			result = "error";
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping("view/{filmId}")
	public String view(@PathVariable Long filmId, HttpServletRequest request){
		Film film = this.crawlService.findById(filmId, Film.class);
		log.info( "filmId=" +film.getId()+ " filmName="+film.getFilmName());
		return null;
	}
	
	@RequestMapping("test")
	public String test(){
		return "test";
	}
}
