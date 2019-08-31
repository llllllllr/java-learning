package immoc.service;

import java.util.ArrayList;
import java.util.List;

import immoc.bean.Command;
import immoc.dao.CommandDao;

public class CommandService {

	       //修改
	      public void updateCommand(Command command) {
	    	  CommandDao commandDao = new CommandDao();
	    	  commandDao.updateCommand(command);
	      }
	        //查询
           public static List<Command> queryCommand(String name){
        	          CommandDao commandDao = new CommandDao();
        	          List<Command>  commandList = commandDao.queryCommand(name);
        	          System.out.println(commandList.get(0).getContentList().size());
        	          return commandList;
           }
           
           //新增
           public void insertOne(String name ,String description) {
        	       
        	        if(name !=null &&  !"".equals(name)    &&  description != null && !"".equals(description)) {
        	        	Command command = new Command();
        	        	CommandDao commandDao = new CommandDao();
        	        	command.setName(name);
        	        	command.setDescription(description);
        	        	commandDao.insertOne(command);
        	   }
        	  
           }
           //删除单条
           public static  void deleteOn(String id) {
              //注意:要先判断id是否合理
        	   if(id!=null && !"".equals(id)) {
        		   CommandDao commandDao = new CommandDao();
            	   commandDao.deleteOne(Integer.valueOf(id));
        	   }
        	   
           }
           
           //删除多条数据
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
