package com.feng.mp4ba.service;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.feng.mp4ba.entity.Film;
import com.feng.mp4ba.entity.Program;
import com.feng.mp4ba.model.FilmCategory;
import com.feng.mp4ba.utils.DateUtil;
import com.feng.mp4ba.utils.FetchedPage;
import com.feng.mp4ba.utils.HtmlUtil;
import com.feng.mp4ba.utils.LoveQApp;
import com.feng.mp4ba.utils.PatternUtil;

public class LoveQContentParser {

	private Logger log = Logger.getLogger(LoveQContentParser.class);
	
	public final static String PREFIX = "http://www.loveq.cn/";
	public static String DOWN = "down.php?date=timestamp&hash=hashCode";
	public final static String programShowUrl = "http://www.loveq.cn/program_download-2556.html";
	public final static String online_play_url = "http://dl.loveq.cn/live/program/LoveQ.cn_2016-10-16-1.mp3";
	public final static String online_play_base = "http://dl.loveq.cn/live/program/LoveQ.cn_@date-1.mp3";
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public List<Program> parser(FetchedPage result) {
		
		List<Program> programList = new ArrayList<Program>();
		
		String html = result.getContent();
		Document htmlDoc = Jsoup.parse(html);
		log.info("开始 解析  loveq官网 html 内容");
		String className = "d1";
		Element _div_start = htmlDoc.getElementsByClass(className).get(0);
		Elements dls_ = _div_start.getElementsByTag("dl");
		for (int i = 0; i < dls_.size(); i++) {
			if( i == 0){
				log.info(" 第一个不是正确的数据 ");
				continue;
			}
			log.info(" ----- parse 第 "+i+" 个 start ------------ ");
			
			Program pr = new Program();
			
			Element _dl = dls_.get(i);
			Element _dt = _dl.getElementsByTag("dt").get(0);
			Element _a  = _dt.getElementsByTag("a").get(0);
			String ownText = _a.ownText();
			log.info(" ownText "+ownText);
			
			String prefix = "【MP3格式】";
			Element _dd = _dl.getElementsByTag("dd").get(0);
			Element _span  = _dd.getElementsByTag("span").get(0);
			String ownText_span = _span.ownText();
			String htmltext = _span.html();
			String text = _span.text();
			
			
			String description = ownText_span.substring(prefix.length(), ownText_span.length());
			log.info(" ownText_span "+ownText_span);
			log.info(" ownText_span 截取后 "+description);
			
			Date date = DateUtil.parseSimpleDT("yyyy.MM.dd", ownText);
			String dateStr = DateUtil.getDate(date);
			pr.setDate(date);
			pr.setDateStr(dateStr);
			pr.setDownloadUrl( LoveQApp.DOWNLOAD_URL + "LoveQ.cn_"+ dateStr +"-1.mp3");
			pr.setDescription(description);
			pr.setRecordTime(new Date());
			
			programList.add(pr);
			
			/*
			if( i == 1){
				break;
			}
			*/
			log.info(" ----- parse 第 "+i+" 个 end ------------ ");
		}
		
		
		
			
		
		return programList;
	}

	
}
