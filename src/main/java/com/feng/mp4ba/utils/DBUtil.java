package com.feng.mp4ba.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.feng.mp4ba.model.FilmData;


public class DBUtil {
	public static final String url = "jdbc:mysql://127.0.0.1/mp4ba";  
    public static final String name = "com.mysql.jdbc.Driver";  
    public static final String user = "root";  
    public static final String password = "root";  
    public static final String table_name = "mp4ba_url";
//	public staitc final String dburl = "jdbc:mysql://localhost/mydata?user=root&password=root";
    
    
    public Connection conn = null;  
    public PreparedStatement pst = null;  
    public Statement stmt = null;
    
    public DBUtil(){
    	
    }
    public DBUtil(String sql) {  
        try {  
            Class.forName(name);//指定连接类型  
            conn = DriverManager.getConnection(url, user, password);//获取连接  
            pst = conn.prepareStatement(sql);//准备执行语句  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
  
    public void insertFilm(FilmData film){
    	try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);//获取连接  
	    	stmt = conn.createStatement();
	    	String sql = "insert into film (filmName,onShowDate,showUrl,hashCode,torrentUrl,pubTime,filmSize,downloadCount) "
	    			+ " values('"+film.getFilmName()+"','"+film.getOnShownDate()+"','"+film.getShowUrl()+"'"
	    					+ ", '"+film.getHashCode()+"','"+film.getTorrentUrl()+"','"+film.getPubTime()+"'"
	    					+ ", '"+film.getFilmSize()+"','"+film.getDownloadCount()+"' ) ";
	    	int result = stmt.executeUpdate(sql);
	    	System.out.println("保存film数据：返回结果："+result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void updateFilm(FilmData film){
    	try{
    		Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);//获取连接  
	    	stmt = conn.createStatement();
	    	StringBuffer sb = new StringBuffer();
	    	sb.append(" update film f where f.hashCode = '"+film.getHashCode()+"' ");
    	}catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
    public void insert(String filmUrl, String title){
    	try {
			Class.forName(name);
			conn = DriverManager.getConnection(url, user, password);//获取连接  
	    	stmt = conn.createStatement();
//	    	String sql = "insert into "+table_name+" (url,title) values ('"+filmUrl+"', '"+title+"')";
//	    	String sql = "insert into "+table_name+" (url,title) values ('"+filmUrl+"', '"+title+"')";
	    	String sql = "insert into film (filmName,onShowDate,showUrl,hashCode,torrentUrl,pubTime,filmSize,downloadCount) "
	    			+ " values('') ";
//	    	String sql = "insert into table_name";
	    	int result = stmt.executeUpdate(sql);
	    	System.out.println("返回结果："+result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
    
    public void close() {  
        try {  
            this.conn.close();  
            this.pst.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
	
}
