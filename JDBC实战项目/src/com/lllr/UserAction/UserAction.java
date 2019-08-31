package com.lllr.UserAction;

import java.sql.SQLException;

import com.lllr.UserDao.UserDao;
import com.lllr.model.User;
import java.util.*;
public class UserAction {

	        //增
	         public void addUser(User usr) throws SQLException {
	        	 UserDao usrDao = new UserDao();
	        	 usrDao.addUser(usr);
	         }
	         
	         //删
	         public void delUser(Integer id) throws SQLException {
	        	 UserDao usrDao = new UserDao();
	        	 usrDao.delUser(id);
	         }
	         
	         //改
	         public void updateUser(User usr) throws SQLException {
	        	 UserDao usrDao = new UserDao();
	        	 usrDao.updateUser(usr);
	         }
	         
	         
	         //查1
	         public List<User> getAllUser() throws SQLException {
	        	 UserDao usrDao = new UserDao();
	        	 return usrDao.getAllUser();
	         }
	         
	         
	         //查2
	         public User queryUser(List<Map<String,Object>> params) throws SQLException {
	        	 UserDao usrDao = new UserDao();
	        	 return usrDao.queryUser(params);
	         }
	         
	         
	public static void main(String[] args) throws SQLException {
		      //UserDao usrDao = new UserDao();
		      
		      
		      /*delete test
		       usrDao.delUser(2);
		       */
		      
		     
		      /*add test
	           User usr = new User();
	           usr.setUsername("小fang");
	           usr.setMobile("122876543218");
	           usr.setCreate_user("admin");
	           usrDao.addUser(usr);*/
		      
		      
		      /* update test
		       User usr = new User();
	           usr.setUsername("小霞");//这里小霞不用再加单引号
	           usr.setMobile("122876543218");
	           usr.setCreate_user("admin");
	           usr.setId(3);
	           usrDao.updateUser(usr);*/
	           
		      
		         /*getAllUser test
			      List<User> usrList = usrDao.getAllUser() ;
			      for(User usr : usrList)
			      {
			    	  System.out.println(usr.getId() + " " + usr.getUsername());
		           }*/
		      
		     
		      /*query test
		      List<Map<String,Object>> params = new ArrayList<Map<String,Object>>();
		      Map<String,Object> param = new HashMap<String,Object>();
		      param.put("name", "username");
		      param.put("rela","=");
		      param.put("value", " '小霞 ' " );
		      params.add(param);
		      User usr = usrDao.queryUser(params);
		      System.out.println(usr.getId() + " " + usr.getUsername());
		      */
		      
		      
	}
}
