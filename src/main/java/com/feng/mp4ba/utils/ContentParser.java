package com.feng.mp4ba.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.util.StringUtils;

import com.feng.mp4ba.entity.Film;
import com.feng.mp4ba.model.FilmCategory;

public class ContentParser {

	private Logger log = Logger.getLogger(ContentParser.class);
	
	public final static String PREFIX = "http://www.mp4ba.com/";
	public static String DOWN = "down.php?date=timestamp&hash=hashCode";
	
	private SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	
	public List<Film> parser(FetchedPage result) {
		
		List<Film> filmList = new ArrayList<Film>();
		
		String html = result.getContent();
		Document htmlDoc = Jsoup.parse(html);
		log.info("开始 解析html 内容");
		String tbodyId = "data_list";
		Element tbody = htmlDoc.getElementById(tbodyId);
//		log.info(tbody.html());
		
		Elements trs = tbody.getElementsByTag("tr");
		System.out.println("tr行数："+trs.size());
		FilmCategory category = new FilmCategory();
//		category.setTypeName("港台电影");
		for (int i = 0; i < trs.size(); i++) {
			Element elm = trs.get(i);
		/*}
		for (Element elm : trs) {*/
			if(! "ad_list_middle".equals(elm.attr("id"))){
				
				/*
				 * 
				 * 	<td nowrap="nowrap">04/28 13:36</td>
				  	<td><a href="index.php?sort_id=2">港台电影</a></td>
					<td style="text-align:left;"> <img src="images/icon_sticky.gif" alt="置顶" title="置顶" align="absmiddle" />&nbsp; <a style="color:red;font-weight:bold;" href="show.php?hash=cf22b1ff00c4dce8bc9e0fb309c6d24269010f58" target="_blank"> 踏血寻梅.Port.of.Call.2015.BD1080P.X264.AAC.Cantonese&amp;Mandarin.CHS.Mp4Ba</a> </td>
					<td>5.2GB</td>
					<td nowrap="nowrap"> <span class="bts_1">1264</span> </td>
					<td nowrap="nowrap"> <span class="btl_1">1761</span> </td>
					<td nowrap="nowrap"> <span class="btc_1">123</span> </td>
					<td><a href="index.php?team_id=1" class="text_green">高清MP4吧</a></td>
				 * 
				 * */
				
				Elements tds = elm.getElementsByTag("td");
				System.out.println("tds "+tds);
				Element td_pubTime = tds.get(0);
				Element td_category = tds.get(1);
				Element td_film = tds.get(2);
				Element td_size = tds.get(3);
				Element td_down = tds.get(5);// 下载次数
				
				String str_pubTime = td_pubTime.ownText(); // 发布时间
				String str_categoryName = td_category.text();
				log.info("str_pucTime "+str_pubTime+" str_categoryName "+str_categoryName);
				Element a_film = td_film.getElementsByTag("a").get(0);
				String str_url = a_film.attr("href");
				String str_filmName = a_film.ownText();
				String filmName = str_filmName.substring(0, str_filmName.indexOf(".")) ;
				String definition = PatternUtil.getPatternStr(str_filmName) ; //清晰度
				
				String hashCode = str_url.substring(str_url.indexOf("hash") + "hash=".length());
				String str_filmSize = td_size.ownText();
				String str_downCount =  td_down.text();
				Elements span_down = td_down.getElementsByAttributeValueStarting("class", "btl_");
				String str_downCount_span = span_down.get(0).ownText(); 
				
				
//				Element td_film = elm.getElementsByAttributeValueContaining("style", "text-align").get(0);
//				Element a_film = td_film.getElementsByTag("a").get(0);
				category.setTypeName(str_categoryName);
				Film film = new Film();
//				film.setCategory(category);
				film.setDownloadCount(str_downCount);
				
				film.setFilmName(filmName);
				film.setFilmSize(str_filmSize);
				film.setHashCode(hashCode);
				film.setOnShowDate(str_pubTime);
				film.setPubTime(str_pubTime);
				film.setShowUrl(str_url);
				film.setDefinition(definition); // 清晰度
				
				
				
				FetchedPage film_result = HtmlUtil.getContentFromUrl(PREFIX + str_url);
				String film_html = film_result.getContent();
				Document film_html_doc = Jsoup.parse(film_html);
				String basic_info_class = "basic_info";
				Element div_basic_info =  film_html_doc.getElementsByClass(basic_info_class).get(0);
				Element p_pubTime = div_basic_info.getElementsByTag("p").get(3);
				String str_pubTime_format = p_pubTime.ownText();
				String pubTimeStr = str_pubTime_format.substring("发布时间: ".length()); 
				Date pubTime = null;
				try {
					pubTime = format.parse(pubTimeStr);
				} catch (ParseException e) {
					log.info("解析日期发生异常");
					pubTime = new Date();
					e.printStackTrace();
				}
				long timestamp13 = pubTime.getTime(); // 13位
				long timestamp = timestamp13 / 1000; // 转换为10位
				System.out.println("pubTimeStr"+pubTimeStr); // -- 1461821807  1461821807000
				System.out.println("pubTime日期"+pubTime);
				System.out.println("timestamp "+timestamp);
				film.setPubTime(pubTimeStr); // 发布日期
				
				
				String torrentUrl = DOWN.replace("timestamp", String.valueOf(timestamp)).replace("hashCode", hashCode);
				System.out.println("电影下载url "+torrentUrl);
				film.setTorrentUrl(torrentUrl);;
				
				Elements intros = film_html_doc.getElementsByClass("intro");
				System.out.println("intros size "+intros.size());
				Element film_intro = intros.get(0);
				
				/*
				// 上映日期: 2016-03-23(香港国际电影节)
				TextNode onShowNode = (TextNode) film_intro.childNode(15); // 上映日期: 2016-03-23(香港国际电影节)
				String onShowNodeStr = onShowNode.text();
				String onShowDateStr = onShowNodeStr.substring(7, 17);
				*/
				String intros_allText = film_intro.text();
				String onShowDateStr= intros_allText.substring(intros_allText.indexOf("上映日期")+6, intros_allText.indexOf("上映日期")+6+10);
				
				
				
				
				film.setOnShowDate(onShowDateStr);
				
				Element imgEle = film_intro.getElementsByTag("img").get(0);
				String img_url = imgEle.attr("src");
				film.setImgUrl(img_url);
				
				filmList.add(film);
				/*DBUtil db = new DBUtil();
				db.insertFilm(film);*/
				/*if(i == 5){
					break;
//					System.exit(1);
				}*/
			}
			
		}
		
		return filmList;
	}

	
}
