package test.jdbc.basic.postgre;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) {

    	TestJdbcBasicSqlite sqliteBasicJdbc = new TestJdbcBasicSqlite();
    	sqliteBasicJdbc.test01();
    }
}

class TestJdbcBasicSqlite {

    public Connection conn = null;
    public Statement stmt = null;
    public ResultSet rs = null;

    TestJdbcBasicSqlite() { DBConn(); }

    public void test01(){

        try {
            stmt = conn.createStatement();
            String query = "select * from (select '1' empno, 'abc' ename union all select '2' empno, 'def' ename) emp";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String empno = rs.getString("empno");
                String ename = rs.getString("ename");
                System.out.println("empno="+empno+" ename=" + ename);
            }
        } catch ( Exception e ) { e.printStackTrace(); } finally { DBClose(); }

    }

    void DBConn(){

        //String DB_URL = "jdbc:postgresql://localhost:5432/postgres";
        String DB_URL = "jdbc:postgresql://192.168.0.6:5432/postgres";
        String DB_USER = "postgres"; String DB_PASSWORD = "1";

        //String DB_URL = "jdbc:postgresql://localhost:5432/cellplan";
        //String DB_USER = "cellplan"; String DB_PASSWORD = "cell_2012";


        try {
        	Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch ( Exception e ) { e.printStackTrace(); } finally {}
    }

    void DBClose(){ rs = null; stmt = null; conn = null; }
}

