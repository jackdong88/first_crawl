package com.feng.mp4ba.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feng.mp4ba.entity.ReceiveXmlEntity;
import com.feng.mp4ba.entity.ReceiveXmlProcess;

@RequestMapping("wechat")
@Controller
public class WeChatController {

	Logger log = Logger.getLogger(this.getClass());
	
	@RequestMapping(value="authentication",method=RequestMethod.POST, produces = {"text/xml;charset=UTF-8"})
	@ResponseBody
	public String test(HttpServletRequest request, HttpServletResponse response){
		String returnXml = "";
		String content = "no support";
		try{
			
			request.setCharacterEncoding("UTF-8"); 
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml;charset=UTF-8");
			log.info(" receive msg");
			
			Map<String, String> map = request.getParameterMap();
			StringBuffer sb = new StringBuffer();  
			InputStream is = request.getInputStream();  
	        InputStreamReader isr = new InputStreamReader(is, "UTF-8");  
	        BufferedReader br = new BufferedReader(isr);  
	        String s = "";  
	        while ((s = br.readLine()) != null) {  
	            sb.append(s);  
	        }  
	        String xml = sb.toString(); //次即为接收到微信端发送过来的xml数据  
	        log.info(" xml "+xml);
	        
	        
	        ReceiveXmlEntity xmlEntity = new ReceiveXmlProcess().getMsgEntity(xml);  
	        String receive_str = xmlEntity.getContent();
	        log.info(new Date()+" 用户发送的消息为:"+receive_str);
	        
	        String to  = "oQZ9ktxQFbzcWxODWZjx8VTtoouI";
	        String from = "gh_f66b409a81ca";
	        if(receive_str != null){
	        	
	        	receive_str = receive_str.trim();
	        	if("5152".equals(receive_str)){
	        		content = "jd 号码";
	        	}else if("2224".equals(receive_str)){
	        		content = "jc haoma：15013442224";
	        	}else if("4820".equals(receive_str)){
	        		content = "jc haoma：13580024820";
	        	}else if("2593".equals(receive_str)){
	        		content = "jc haoma：13711472593";
	        	}
	        }
	       
//	        content = URLEncoder.encode(content,"UTF-8");
	        
	        returnXml = formatXmlAnswer(to, from, content);
	        log.info("returnXml "+returnXml);
		}catch(Exception e){
			e.printStackTrace();
		}
		return returnXml;
	}
	
	
	public String formatXmlAnswer(String to, String from, String content) {  
        StringBuffer sb = new StringBuffer();  
        Date date = new Date();  
        sb.append("<xml><ToUserName><![CDATA[");  
        sb.append(to);  
        sb.append("]]></ToUserName><FromUserName><![CDATA[");  
        sb.append(from);  
        sb.append("]]></FromUserName><CreateTime>");  
        sb.append(date.getTime());  
        sb.append("</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[");  
        sb.append(content);  
        sb.append("]]></Content><FuncFlag>0</FuncFlag></xml>");  
        return sb.toString();  
    }  
	
	
	/**
	 * 微信认证
	 * @param request
	 * @return
	 */
	@RequestMapping(value="authentication_weixin")
	@ResponseBody
	private String authentication(HttpServletRequest request){
		log.info(" "+request.getRemoteHost());
		log.info(" test msg");
		
		// 验证信息
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		
		log.info("signature :"+signature);
		log.info("timestamp :"+timestamp);
		log.info("nonce :"+nonce);
		log.info("echostr :"+echostr);
		
		String token = signature;
		String returnStr = parse(token, timestamp, nonce);
		log.info("比对结果:"+returnStr);
		return returnStr;
	}
	
	
	private String parse(String token,String timestamp, String nonce){
		 
		String[] array = new String[] { token, timestamp, nonce };
         StringBuffer sb = new StringBuffer();
         // 字符串排序
         Arrays.sort(array);
         for (int i = 0; i < 3; i++) {
             sb.append(array[i]);
         }
         String str = sb.toString();
         // SHA1签名生成
         MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         md.update(str.getBytes());
         byte[] digest = md.digest();

         StringBuffer hexstr = new StringBuffer();
         String shaHex = "";
         for (int i = 0; i < digest.length; i++) {
             shaHex = Integer.toHexString(digest[i] & 0xFF);
             if (shaHex.length() < 2) {
                 hexstr.append(0);
             }
             hexstr.append(shaHex);
         }
         return hexstr.toString();
	}
	
}
