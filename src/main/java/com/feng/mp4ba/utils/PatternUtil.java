package com.feng.mp4ba.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternUtil {
	
	public final static String getPatternStr(String matchingStr){
//		String testStr = "赌圣.Distance.2016.HD720P.X264.AAC.Mandarin.CHS.Mp4Ba";
		String pattern = "[B|H][D]\\d+[P]"; // 匹配HD720P、HD1080P、BD720P等清晰度
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(matchingStr);
		if(m.find()){
			String groupStr = m.group();
			System.out.println("匹配的字符串 : "+groupStr);
			return groupStr;
		}else{
			System.out.println("加了^$ 此字符串不匹配");
			return null;
		}
	}
	
}
