package immoc.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import immoc.bean.*;
import immoc.db.DBAcess;
public class CommandDao {

	//查询列表信息
	public List<Command> queryCommand(String name){
		
		  DBAcess  dbAcess = new DBAcess();
		  List<Command> commandList =null;
		  Command command = null;
		  SqlSession  sqlSession =null;
		  try {
			sqlSession = dbAcess.getSqlSession();
			command = new Command();
			command.setName(name);
			commandList = sqlSession.selectList("Command.queryCommand", command);
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		  return commandList;
	}
	
	//新增command
	
	public void insertOne(Command command) {
		DBAcess dbAcess = new DBAcess();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAcess.getSqlSession();
			sqlSession.insert("Command.insertToCommand", command);
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	//删除单条语句
	public void deleteOne (int id) {
		DBAcess dbAcess = new DBAcess();
		SqlSession sqlSession =null;
		try {
		       sqlSession =dbAcess.getSqlSession();
		       sqlSession.delete("Command.deleteCommand",id);
		       sqlSession.commit();
		} catch (IOException e) {
			
			e.printStackTrace();
		}finally{
			
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
  
	//删除多条信息
	public void deleteBench(List<Integer> ids) {
		DBAcess dbAcess = new DBAcess();
		SqlSession sqlSession =null;
		try {
			sqlSession = dbAcess.getSqlSession();
			sqlSession.delete("Command.deleteBench", ids);
			sqlSession.commit();//删除完信息记得提交
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(sqlSession !=null)
				sqlSession.close();
		}
	}
	
	public void updateCommand(Command command) {
		DBAcess dbAcess  = new DBAcess();
		SqlSession sqlSession = null;
		
		try {
			sqlSession = dbAcess.getSqlSession();
			sqlSession.update("Command.updateCommand", command);
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(sqlSession != null)
				sqlSession.close();
		}
	}
	
	
	
     }

