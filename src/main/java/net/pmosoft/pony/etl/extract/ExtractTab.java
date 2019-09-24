package net.pmosoft.pony.etl.extract;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.pmosoft.pony.comm.App;
import net.pmosoft.pony.comm.db.DbCon;
import net.pmosoft.pony.dams.jdbc.JdbcInfo;
import net.pmosoft.pony.dams.table.TabInfo;
import net.pmosoft.pony.dams.table.TabInfoDynSrv;

/*
 * DB의 테이블 데이터를 추출하여 샘파일로 만든다
 * 
 * */
public class ExtractTab {

    private static Logger logger = LoggerFactory.getLogger(ExtractTab.class);
    
    TabInfo  tabInfo = new TabInfo();    
    TabInfoDynSrv tabInfoDynSrv = new TabInfoDynSrv();
    Map<String, Object> map = new HashMap<String, Object>();

    List<TabInfo> tabInfoList = new ArrayList<TabInfo>();
    
    String selQry = "";    
    String cntSelQry = "";    
    int rowCnt = 0;    
    int extractCnt = 0;    
    int logCnt = 10000;
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    String qry = "";
    
    public ExtractTab(){}

    public ExtractTab(JdbcInfo jdbcInfo){
        this.conn = new DbCon().getConnection(jdbcInfo);
    }
    
    public ExtractTab(TabInfo tabInfo){
        this.tabInfo = tabInfo;
        this.conn = new DbCon().getConnection(tabInfo.getJdbcInfo());
    }
    
