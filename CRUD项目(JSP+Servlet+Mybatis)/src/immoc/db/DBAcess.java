package immoc.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class DBAcess {
	
	public SqlSession getSqlSession() throws IOException {
		//��ȡ������Ϣ
	       Reader reader = Resources.getResourceAsReader("immoc/config/Configuration.xml");
	       //��ȡSqlSessionFactory
	       SqlSessionFactory sqlSessionFactory =new  SqlSessionFactoryBuilder().build(reader);
	       //�����ݿ�Ự
	       SqlSession sqlSession = sqlSessionFactory.openSession();
	       return sqlSession;
	}

}
