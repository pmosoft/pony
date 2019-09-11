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
    
    String sqlScript = "";    
    String cntSqlScript = "";    
    int rowCnt = 0;    
    int extractCnt = 0;    
    int logCnt = 10000;
    
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    
    String qry = "";
    
    public ExtractTab(){}
    public ExtractTab(TabInfo tabInfo){
        this.tabInfo = tabInfo;
        this.conn = new DbCon().getConnection(tabInfo.getJdbcInfo());
    }
    
    public static void main(String[] args) {
        TabInfo tabInfo = new TabInfo();
        tabInfo.getJdbcInfo().setUrl("jdbc:log4jdbc:oracle:thin:@localhost:9951/IAMLTE");
        tabInfo.getJdbcInfo().setUsrId("cellplan");
        tabInfo.getJdbcInfo().setUsrPw("cell_2012");
        tabInfo.getJdbcInfo().setDb("Oracle");
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
        sqlScript = map.get("sqlScript").toString();
        cntSqlScript = map.get("cntSqlScript").toString();
        tabInfoList = (List<TabInfo>) map.get("tabInfoList");
        tabInfo.setQry(sqlScript);
        tabInfo.setCntQry(cntSqlScript);
    }
    
    // 조회건수를 조회
    public void selectDataCnt() {
        rowCnt = Integer.parseInt(tabInfoDynSrv.selectDataCnt(tabInfo).get("rowCnt").toString());
    }
    
    public void selectInsStatToFile() {

        logger.info("selectInsStatToFile start");
        
        Map<String, Object> result = new HashMap<String, Object>();
        
        // 파일 변수
        tabInfo.setPathFileNm(App.excelPath+tabInfo.getTabNm()+".sql");
        PrintWriter writer = null;

        // 쿼리 변수
        String db = "";
        String qry = "";
        String ColumnValue = "";
        String s01 = "";
        String s02 = "";
        
        try {

            // 파일 변수 초기화
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(tabInfo.getPathFileNm())));


            /***********************************************************************************
             *                              insert 문장 생성
             **********************************************************************************/
            s01 = "INSERT INTO "+tabInfo.getOwner()+"."+tabInfo.getTabNm()+" VALUES (";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sqlScript);
            
            /****************************
             * 메타정보 수보
             ****************************/
            //ResultSetMetaData rsmd = rs.getMetaData();
            //int colCnt = rsmd.getColumnCount();
            //System.out.println("colCnt="+colCnt);
            
            while(rs.next()){
                extractCnt++;
                if(extractCnt%logCnt == 0) {
                    logger.info("extractCnt=========="+extractCnt);
                }
                
                for (int i = 0; i < tabInfoList.size(); i++) {
                    ColumnValue = rs.getString(i+1);
                    if (tabInfoList.get(i).getDataTypeNm().trim().toUpperCase().matches("NUMBER|INT|NUMERIC") || ColumnValue==null) {
                        s02 += ColumnValue + ",";
                        //System.out.println("insertData1="+insertData);
                    } else if (tabInfoList.get(i).getDataTypeNm().trim().toUpperCase().matches("DATE|TIMESTAMP")) {
                        db = tabInfo.getJdbcInfo().getDb();
                        /********************************************************************************
                         * [DATE] DBMS 및 site에 따라 date 형식을 처리하는 것이 상이하므로 별도로 처리요 
                         ********************************************************************************/
                        // 데이트 타입 변형조건일 경우 DBMS의 SQL규칙에 맞게 형변환 처리
                        if     (db.equals("Oracle")) {ColumnValue = "TO_DATE('"+ColumnValue+"','YYYY-MM-DD HH24:MI:SS')";}
                        else if(db.equals("Oracle")) {ColumnValue = "TO_DATE('"+ColumnValue+"','YYYY-MM-DD HH24:MI:SS')";}
                       
                        s02 += ColumnValue + ",";
                    } else {          
                        s02 += "'"+ColumnValue + "',";
                    }
                }
                qry = s01 + s02 + ");"; qry = qry.replace(",);",");");
                writer.println(qry);
                qry = "";s02 = "";
            }            
            writer.close();
            result.put("isSuccess", true);

            logger.info("selectInsStatToFile rowCnt="+rowCnt+" extractCnt="+extractCnt+" end");
            
        } catch ( Exception e ) { 
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        } finally { DBClose(); writer.close();}
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