    public static void main(String[] args) {
        TabInfo tabInfo = new TabInfo();
        tabInfo.getJdbcInfo().setUrl("jdbc:log4jdbc:oracle:thin:@localhost:9951/IAMLTE");
        tabInfo.getJdbcInfo().setUsrId("cellplan");
        tabInfo.getJdbcInfo().setUsrPw("cell_2012");
        tabInfo.getJdbcInfo().setDb("oracle");
        tabInfo.getJdbcInfo().setDriver("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        tabInfo.setJdbcNm("CELLPLAN");
        tabInfo.setOwner("CELLPLAN"); 
        tabInfo.setTabNm("ANALYSIS_RESULT");
        //tabInfo.setTabNm("DU");
        
        ExtractTab extractTab = new ExtractTab(tabInfo);
        extractTab.executeTab();
    }

    public void executeTab() {        
        selectSelectScript();
        selectDataCnt();
        selectInsStatToFile();
    }    

    void DBClose(){ try { rs.close();stmt.close(); conn.close();} catch (SQLException e) { e.printStackTrace(); } }    

    // 테이블의 조회쿼리 및 조회건수쿼리 생성
    public void selectSelectScript() {
        map = tabInfoDynSrv.selectSelectScript(tabInfo);
        selQry = map.get("selQry").toString();
        cntSelQry = map.get("cntSelQry").toString();
        tabInfoList = (List<TabInfo>) map.get("tabInfoList");
        tabInfo.setQry(selQry);
        tabInfo.setCntQry(cntSelQry);
    }
    
    // 조회건수를 조회
    public void selectDataCnt() {
        rowCnt = Integer.parseInt(tabInfoDynSrv.selectDataCnt(tabInfo).get("rowCnt").toString());
    }
    
    public void selectInsStatToFile()
    {
        String db         = tabInfo.getJdbcInfo().getDb().toUpperCase();
        String owner      = tabInfo.getOwner();
        String tabNm      = tabInfo.getTabNm();
        String selQry     = this.selQry;
        boolean isFile    = true; 
        String pathFileNm = App.excelPath + tabInfo.getTabNm()+".sql";
        selectInsStat(db, owner, tabNm, selQry, pathFileNm, isFile);
    }

    public void selectInsStatToString()
    {
        String db         = tabInfo.getJdbcInfo().getDb().toUpperCase();
        String owner      = tabInfo.getOwner();
        String tabNm      = tabInfo.getTabNm();
        String selQry     = this.selQry;
        boolean isFile    = false;
        String pathFileNm = "";
        
        String retSql = selectInsStat(db, owner, tabNm, selQry, pathFileNm, isFile);
        System.out.println(retSql);
    }
        
    
    
    public String selectInsStat(String db, String owner, String tabNm, String selQry, String pathFileNm, boolean isFile) {

        logger.info("selectInsStatToFile start");
        
        Map<String, Object> result = new HashMap<String, Object>();

        // DB접속 변수
        Statement stmt = null;
        ResultSet rs = null;
        
        // 파일 변수
        PrintWriter writer = null;

        // 쿼리 변수
        int    colCnt = 0;
        String dataTypeNm  = "";
       
        String colData = "";
        String s01 = "";
        String s02 = "";
        String insQry = "";
        String retQry = "";
        
        try {

            // 파일 변수 초기화
            if(isFile) writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(pathFileNm)));

            /***********************************************************************************
             *                              insert 문장 생성
             **********************************************************************************/
            stmt = conn.createStatement();
            rs = stmt.executeQuery(selQry);
            s01 = "INSERT INTO "+owner+"."+tabNm+" VALUES (";
            
            /****************************
             * 메타정보 수보
             ****************************/
            ResultSetMetaData rsmd = rs.getMetaData();
            colCnt = rsmd.getColumnCount();
            for (int i = 0; i < colCnt; i++) {
                //System.out.println(rsmd.getColumnName(i+1)+"    "+rsmd.getColumnTypeName(i+1));
            }
            
            //System.out.println("colCnt="+colCnt);
            
            while(rs.next()){
            //if(rs.next()){
                extractCnt++;
                if(extractCnt%logCnt == 0) {
                    logger.info("extractCnt=========="+extractCnt);
                }

                //for (int i = 0; i < tabInfoList.size(); i++) {
                for (int i = 0; i < colCnt; i++) {
                    //dataTypeNm = tabInfoList.get(i).getDataTypeNm().trim().toUpperCase();
                    dataTypeNm = rsmd.getColumnTypeName(i+1).trim().toUpperCase();
                    
                    //System.out.println("tabInfoList.get(i).getDataTypeNm()=="+tabInfoList.get(i).getDataTypeNm()+"   "+rs.getString(i+1));
                    //System.out.println(tabInfo.getJdbcInfo().getDb());
                    colData = rs.getString(i+1);
                    if (dataTypeNm.matches("NUMBER|INT|NUMERIC") || colData==null) {
                        s02 += colData + ",";
                        //System.out.println("insertData1="+insertData);
                    } else if (dataTypeNm.matches("DATE|TIMESTAMP")) {
                        
                        /********************************************************************************
                         * [DATE] DBMS 및 site에 따라 date 형식을 처리하는 것이 상이하므로 별도로 처리요 
                         ********************************************************************************/
                        // 데이트 타입 변형조건일 경우 DBMS의 SQL규칙에 맞게 형변환 처리
                        if     (db.equals("ORACLE")) {colData = "TO_DATE('"+colData+"','YYYY-MM-DD HH24:MI:SS')";}
                        else if(db.equals("ORACLE")) {colData = "TO_DATE('"+colData+"','YYYY-MM-DD HH24:MI:SS')";}
                       
                        s02 += colData + ",";
                    } else {          
                        s02 += "'"+colData + "',";
                    }
                }
                insQry = s01 + s02 + ");"; insQry = insQry.replace(",);",");");
                if(isFile) { writer.println(insQry); } else {retQry += insQry +"\n";}
                insQry = ""; s02 = "";                
            }            
            if(isFile) writer.close();
            result.put("isSuccess", true);
            //if(!isFile) { System.out.println(retQry);}
            logger.info("selectInsStatToFile rowCnt="+rowCnt+" extractCnt="+extractCnt+" end");
            
            return retQry;
            
        } catch ( Exception e ) { 
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        } finally { try { rs.close();stmt.close();conn.close(); } catch (SQLException e) { e.printStackTrace(); }; if(isFile) writer.close();}
        
        return retQry;
    }
    
    
    
    public void selectCommaData() {}    
    public void selectBarData() {}    
    public void selectSharpBarData() {}    
    public void insDataToFile() {}    
    
    
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