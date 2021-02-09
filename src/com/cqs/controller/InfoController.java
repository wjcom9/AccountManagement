package com.cqs.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cqs.bean.User;
import com.cqs.service.ServiceInfoImp;
import com.google.gson.Gson;
import com.util.Constant;
import com.util.JsonChange;

/*
 * 作者：蔡琪深
 * 此servlet实现账户管理的功能，包含了增删查改
*/

public class InfoController extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			doPost(request, response);
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		try {

			//常用类
			Constant.infoController(request, response);
			String username = request.getParameter("username");
			String flag = request.getParameter("flag");
			//使用elseif结构选择要执行的功能
			System.out.println(flag);
			if(flag.equals("添加用户"))
			{
				AddUser(request, response);
			}
			else if (flag.equals("删除用户")) {
				DelUser(request, response);
			}
			else if (flag.equals("查找用户")) {
				FinUser(request, response);
			}
			else if (flag.equals("更正信息")) {
				ModifyUser(request, response);
			}
			else if (flag.equals("展现用户信息表")) {
				ShowAll(request, response);
			}
			else {
				PrintWriter out  = response.getWriter();
				out.println("很遗憾，找不到该功能！");
			}
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
			
		}
	}
	//增加用户功能
	private void AddUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			Constant.infoController(request, response);
			
			ServiceInfoImp serviceInfoImp = new ServiceInfoImp();
			String username,password,name,sex,bumen,position,lianxi;
			int age,permission;
			username =request.getParameter("username");
			password =request.getParameter("password");
			name =request.getParameter("name");
			sex =request.getParameter("sex");
			age =Integer.parseInt(request.getParameter("age"));
			bumen = request.getParameter("bumen");
			position =request.getParameter("position");
			lianxi = request.getParameter("lianxi");
			permission = Integer.parseInt(request.getParameter("permission"));
			//读取前台表单的各种数值
			PrintWriter out;
			out = response.getWriter();
			if(serviceInfoImp.Add(username, password, name, sex, age, bumen, position, lianxi, permission))
				out.println("已增加改数据！");
			else {
				 out.println("添加失败！");
			}
		}catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		catch (Exception e) {
			e.printStackTrace();
			System.out.println(e);
		}	
	}
	
	//删除一条用户信息，需要提供其用户名
	private void DelUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			Constant.infoController(request, response);
			JsonChange jc = new JsonChange();
			String username = request.getParameter("username");
			Map map = new HashMap();
			ServiceInfoImp serviceInfoImp = new ServiceInfoImp();
			boolean falg = serviceInfoImp.DelUser(username);
			if(falg==true) {
				map.put("code", "0");
				map.put("msg", "success");
				map.put("username", username);
			}
			else {
				map.put("code", "1");
				map.put("msg", "fail");
				map.put("username", username);
			}
			//转换为前端所需的json格式
			jc.change(response, map);
		}catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}
	}
	
	//查找用户信息，需要前端提供username，返回json格式
	private void FinUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			Constant.infoController(request, response);
			ServiceInfoImp serviceInfoImp = new ServiceInfoImp();
			Map map = new HashMap();
			JsonChange jc = new JsonChange();
			String username = request.getParameter("username");
			
			if(serviceInfoImp.Find(username)!=null)
			{
				map.put("code", "0");
				map.put("msg", "success");
				map.put("count", 50);
				map.put("data",serviceInfoImp.Find(username));
				//map.put("data", username);
			}
			else {
				map.put("code", "1");
				map.put("msg", "fail");
				map.put("count", 1);
				map.put("data",null);
			}
			
			User user =new User();
			Gson gson =new Gson();
			String json =gson.toJson(map);
			//String json=gson.toJson(user);
			//jc.change(response, map);
			//System.out.println(map);
			//String json = JSON.toJSONString(map);
			System.out.println(json);

		}catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}
	}
	
	//修改一条用户信息，需要前端提供username，返回json格式
	private void ModifyUser(HttpServletRequest request, HttpServletResponse response) {
		try {
			Constant.infoController(request, response);
			ServiceInfoImp serviceInfoImp = new ServiceInfoImp();

			Map map = new HashMap();
			JsonChange jc = new JsonChange();
			String username,password,name,sex,department,position,contact;
			int age,permission;
			username =request.getParameter("username");
			
			password =request.getParameter("password");
			
			name =request.getParameter("name");
			sex =request.getParameter("sex");
			age =Integer.parseInt(request.getParameter("age"));
			department = request.getParameter("department");
			position =request.getParameter("position");
			contact = request.getParameter("contact");
			permission = Integer.parseInt(request.getParameter("permission"));
			System.out.println("传递来的是"+password+"name: "+name+"sex: "+sex+"age: "+age+"position: "+position+"department: "+department+"contact:"+contact+"permission "+permission);
			boolean falgg =serviceInfoImp.modifyUser(username, password, name, sex, age, department, position, contact, permission);
			if(falgg)
			{
				map.put("code", "0");
				map.put("msg", "success");
				map.put("username", username);
			}
			else {
				map.put("code", "1");
				map.put("msg", "fail");
				map.put("username", username);
			}
			jc.change(response, map);
			
		}	catch (Exception e) {
			
			System.out.println(e);
		}
	}
	
	//展现当前用户数据库的所以数据，返回json格式
	private void ShowAll(HttpServletRequest request, HttpServletResponse response) {
		try {
			Constant.infoController(request, response);
			ServiceInfoImp serviceInfoImp  = new ServiceInfoImp();
			Map map = new HashMap();
			JsonChange jc = new JsonChange();
			map.put("code", "0");
			map.put("msg", "");
			map.put("count", 50);
			map.put("data",serviceInfoImp.showAll());
			jc.change(response, map);
		}catch (Exception e) {
				e.printStackTrace();
				System.out.println(e);
			}
	}
	
	
}
