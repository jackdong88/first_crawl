package com.feng.mp4ba.utils;

import java.util.HashMap;
import java.util.Map;

public class App {

	public final static String WI = "wi";
	public final static String DB = "mp4ba";
	
	public final static String BASE = "http://www.mp4ba.com/";
	
	public final static String NGROK_WEB_URL_FIR = "http://putizi.ngrok.cc/crawl/test";
	public final static String NGROK_WEB_URL_SEC = "http://jeaton.test.ngrok.cc/crawl/test";

	
	public final static Map<String, String> FIR_MAP = new HashMap<String, String>();
	public final static Map<String, String> SEC_MAP = new HashMap<String, String>();

	static {
		
		FIR_MAP.put("fir", "http://cao.sb/BRT");
		FIR_MAP.put("fir", "http://cao.sb/HUU");
		FIR_MAP.put("fir", "https://302.at/960zw");
		FIR_MAP.put("fir", "https://302.at/4n720");
		
		SEC_MAP.put("sec", "http://cao.sb/LDT");
		SEC_MAP.put("sec", "https://302.at/i217i");
		
	}
	
}
