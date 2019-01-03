package test.jdbc.mybatis.oracle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

    	
		String configLocation = "classpath:test/springMybatisOracle.xml"; // src/main/resources/springMybatisOracle.xml
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
		TestJdbcMybatisOracle TestJdbcMybatisOracle = ctx.getBean("TestJdbcMybatisOracle",TestJdbcMybatisOracle.class);
		
		/* select */		
		TestJdbcMybatisOracle.selectEmp();

		//ctx.close();
    }
}
