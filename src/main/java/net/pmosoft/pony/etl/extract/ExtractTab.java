package net.pmosoft.pony.etl.extract;

import java.io.BufferedWriter;
import java.io.File;
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

    JdbcInfo jdbcInfo1 = new JdbcInfo();
    JdbcInfo jdbcInfo2 = new JdbcInfo();
    TabInfo  tabInfo1 = new TabInfo();
    TabInfo  tabInfo2 = new TabInfo();

    TabInfoDynSrv tabInfoDynSrv = new TabInfoDynSrv();
    Map<String, Object> map = new HashMap<String, Object>();

    List<TabInfo> tabInfoList1 = new ArrayList<TabInfo>();

    String selQry = "";
    String cntSelQry = "";
    int rowCnt = 0;
    int extractCnt = 0;
    int logCnt = 10000;

    Connection conn1 = null;
    Statement stmt1 = null;
    ResultSet rs1 = null;

    String qry = "";

    /**********************************************************************************
    *
    *                                   생성자
    *
    **********************************************************************************/
    public void __________생성자__________(){}


    public ExtractTab(){}

    public ExtractTab(TabInfo tabInfo1,TabInfo tabInfo2){
        this.tabInfo1 = tabInfo1;
        this.tabInfo2 = tabInfo2;
        this.jdbcInfo1 = tabInfo1.getJdbcInfo();
        this.jdbcInfo2 = tabInfo2.getJdbcInfo();
        this.conn1 = new DbCon().getConnection(tabInfo1.getJdbcInfo());
    }

    public static void main(String[] args) {
        TabInfo tabInfo1 = new TabInfo();
        tabInfo1.getJdbcInfo().setUrl("jdbc:log4jdbc:oracle:thin:@localhost:9951/IAMLTE");
        tabInfo1.getJdbcInfo().setUsrId("cellplan");
        tabInfo1.getJdbcInfo().setUsrPw("cell_2012");
        tabInfo1.getJdbcInfo().setDb("oracle");
        tabInfo1.getJdbcInfo().setDriver("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        tabInfo1.setJdbcNm("CELLPLAN");
        tabInfo1.setOwner("CELLPLAN");
        tabInfo1.setTabNm("ANALYSIS_RESULT");
        //tabInfo1.setTabNm("DU");

        TabInfo tabInfo2 = new TabInfo();

        ExtractTab extractTab = new ExtractTab(tabInfo1,tabInfo2);
        extractTab.executeTabToInsStatFile();
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
    public void executeTabToInsStatFile() {
        selectSelStat();
        selectDataCnt();
        selectTabToInsStatToFile();
    }

    /*
     * 테이블명 쿼리 결과를 샘파일로 저장
     **/
    public void executeTabToSamFile() {
        selectSelStat();
        selectDataCnt();
        selectTabToSamFile();
    }

    /*
     * 테이블명으로 메타정보를 조회하여 쿼리를 생성후 INSERT문장을 생성하여 문자열로 리턴
     **/
    public String executeTabToString() {
        selectSelStat();
        selectDataCnt();
        return selectTabToInsStatToString();
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
        map = tabInfoDynSrv.selectSelectScript(tabInfo1);
        selQry = map.get("selQry").toString();
        logger.info(selQry);
        cntSelQry = map.get("cntSelQry").toString();
        logger.info(cntSelQry);
        tabInfoList1 = (List<TabInfo>) map.get("tabInfoList");
        tabInfo1.setQry(selQry);
        tabInfo1.setCntQry(cntSelQry);
    }

    /*
     * 쿼리문장의 컬럼에서 데이터타입 추출
     **/
    public List<TabInfo> getColMeta(String qry) {

        // 쿼리 문자열을 리스트로 변경
        ArrayList<String> qryList = new ArrayList<String>(Arrays.asList(qry.split("\n")));

        List<TabInfo> tabInfoList1 = new ArrayList<TabInfo>();
        String asCol = ""; String dataTypeNm = ""; String colNm = "";
        for (int i = 0; i < qryList.size(); i++) {
            if(qryList.get(i).matches(".* AS [I|S|D]_.*")){

                // 파싱
                asCol = StringUtil.patternMatch(qryList.get(i)," AS [I|S|D]_[a-zA-Z0-9]*[ ]?.*").trim();
                colNm = asCol.substring(5,asCol.length());
                dataTypeNm = asCol.substring(3,4).replace("I", "INT").replace("S", "CHAR").replace("D", "DATE");

                // 리스트에 ADD
                TabInfo tabInfo = new TabInfo();
                tabInfo1.setColNm(colNm);
                tabInfo1.setDataTypeNm(dataTypeNm);
                tabInfoList1.add(tabInfo);
                System.out.println(asCol + "  " + colNm + "  " +dataTypeNm);
            }
        }

        return tabInfoList1;
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
        boolean isFile    = true;
        String pathFileNm = App.excelPath + tabInfo1.getTabNm();
        String encoding = "";
        selectInsStat(selQry, tabInfo1.getTabNm(), isFile, pathFileNm, tabInfoList1, encoding);
    }

    /*
     * 테이블명 쿼리 결과를 INSERT문장으로 생성하여 파일로 저장
     **/
    public String selectTabToInsStatToString()
    {
        boolean isFile    = false;
        String encoding = "euc-kr";
        return selectInsStat(selQry, tabInfo1.getTabNm(), isFile, "", tabInfoList1, encoding);
    }

    /*
     * 사용자정의 쿼리 조회 결과를 INSERT문장으로 생성하여 문자열로 반환
     **/
    public String selectQryToInsStatToString(String selQry, String tarTbNm) {
        return selectInsStat(selQry, tarTbNm, false, "", getColMeta(selQry),"");
    }

    /*
     * (코어) 쿼리된 결과를 INSERT문장으로 생성한다.
     **/
    public String selectInsStat(String selQry, String tabNm2, boolean isFile, String pathFileNm, List<TabInfo> tabInfoList1, String encoding) {
        //logger.info("selectInsStatToFile start");
        Map<String, Object> result = new HashMap<String, Object>();

        // DB접속 변수
        String db2 = jdbcInfo2.getDb().toUpperCase();

        Statement stmt1 = null;
        ResultSet rs1 = null;

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

        int   fileMaxRowCnt = 1000000;
        int   fileNo = 1;

        String pathFileNoNm = pathFileNm+"_"+String.format("%03d",fileNo)+".sql";

        try {

            // 파일 변수 초기화
            if(isFile) {
                for (int i = 1; i < 1000; i++) {
                    new File(pathFileNm+"_"+String.format("%03d",i)+".sql").delete();
                }
                writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(pathFileNoNm),encoding));
            }

            /***********************************************************************************
             *                              insert 문장 생성
             **********************************************************************************/
            stmt1 = conn1.createStatement();
            rs1 = stmt1.executeQuery(selQry);
            s01 = "INSERT INTO "+tabNm2+" VALUES (";

            // 메타정보 수보(ResultSetMetaData) 사용불가. 컬럼의 변형이 있으면 date를 varchar로 인식할수 있다.
            colCnt = tabInfoList1.size();

            //System.out.println("colCnt="+colCnt);

            //if(rs1.next()){
            while(rs1.next()){
                extractCnt++;
                if(extractCnt%logCnt == 0) {
                    logger.info("extractCnt="+extractCnt);
                }

                if(isFile && extractCnt%fileMaxRowCnt == 0) {
                    fileNo++;
                    pathFileNoNm = pathFileNm+"_"+String.format("%03d",fileNo)+".sql";
                    writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(pathFileNoNm),encoding));
                }

                //for (int i = 0; i < tabInfoList1.size(); i++) {
                for (int i = 0; i < colCnt; i++) {
                    dataTypeNm = tabInfoList1.get(i).getDataTypeNm().toUpperCase();

                    //System.out.println("tabInfoList1.get(i).getDataTypeNm()=="+tabInfoList1.get(i).getDataTypeNm()+"   "+rs1.getString(i+1));
                    //System.out.println(tabInfo1.getJdbcInfo().getDb());
                    colData = rs1.getString(i+1);
                    if (dataTypeNm.matches("NUMBER|INT|NUMERIC") || colData==null) {
                        s02 += colData + ",";
                    } else if (dataTypeNm.matches("DATE|TIMESTAMP")) {
                        s02 += getDateColData(db2,colData) + ",";
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
            if(isFile) writer.close();
            e.printStackTrace();
        }
        //finally { try { rs1.close();stmt1.close();conn1.close(); } catch (SQLException e) { e.printStackTrace(); }; if(isFile) writer.close();}

        return retQry;
    }


    /**********************************************************************************
    *
    *                                      샘파일생성
    *
    **********************************************************************************/
    /*
     * 테이블명 쿼리 결과를 INSERT문장으로 생성하여 파일로 저장
     **/
    public void selectTabToSamFile()
    {
        boolean isFile    = true;
        selectSamFile(selQry, tabInfo1.getPathFileNm(), tabInfo1.getDelimeter(), tabInfoList1);
    }


    /*
     * (코어) 쿼리된 결과를 샘파일로 생성한다.
     **/
    public String selectSamFile(String selQry, String pathFileNm, String delimeter, List<TabInfo> tabInfoList1) {
        Map<String, Object> result = new HashMap<String, Object>();

        // DB접속 변수
        String db2 = jdbcInfo2.getDb().toUpperCase();

        Statement stmt1 = null;
        ResultSet rs1 = null;

        // 파일 변수
        PrintWriter writer = null;

        // 쿼리 변수
        int    colCnt = 0;
        String dataTypeNm  = "";

        String colData = "";
        String s02 = "";
        String retQry = "";

        try {

            // 파일 변수 초기화
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(pathFileNm),"euc-kr"));

            /***********************************************************************************
             *                                    샘파일 생성
             **********************************************************************************/
            stmt1 = conn1.createStatement();
            rs1 = stmt1.executeQuery(selQry);

            colCnt = tabInfoList1.size();

            while(rs1.next()){
                extractCnt++; if(extractCnt%logCnt == 0) { logger.info("extractCnt="+extractCnt); }

                for (int i = 0; i < colCnt; i++) {
                    colData = StringUtil.trimNull(rs1.getString(i+1));
                    s02 += colData + delimeter;
                }
                writer.println(s02);
                s02 = "";
            }
            writer.close();
            result.put("isSuccess", true);
            logger.info("extractCnt="+extractCnt);

            return retQry;

        } catch ( Exception e ) {
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        } finally { try { rs1.close();stmt1.close();conn1.close(); } catch (SQLException e) { e.printStackTrace(); }; writer.close();}

        return retQry;
    }



    /**********************************************************************************
    *
    *                                    유틸
    *
    **********************************************************************************/
    public void _________유틸_________(){}

    /*
     * Date형의 data를 변형하여 리턴
     **/
    public String getDateColData(String db, String colData) {
        if     (db.equals("ORACLE"))  {colData = "TO_DATE('"+colData+"','YYYY-MM-DD HH24:MI:SS')";}
        else if(db.equals("POSTGRE")) {colData = "TO_DATE('"+colData+"','YYYY-MM-DD HH24:MI:SS')";}

        return colData;
    }

    /*
     * 조회건수
     **/
    public void selectDataCnt() {
        rowCnt = Integer.parseInt(tabInfoDynSrv.selectDataCnt(tabInfo1).get("rowCnt").toString());
    }

    /*
     * DB close
     **/
    void DBClose(){ try { rs1.close();stmt1.close(); conn1.close();} catch (SQLException e) { e.printStackTrace(); } }

}