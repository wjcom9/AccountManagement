package com.cqs.service;

import java.util.List;

import com.cqs.bean.User;
import com.cqs.dao.UserDao;

public class ServiceInfoImp implements UserInfo {
	//ɾ��һ���û���Ϣ
	public boolean DelUser(String username) {
		UserDao Userdao =  new UserDao();
		if(Userdao.Del(username)!=null)
			return true;
		else 
			return false;
	}
	
	//����һ���û���Ϣ
	public boolean Add(String username, String password,String name,String sex,int age,String department,String position,String contact,int permission) {
		UserDao Userdao =  new UserDao();
		Userdao.Adduser(username, password, name, sex, age, department, position, contact, permission);
		return true;
	}
	
	//չ���û��������
	public List<User> showAll() {
		UserDao Userdao =  new UserDao();
		return Userdao.Show();
	}
	
	//�����û�����������
	public List<User> Find(String username) {
		UserDao Userdao =  new UserDao();
			return Userdao.Find(username);
	
	}
	
	//�޸��û�����
	public boolean modifyUser(String username, String password,String name,String sex,int age,String department,String position,String contact,int permission) {
		UserDao Userdao =  new UserDao();
		if(Userdao.Modify(username, password, name, sex, age, department, position, contact, permission) != null)
			return true;
		else {
			return false;
		}			
		
	}
}
