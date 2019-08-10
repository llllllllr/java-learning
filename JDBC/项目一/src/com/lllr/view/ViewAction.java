package com.lllr.view;

import java.sql.SQLException;
import java.util.*;

import com.lllr.UserAction.UserAction;
import com.lllr.model.User;

public class ViewAction {

	private static final String CONTEXT="用户管理：\n" +
	"[MAIN/M]:主菜单\n" +
	"[ADD/A]:新增用户\n" +
	"[DELETE/D]:删除用户\n" +
	"[UPDATE/U]:修改用户\n" +
	"[BREAK/B]:退出当前功能，返回主菜单" ;
	
	private static final String OPERATION_EXIT = "EXIT";
	private static final String OPERATION_ADD = "ADD";
	private static final String OPERATION_DELETE = "DELETE";
	private static final String OPERATION_UPDATE = "UPDATE";
	public static void main(String[] args)  {
		
		System.out.println(CONTEXT);
		
		Scanner scan = new Scanner(System.in);
		String previous = null;
		Integer step =1;
		User usr  = new User();
		UserAction usrAction = new UserAction();
		while(scan.hasNext()) {
			
			//System.out.println("jinlaile");
			String in = scan.next().toString().toUpperCase();

			
			//退出
			if(OPERATION_EXIT.equals(in)  || OPERATION_EXIT.substring(0, 1).equals(in)){
				System.out.println("已退出用户管理界面");
				break;
			} 
			
			//新增用户
			else if(OPERATION_ADD.equals(in)  || OPERATION_ADD.substring(0, 1).equals(in) || OPERATION_ADD.equals(previous)) {
				
				previous = OPERATION_ADD;
				
				if(step == 1) {
					System.out.println("请输入用户的[姓名]：" );
				}
				else if(step==2) {
					usr.setUsername(in);
					System.out.println("请输入用户的[电话]：");
				}
				else if(step==3) {
					usr.setMobile(in);
					System.out.println("请输入创建此用户的[用户名]：");
				}
				else if(step==4) {
					usr.setCreate_user(in);
				    try {
						usrAction.addUser(usr);
					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("新增用户失败!");
						step=3;
					}
					System.out.println("新增用户成功!");
					previous = null;
					step=1;
				}
				if(OPERATION_ADD.equals(previous))
							step++;
			}
			
			
			//删除用户
			else if(OPERATION_DELETE.equals(in)  || OPERATION_DELETE.substring(0, 1).equals(in) || OPERATION_DELETE.equals(previous)) {
			        
				previous = OPERATION_DELETE;
				  List<User>  usrList = new ArrayList<User>();
			          try {
						usrList = usrAction.getAllUser();
					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("查询全部用户失败!");
					}
			         System.out.println("全部用户:");
			         for(User user : usrList) {
			        	 System.out.println(user.toString());
			         }
			         
			         if(step ==1 )
			         System.out.println("请输入要删除的用户的id信息：");
			         
			         if(step ==2)
			         {
		                   try {
		                	   
		                	   System.out.println(Integer.valueOf(in));
								usrAction.delUser(  (Integer.valueOf(in)));
							
							} catch (NumberFormatException e) {
								e.printStackTrace();
								System.out.println("请输入正确的id信息:");
								step=1;
							} catch (SQLException e) {
								e.printStackTrace();
								System.out.println("删除用户失败");
							}
					  }
			         
			         
			         if(step ==3) {
			        	 System.out.println("删除用户成功!");
			        	 previous = null;
			        	 step=1;
			         }
			         step++;
		     }
			
			//修改用户信息
			else if(OPERATION_UPDATE.equals(in)  || OPERATION_UPDATE.substring(0, 1).equals(in) || OPERATION_UPDATE.equals(previous)) {
				previous = OPERATION_UPDATE;
				 
			         
			         if(step ==1 ) {
			        	 List<User>  usrList = new ArrayList<User>();
				          try {
							usrList = usrAction.getAllUser();
						} catch (SQLException e) {
							e.printStackTrace();
							System.out.println("查询全部用户失败!");
						}
				         System.out.println("全部用户:");
				         for(User user : usrList) {
				        	 System.out.println(user.toString());
				         }
			         System.out.println("请输入要修改的用户的id信息：");
			         }
			         
			         if(step==2) {
			        	 List<Map<String ,Object>> params = new ArrayList<Map<String ,Object>>();
			        	 Map<String ,Object> param = new HashMap<String,Object>();
			        	 param.put("name", "id");
			        	 param.put("rela","=");
			        	 param.put("value",Integer.valueOf(in));
			        	 params.add(param);
			        	
			        	 try {
							usr = usrAction.queryUser(params);
						} catch (SQLException e) {
							e.printStackTrace();
							System.out.println("未找到该用户！");
						}
			        	 
			        	 System.out.println("请输入要更改的用户名:");
			         }
			         
			         if(step == 3) {
			        	 usr.setUsername(in);
			        	  System.out.println("请输入要更改的用户电话:");
			        	  usr.setMobile(in);
			        	  try {
							usrAction.updateUser(usr);
						} catch (SQLException e) {
							e.printStackTrace();
							System.out.println("修改用户失败！" );
				        	 }
			         }
			         
			         if(step ==4 ) {
			        	    System.out.println("修改用户成功！" );
			        		step=1;
			        		previous =null;
			        		System.out.println("输入新的管理请求:" );
		        	  }
			        
			         if(OPERATION_UPDATE.equals(previous)) {     	
			        	 step++;
			         }
		              
			         }

			else {
				System.out.println("您输入的值为：" + in);
			}
			}
			
	}
		
	}
	


