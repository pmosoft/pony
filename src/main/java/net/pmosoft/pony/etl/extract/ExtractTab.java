package net.pmosoft.pony.etl.extract;

import java.io.BufferedWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;

import net.pmosoft.pony.dams.jdbc.JdbcInfo;
import net.pmosoft.pony.dams.table.TabInfo;
import net.pmosoft.pony.dams.table.TabInfoSrv;

/*
 * DB의 테이블 데이터를 추출하여 샘파일로 만든다
 * 
 * */
public class ExtractTab {
    
    TabInfo  tabInfo = new TabInfo();    
    
    public ExtractTab(){}
    public ExtractTab(TabInfo tabInfo){this.tabInfo = tabInfo;}
    
    public static void main(String[] args) {
        TabInfo tabInfo = new TabInfo();
        tabInfo.getJdbcInfo().setUrl("jdbc:oracle:thin:@localhost:9951/IAMLTE");
        tabInfo.getJdbcInfo().setUsrId("cellplan");
        tabInfo.getJdbcInfo().setUsrPw("cell_2012");
        tabInfo.getJdbcInfo().setDriver("Oracle");
        tabInfo.setJdbcNm("CELLPLAN");
        tabInfo.setOwner("CELLPLAN"); 
        tabInfo.setTabNm("SCENARIO");
        
        ExtractTab extractTab = new ExtractTab(tabInfo);
        extractTab.executeTab();
    }

    public void executeTab() {

        TabInfoSrv tabInfoSrv = new TabInfoSrv();
        //Map<String, Object> map = tabInfoSrv.selectColScript(tabInfo);
        //System.out.println(map.get("sqlScript"));
    }    

    public void executeTabComma() {
        
    }    
    
    public void executeTabBar() {
        
    }    
    
    public void executeQry() {
        
    }    

    public void executeQryComma() {
        
    }    
    
    public void executeQryBar() {
        
    }    
    
    
    public void execute() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        String insQry01 = "";
        String insQry02 = "";
        String insQry03 = "";
        String col = "";
        
        BufferedWriter bw = null;
        

        String DB_URL = "jdbc:sqlite:C:/pony/pony.db";
        String DB_USER = "";
        String DB_PASSWORD = "";

        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = conn.createStatement();
            String query = "SELECT DISTINCT TAB_NM FROM TDACM00080 WHERE UPPER(JDBC_NM) = 'EOS_DEV'";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                //System.out.println(rs.getString("TAB_NM"));
            }
        } catch ( Exception e ) { e.printStackTrace(); } finally {try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }}        
    }
    
   
    
}