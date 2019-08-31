package immoc.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBAcess {
	
	public SqlSession getSqlSession() throws IOException {
		//获取配置信息
	       Reader reader = Resources.getResourceAsReader("immoc/config/Configuration.xml");
	       //获取SqlSessionFactory
	       SqlSessionFactory sqlSessionFactory =new  SqlSessionFactoryBuilder().build(reader);
	       //打开数据库会话
	       SqlSession sqlSession = sqlSessionFactory.openSession();
	       return sqlSession;
	}

}
