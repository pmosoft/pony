package test.jdbc.mybatisLogback.oracle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
    	//excuteOracle();
    	excuteSqlite();
        //excuteMariadb();
    }
    public static void excuteMariadb(){
        
        String configLocation = "classpath:springMybatisLogbackMariadb.xml"; // src/main/resources/springMybatisLogbackOracle.xml
        AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
        TestJdbcMybatisLogbackMariadb TestJdbcMybatisLogbackMariadb = ctx.getBean("TestJdbcMybatisLogbackMariadb",TestJdbcMybatisLogbackMariadb.class);
        
        /* select */        
        TestJdbcMybatisLogbackMariadb.selectEmp();
        ctx.close();
        
    }

    public static void excuteSqlite(){
    	
		String configLocation = "classpath:springMybatisLogbackSqlite.xml"; // src/main/resources/springMybatisLogbackOracle.xml
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		TestJdbcMybatisLogbackSqlite TestJdbcMybatisLogbackSqlite = ctx.getBean("TestJdbcMybatisLogbackSqlite",TestJdbcMybatisLogbackSqlite.class);
		
		/* select */		
		TestJdbcMybatisLogbackSqlite.selectSqlite();
		ctx.close();
    	
    }
    
    
    
    public static void excuteOracle(){
    	
		String configLocation = "classpath:springMybatisLogbackOracle.xml"; // src/main/resources/springMybatisLogbackOracle.xml
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		TestJdbcMybatisLogbackOracle TestJdbcMybatisLogbackOracle = ctx.getBean("TestJdbcMybatisLogbackOracle",TestJdbcMybatisLogbackOracle.class);
		
		/* select */		
		TestJdbcMybatisLogbackOracle.selectEmp();
		ctx.close();
    	
    }
    
}
