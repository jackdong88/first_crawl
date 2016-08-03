package com.feng.mp4ba.utils;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HtmlUtil {
	
	private static Logger log = Logger.getLogger(HtmlUtil.class);
	
	public static FetchedPage getContentFromUrl(String url){
		
		
		HttpParams httpParams = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(httpParams, 10 * 1000);
	    HttpConnectionParams.setSoTimeout(httpParams, 10 * 1000);	    
		HttpClient client = new DefaultHttpClient(httpParams);
//		HttpClient client = new DefaultHttpClient();
		HttpGet getHttp = new HttpGet(url);
		getHttp.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");
		HttpResponse response = null;
		String htmlContent = null;
		String response_type = null;
		try {
			response = client.execute(getHttp);
			int status_code = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			if(entity != null){
				htmlContent = EntityUtils.toString(entity, "UTF-8");
			}
//			System.out.println("htmlContent : "+htmlContent);
			Header[] headers = response.getHeaders("Content-Type");
			String contentType = headers[0].getValue();
			log.info("Content-Type = "+contentType);
			int start = contentType.lastIndexOf("/") + 1;
			int end = contentType.lastIndexOf(";");
			response_type = contentType.substring(start,end);
			
			FetchedPage fetchedPage = null; 
			
			if(response_type.equals("html")){
				fetchedPage = new FetchedPage(url, htmlContent, status_code, 0);
			}else if(response_type.equals("json")){
				fetchedPage = new FetchedPage(url, htmlContent, status_code, 1);
			}
			
			return fetchedPage;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
}
