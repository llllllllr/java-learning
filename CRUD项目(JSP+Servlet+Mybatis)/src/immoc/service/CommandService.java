package immoc.service;

import java.util.ArrayList;
import java.util.List;

import immoc.bean.Command;
import immoc.dao.CommandDao;

public class CommandService {

	       //�޸�
	      public void updateCommand(Command command) {
	    	  CommandDao commandDao = new CommandDao();
	    	  commandDao.updateCommand(command);
	      }
	        //��ѯ
           public static List<Command> queryCommand(String name){
        	          CommandDao commandDao = new CommandDao();
        	          List<Command>  commandList = commandDao.queryCommand(name);
        	          System.out.println(commandList.get(0).getContentList().size());
        	          return commandList;
           }
           
           //����
           public void insertOne(String name ,String description) {
        	       
        	        if(name !=null &&  !"".equals(name)    &&  description != null && !"".equals(description)) {
        	        	Command command = new Command();
        	        	CommandDao commandDao = new CommandDao();
        	        	command.setName(name);
        	        	command.setDescription(description);
        	        	commandDao.insertOne(command);
        	   }
        	  
           }
           //ɾ������
           public static  void deleteOn(String id) {
              //ע��:Ҫ���ж�id�Ƿ����
        	   if(id!=null && !"".equals(id)) {
        		   CommandDao commandDao = new CommandDao();
            	   commandDao.deleteOne(Integer.valueOf(id));
        	   }
        	   
           }
           
           //ɾ����������
           public  void deleteBench(String[] idList) {
        	   if(idList !=null && !"".equals(idList)) {
        		   List<Integer> ids = new ArrayList<>();
        		   for(String str :idList) {
        			   ids.add(Integer.valueOf(str));
        		   }
        		   CommandDao commandDao = new CommandDao();
        		   commandDao.deleteBench(ids);
        			
        	   }
           }
}
