package test.jdbc.basic.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

    	TestJdbcBasicOracle oracleBasicJdbc = new TestJdbcBasicOracle();
    	oracleBasicJdbc.test01();
    }
}

class TestJdbcBasicOracle {

    public Connection conn = null;
    public Statement stmt = null;
    public ResultSet rs = null;

    TestJdbcBasicOracle() { DBConn(); }

    public void test01(){

        try {
            stmt = conn.createStatement();

            String query = "SELECT '1' empno, 'aaa' ename from dual";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String empno = rs.getString("empno");
                String ename = rs.getString("ename");

                System.out.println(empno + " : " + ename);
            }
        } catch ( Exception e ) { e.printStackTrace(); } finally { DBClose(); }

    }

    void DBConn(){

        //String DB_URL = "jdbc:oracle:thin:@localhost:1521/ORCL"; String DB_USER = "scott"; String DB_PASSWORD = "tiger";
        String DB_URL = "jdbc:oracle:thin:@localhost:9951/IAMLTE"; String DB_USER = "cellplan"; String DB_PASSWORD = "cell_2012";

        try {
        	Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch ( Exception e ) { e.printStackTrace(); } finally {}
    }

    void DBClose(){ rs = null; stmt = null; conn = null; }
}

