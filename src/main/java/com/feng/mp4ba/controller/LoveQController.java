package com.feng.mp4ba.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.feng.mp4ba.service.CrawlService;
import com.feng.mp4ba.service.LoveQContentService;

@Controller
@RequestMapping("loveq")
public class LoveQController {

	@Autowired
	private LoveQContentService service;
	
	Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping("crawlAction")
	public String action(){
		String result = "";
		try{
			log.info(" LoveqController action method");
			service.crawlData();
			result = "success";
		}catch(Exception e){
			result = "error";
			e.printStackTrace();
		}
		return result;
	}
	
}
