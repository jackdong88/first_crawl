package com.feng.mp4ba.utils;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TestDB {

	static String sql = null;  
    static DBUtil db1 = null;  
    static ResultSet ret = null;  
    
	public static void main(String[] args) {
		sql = "select * from mp4ba_url";//SQL语句  
        db1 = new DBUtil(sql);//创建DBHelper对象  
  
        try {  
            ret = db1.pst.executeQuery();//执行语句，得到结果集  
            while (ret.next()) {  
            	int id = ret.getInt(1);
            	String url = ret.getString("url");
            	System.out.println("id "+id+", url="+url);
                /*String uid = ret.getString(1);  
                String ufname = ret.getString(2);  
                String ulname = ret.getString(3);  
                String udate = ret.getString(4);  
                System.out.println(uid + "\t" + ufname + "\t" + ulname + "\t" + udate );  
                */
            }//显示数据  
            ret.close();  
            db1.close();//关闭连接  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
  
}
