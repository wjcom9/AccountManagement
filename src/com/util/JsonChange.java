package com.util;

/*
 * json格式转化类
 */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
public class JsonChange {
	public void change(HttpServletResponse response , Object obj) {
			try {
				PrintWriter out;
				response.setContentType("application/json;charset=UTF-8");
				out = response.getWriter();
				String json = JSON.toJSONString(obj);
				out.write(json);
			} catch (IOException e) {
				System.out.println("json杞崲閿欒!");
				e.printStackTrace();
			}
			
		}
	
}
