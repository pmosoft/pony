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
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.pmosoft.pony.comm.App;
import net.pmosoft.pony.comm.db.DbCon;
import net.pmosoft.pony.comm.util.StringUtil;
import net.pmosoft.pony.dams.jdbc.JdbcInfo;
import net.pmosoft.pony.dams.table.TabInfo;
import net.pmosoft.pony.dams.table.TabInfoDynSrv;

/*
 * DB의 테이블 데이터를 추출하여 샘파일로 만든다
 * 
 * */
public class ExtractTab {

    private static Logger logger = LoggerFactory.getLogger(ExtractTab.class);

    JdbcInfo jdbcInfo = new JdbcInfo(); 
    
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

    /**********************************************************************************
    *
    *                                   생성자
    *
    **********************************************************************************/
    public void __________생성자__________(){}
    
    
    public ExtractTab(){}

    public ExtractTab(JdbcInfo jdbcInfo){
        this.jdbcInfo = jdbcInfo;
        this.conn = new DbCon().getConnection(jdbcInfo);
        this.tabInfo.setJdbcInfo(jdbcInfo);
        this.tabInfo.setOwner(jdbcInfo.getUsrId());
    }
    
    public ExtractTab(TabInfo tabInfo){
        this.tabInfo = tabInfo;
        this.jdbcInfo = tabInfo.getJdbcInfo();
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

    /**********************************************************************************
    *
    *                                   추출수행
    *
    **********************************************************************************/
    public void __________추출수행__________(){}
    
    
    /*
     * 테이블명으로 메타정보를 조회하여 쿼리를 생성후 INSERT문장을 생성하여 파일로 저장
     **/
    public void executeTab() {        
        selectSelStat();
        selectDataCnt();
        selectTabToInsStatToFile();
    }
    
    /**********************************************************************************
    *
    *                                  쿼리문장생성
    *
    **********************************************************************************/
    public void _____쿼리문장생성_____(){}

    /*
     * 테이블명으로 메타정보를 조회하여 쿼리를 생성
     **/
    public void selectSelStat() {
        map = tabInfoDynSrv.selectSelectScript(tabInfo);
        selQry = map.get("selQry").toString();
        logger.info(selQry);
        cntSelQry = map.get("cntSelQry").toString();
        logger.info(cntSelQry);
        tabInfoList = (List<TabInfo>) map.get("tabInfoList");
        tabInfo.setQry(selQry);
        tabInfo.setCntQry(cntSelQry);
    }
    
    /*
     * 쿼리문장의 컬럼에서 데이터타입 추출
     **/
    public List<TabInfo> getColMeta(String qry) {

        // 쿼리 문자열을 리스트로 변경
        ArrayList<String> qryList = new ArrayList<String>(Arrays.asList(qry.split("\n")));
        
        List<TabInfo> tabInfoList = new ArrayList<TabInfo>();
        String asCol = ""; String dataTypeNm = ""; String colNm = "";
        for (int i = 0; i < qryList.size(); i++) {
            if(qryList.get(i).matches(".* AS [I|S|D]_.*")){
                
                // 파싱
                asCol = StringUtil.patternMatch(qryList.get(i)," AS [I|S|D]_[a-zA-Z0-9]*[ ]?.*").trim();
                colNm = asCol.substring(5,asCol.length());
                dataTypeNm = asCol.substring(3,4).replace("I", "INT").replace("S", "CHAR").replace("D", "DATE");

                // 리스트에 ADD
                TabInfo tabInfo = new TabInfo();
                tabInfo.setColNm(colNm);
                tabInfo.setDataTypeNm(dataTypeNm);
                tabInfoList.add(tabInfo);
                System.out.println(asCol + "  " + colNm + "  " +dataTypeNm);
            }
        }       
        
        return tabInfoList;
    }    
    
   
    
    /**********************************************************************************
    *
    *                                  INSERT문장생성
    *
    **********************************************************************************/
    public void _____INSERT문장생성_________(){}
   

    /*
     * 테이블명 쿼리 결과를 INSERT문장으로 생성하여 파일로 저장
     **/
    public void selectTabToInsStatToFile()
    {
        String selQry     = this.selQry;
        String tarOwner   = tabInfo.getOwner();
        String tarTabNm   = tabInfo.getTabNm();
        boolean isFile    = true; 
        String pathFileNm = App.excelPath + tabInfo.getTabNm()+".sql";
        selectInsStat(selQry, tarTabNm, isFile, pathFileNm, tabInfoList);
    }

    /*
     * 테이블명 쿼리 결과를 INSERT문장으로 생성하여 문자열로 반환
     **/
    public String selectTabToInsStatToString(String selQry, String tabNm) {
        tabInfo.setTabNm(tabNm);
        selectSelStat();
        return selectInsStat(selQry, tabNm, false, "", tabInfoList);
    }

    /*
     * 사용자정의 쿼리 조회 결과를 INSERT문장으로 생성하여 문자열로 반환
     **/
    public String selectQryToInsStatToString(String selQry, String tarTbNm) {
        return selectInsStat(selQry, tarTbNm, false, "", getColMeta(selQry));
    }
    
    
    /*
     * (코어) 쿼리된 결과를 INSERT문장으로 생성한다.
     **/
    public String selectInsStat(String selQry, String tarTabNm, boolean isFile, String pathFileNm, List<TabInfo> tabInfoList) {
        //logger.info("selectInsStatToFile start");
        Map<String, Object> result = new HashMap<String, Object>();

        // DB접속 변수
        String db = jdbcInfo.getDb().toUpperCase();
        
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
            System.out.println("selQry==="+selQry);
            rs = stmt.executeQuery(selQry);
            s01 = "INSERT INTO "+tarTabNm+" VALUES (";
            
            // 메타정보 수보(ResultSetMetaData) 사용불가. 컬럼의 변형이 있으면 date를 varchar로 인식할수 있다.
            colCnt = tabInfoList.size();
            
            //System.out.println("colCnt="+colCnt);
            
            //if(rs.next()){
            while(rs.next()){
                extractCnt++;
                if(extractCnt%logCnt == 0) {
                    logger.info("extractCnt="+extractCnt);
                }

                //for (int i = 0; i < tabInfoList.size(); i++) {
                for (int i = 0; i < colCnt; i++) {
                    dataTypeNm = tabInfoList.get(i).getDataTypeNm().toUpperCase();
                    
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
                        else if(db.equals("POSTGRE")) {colData = "TO_DATE('"+colData+"','YYYY-MM-DD HH24:MI:SS')";}
                       
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
            if(!isFile) { /* logger.info(retQry); */ }
            logger.info("extractCnt="+extractCnt);
            
            return retQry;
            
        } catch ( Exception e ) { 
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        } finally { try { rs.close();stmt.close();conn.close(); } catch (SQLException e) { e.printStackTrace(); }; if(isFile) writer.close();}
        
        return retQry;
    }
    


    /**********************************************************************************
    *
    *                                    유틸
    *
    **********************************************************************************/
    public void _________유틸_________(){}
    

    /*
     * 조회건수
     **/
    public void selectDataCnt() {
        rowCnt = Integer.parseInt(tabInfoDynSrv.selectDataCnt(tabInfo).get("rowCnt").toString());
    }
    
    /*
     * DB close
     **/
    void DBClose(){ try { rs.close();stmt.close(); conn.close();} catch (SQLException e) { e.printStackTrace(); } }    
        

    /**********************************************************************************
    *
    *                                      기타
    *
    **********************************************************************************/
    public void _________기타_________(){}
    
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