package com.util;

/*
 * json��ʽת����
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
				System.out.println("json转换错误!");
				e.printStackTrace();
			}
			
		}
	
}
