package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * 数据库驱动类
 * 作者：蔡琪深
 */
public class DBUtil {
	private static Connection conn =null;
	private static PreparedStatement st = null;
	private static Statement stmt = null;
	private static ResultSet rs = null;
	static {
		try {
			Class.forName(Constant.JDBC_DRIVER);
			conn= DriverManager.getConnection(Constant.URL,Constant.USERNAME,Constant.PASSWORD) ;
		}catch(Exception e){
			 System.out.println(e);
		}
	}
	public static PreparedStatement getPreparedStatement(String sql) {
		try {
			st=conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
	public static void closeDBResource() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
