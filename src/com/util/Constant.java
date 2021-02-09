package com.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Constant {
	public static final String JDBC_DRIVER ="com.mysql.jdbc.Driver";
	public static final String URL ="jdbc:mysql://localhost:3306/db_user3?useSSl=false&useUnicode=true&characterEncoding=utf8";
	public static final String USERNAME ="root";
	public static final String PASSWORD ="456123";
	
	static public void infoController(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out;
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
