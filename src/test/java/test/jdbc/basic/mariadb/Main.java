package test.jdbc.basic.mariadb;

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

            String query = "SELECT * FROM promota.TBL_USER limit 10";
            System.out.println();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String usrId = rs.getString(1);

                System.out.println( usrId );
            }
        } catch ( Exception e ) { e.printStackTrace(); } finally { DBClose(); }

    }

    void DBConn(){


        //String DB_URL = "jdbc:mariadb://pmosoft.net:3306/sttl";
        //String DB_USER = "sttl";
        //String DB_PASSWORD = "s1234";

        String DB_URL = "jdbc:mysql://localhost:33306/promota";
        String DB_USER = "devmaster";
        String DB_PASSWORD = "devmasterisnotthedoctor";

        try {
            //Class.forName("com.mysql.cj.jdbc.Driver");
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch ( Exception e ) { e.printStackTrace(); } finally {}
    }

    void DBClose(){ rs = null; stmt = null; conn = null; }
}

