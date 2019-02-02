package test.jdbc.mybatis.sqlite;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;



public class Main {

    public static void main(String[] args) {

        String resource = "jdbcinfo-mybatis-config.xml";
        Properties props = new Properties();
        props.put("driver"      , "org.sqlite.JDBC");
        props.put("url"         , "jdbc:log4jdbc:sqlite:C:/pony/pony.db");
        props.put("username"    , "");
        props.put("password"    , "");
        props.put("mapper"      , "test/jdbc/mybatis/sqlite/EmpDao.xml ");

        SqlSession session = null;

        try {
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory
                = new SqlSessionFactoryBuilder().build(inputStream, props);

            session = sqlSessionFactory.openSession(false);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }


        EmpDao dao = session.getMapper(EmpDao.class);
        ArrayList<EmpDto> empList = dao.selectEmp();
        for(int i=0;i<empList.size();i++){
            System.out.println("empno="+empList.get(i).empno+"   "+"ename="+empList.get(i).ename );
        }


        session.commit();
        session.close();

    }
}
