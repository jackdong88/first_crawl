package com.feng.mp4ba.utils;

import org.apache.log4j.Logger;

public class Starter {

	private static final Logger log = Logger.getLogger(Starter.class);
	
	public static void main(String[] args) {
		
//		String url = "https://movie.douban.com/j/search_subjects?type=movie&tag=%E7%83%AD%E9%97%A8&sort=recommend&page_limit=20&";
		String url = "http://www.mp4ba.com/index.php?sort_id=2";
		
		// 3,抓取URL指定的页面，并返回状态码和页面内容构成的FetchedPage对象
//		PageFetcher fetcher = new PageFetcher();
//		FetchedPage fetchedPage = fetcher.getContentFromUrl(url);
		HtmlUtil starter = new HtmlUtil();
		FetchedPage result = starter.getContentFromUrl(url);
		
		if(result.getType() == 0){
			// 解析页面，获取目标数据
			ContentParser parser = new ContentParser();
			Object targetData = parser.parser(result);
		}
		
		
		
	}
	
	
}
