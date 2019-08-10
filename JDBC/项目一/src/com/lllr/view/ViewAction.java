package com.lllr.view;

import java.sql.SQLException;
import java.util.*;

import com.lllr.UserAction.UserAction;
import com.lllr.model.User;

public class ViewAction {

	private static final String CONTEXT="�û�����\n" +
	"[MAIN/M]:���˵�\n" +
	"[ADD/A]:�����û�\n" +
	"[DELETE/D]:ɾ���û�\n" +
	"[UPDATE/U]:�޸��û�\n" +
	"[BREAK/B]:�˳���ǰ���ܣ��������˵�" ;
	
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

			
			//�˳�
			if(OPERATION_EXIT.equals(in)  || OPERATION_EXIT.substring(0, 1).equals(in)){
				System.out.println("���˳��û��������");
				break;
			} 
			
			//�����û�
			else if(OPERATION_ADD.equals(in)  || OPERATION_ADD.substring(0, 1).equals(in) || OPERATION_ADD.equals(previous)) {
				
				previous = OPERATION_ADD;
				
				if(step == 1) {
					System.out.println("�������û���[����]��" );
				}
				else if(step==2) {
					usr.setUsername(in);
					System.out.println("�������û���[�绰]��");
				}
				else if(step==3) {
					usr.setMobile(in);
					System.out.println("�����봴�����û���[�û���]��");
				}
				else if(step==4) {
					usr.setCreate_user(in);
				    try {
						usrAction.addUser(usr);
					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("�����û�ʧ��!");
						step=3;
					}
					System.out.println("�����û��ɹ�!");
					previous = null;
					step=1;
				}
				if(OPERATION_ADD.equals(previous))
							step++;
			}
			
			
			//ɾ���û�
			else if(OPERATION_DELETE.equals(in)  || OPERATION_DELETE.substring(0, 1).equals(in) || OPERATION_DELETE.equals(previous)) {
			        
				previous = OPERATION_DELETE;
				  List<User>  usrList = new ArrayList<User>();
			          try {
						usrList = usrAction.getAllUser();
					} catch (SQLException e) {
						e.printStackTrace();
						System.out.println("��ѯȫ���û�ʧ��!");
					}
			         System.out.println("ȫ���û�:");
			         for(User user : usrList) {
			        	 System.out.println(user.toString());
			         }
			         
			         if(step ==1 )
			         System.out.println("������Ҫɾ�����û���id��Ϣ��");
			         
			         if(step ==2)
			         {
		                   try {
		                	   
		                	   System.out.println(Integer.valueOf(in));
								usrAction.delUser(  (Integer.valueOf(in)));
							
							} catch (NumberFormatException e) {
								e.printStackTrace();
								System.out.println("��������ȷ��id��Ϣ:");
								step=1;
							} catch (SQLException e) {
								e.printStackTrace();
								System.out.println("ɾ���û�ʧ��");
							}
					  }
			         
			         
			         if(step ==3) {
			        	 System.out.println("ɾ���û��ɹ�!");
			        	 previous = null;
			        	 step=1;
			         }
			         step++;
		     }
			
			//�޸��û���Ϣ
			else if(OPERATION_UPDATE.equals(in)  || OPERATION_UPDATE.substring(0, 1).equals(in) || OPERATION_UPDATE.equals(previous)) {
				previous = OPERATION_UPDATE;
				 
			         
			         if(step ==1 ) {
			        	 List<User>  usrList = new ArrayList<User>();
				          try {
							usrList = usrAction.getAllUser();
						} catch (SQLException e) {
							e.printStackTrace();
							System.out.println("��ѯȫ���û�ʧ��!");
						}
				         System.out.println("ȫ���û�:");
				         for(User user : usrList) {
				        	 System.out.println(user.toString());
				         }
			         System.out.println("������Ҫ�޸ĵ��û���id��Ϣ��");
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
							System.out.println("δ�ҵ����û���");
						}
			        	 
			        	 System.out.println("������Ҫ���ĵ��û���:");
			         }
			         
			         if(step == 3) {
			        	 usr.setUsername(in);
			        	  System.out.println("������Ҫ���ĵ��û��绰:");
			        	  usr.setMobile(in);
			        	  try {
							usrAction.updateUser(usr);
						} catch (SQLException e) {
							e.printStackTrace();
							System.out.println("�޸��û�ʧ�ܣ�" );
				        	 }
			         }
			         
			         if(step ==4 ) {
			        	    System.out.println("�޸��û��ɹ���" );
			        		step=1;
			        		previous =null;
			        		System.out.println("�����µĹ�������:" );
		        	  }
			        
			         if(OPERATION_UPDATE.equals(previous)) {     	
			        	 step++;
			         }
		              
			         }

			else {
				System.out.println("�������ֵΪ��" + in);
			}
			}
			
	}
		
	}
	


