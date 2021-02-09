package com.cqs.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import com.cqs.bean.User;
import com.cqs.service.UserInfo;

import org.eclipse.jdt.internal.compiler.ast.ReturnStatement;

import com.util.*;

/*
 * ���ߣ�������
 * ʵ���û�������Ϣ��dao��
 */
public class UserDao implements UserInfo{
	
	//�����û�
	public User Adduser(String username, String password,String name,String sex,int age,String department,String position,String contact,int permission) 
	{
		User user = new User();
		PreparedStatement st = null;
		ResultSet rs = null;
		boolean flag=false;
		new DBUtil();
		String sql="insert into emp_information VALUE(?,?,?,?,?,?,?,?,?)";
		try {
			st =DBUtil.getPreparedStatement(sql);					
			st.setString(1, username);
			st.setString(2, password);
			st.setString(3, name);
			st.setString(4, sex);
			st.setInt(5, age);
			st.setString(6, department);
			st.setString(7, position);
			st.setString(8, contact);
			st.setInt(9, permission);
			//rs = st.executeQuery();
			flag = st.execute();
			if (flag) {
				System.out.println("���ӳ�Ա�ɹ���");
				System.out.println("������"+user.toString());
			}
			st.close();
			rs.close();
			
		} 
		catch(SQLException e) {
			System.out.println(e+"SQL����");}
		catch (Exception e) {
			System.out.println(e);
			}
		
		return user;
		
	}
	
	//�������
	public List<User> Show() {
		PreparedStatement st = null;
		ResultSet rs = null;
		String username,password,name,sex,department,position,contact;
		int age,permission;
		List<User> list = new ArrayList<User>();
		new DBUtil();
		String sql="SELECT * FROM emp_information";
		try {
			st =DBUtil.getPreparedStatement(sql);
			rs =st.executeQuery();

			while (rs.next()) {
				User user =new User();
				username =rs.getString("username");
				password =rs.getString("password");
				name =rs.getString("name");
				sex =rs.getString("sex");
				department =rs.getString("department");
				position =rs.getString("position");
				contact =rs.getString("contact");
				age = rs.getInt("age");
				permission =rs.getInt("permission");
				user.setUsername(username);
				user.setPassword(password);
				user.setAge(age);
				user.setContact(contact);
				user.setDepartment(department);
				user.setName(name);
				user.setPermission(permission);
				user.setPosition(position);
				user.setSex(sex);
				list.add(user);
			}
			String jsonString = JSON.toJSONString(list);
			System.out.println(jsonString);
			
			st.close();
			rs.close();
		
		}
		catch(SQLException e) {
			System.out.println(e);
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return list;
	}
	
	//ɾ��һ������
	public String Del(String username) {
		PreparedStatement st = null;
		ResultSet rs = null;
		new DBUtil();
		String sql = "delete from emp_information where username = ?";
		try {
			st =DBUtil.getPreparedStatement(sql);	
			st.setString(1, username);
			st.execute();
			st.close();
			rs.close();
			return "�ɹ�";
			
		} 
			catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e);
			}
			catch (Exception e) {
				System.out.println(e);
		}
		return "ʧ��";
	}
	
	//�����û�����ѯ����
	public List<User> Find(String username) {
		User user = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		String password,name,sex,department,position,contact;
		int age,permission;
		List<User> list = new ArrayList<User>();
		new DBUtil();
		String sql = "SELECT * FROM emp_information WHERE username=?";
		try {
			st =DBUtil.getPreparedStatement(sql);
			st.setString(1, username);
			rs =st.executeQuery();
			while(rs.next()) {
				user =new User();
				username =rs.getString("username");
				password =rs.getString("password");
				name =rs.getString("name");
				sex =rs.getString("sex");
				department =rs.getString("department");
				position =rs.getString("position");
				contact =rs.getString("contact");
				age = rs.getInt("age");
				permission =rs.getInt("permission");
				user.setUsername(username);
				user.setPassword(password);
				user.setAge(age);
				user.setContact(contact);
				user.setDepartment(department);
				user.setName(name);
				user.setPermission(permission);
				user.setPosition(position);
				user.setSex(sex);
				list.add(user);
			}
			st.close();
			rs.close();
				
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		  catch (Exception e) {
		    System.out.println(e);
		}
		
		return list;
	}
	
	
	
	//�޸�һ�����ݵ���Ϣ
	public User Modify(String username, String password,String name,String sex,int age,String department,String position,String contact,int permission) {
		PreparedStatement st = null;
		ResultSet rs = null;
		new DBUtil();
		User user = new User();
		String sql = "UPDATE emp_information SET password=?,name=?,sex=?,age=?, department=?,position=?,contact=?,permission=? WHERE username = ?";
		
		try {
			st =DBUtil.getPreparedStatement(sql);					
			st.setString(9, username);
			st.setString(1, password);
			st.setString(2, name);
			st.setString(3, sex);
			st.setInt(4, age);
			st.setString(5, department);
			st.setString(6, position);
			st.setString(7, contact);
			st.setInt(8, permission);
			System.out.println(st.toString());
			int flag = st.executeUpdate();
			if(flag>0)
			{
				System.out.println("�޸ĳɹ�");
			}
			else {
				System.out.println("�޸�ʧ��");
			}
			user.setUsername(username);
			user.setPassword(password);
			user.setAge(age);
			user.setContact(contact);
			user.setDepartment(department);
			user.setName(name);
			user.setPermission(permission);
			user.setPosition(position);
			user.setSex(sex);
			
			st.close();
			rs.close();
			return user;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e);
		}
		  catch (Exception e) {
		    System.out.println(e);
		}
		return user;
		
	}
}
