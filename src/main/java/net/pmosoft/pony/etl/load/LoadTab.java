package net.pmosoft.pony.etl.load;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.pmosoft.pony.comm.App;
import net.pmosoft.pony.comm.db.DbCon;
import net.pmosoft.pony.comm.util.FileUtil;
import net.pmosoft.pony.dams.jdbc.JdbcInfo;
import net.pmosoft.pony.dams.table.TabInfo;
import net.pmosoft.pony.etl.extract.ExtractTab;

public class LoadTab {

    private static Logger logger = LoggerFactory.getLogger(ExtractTab.class);

    TabInfo tabInfo = new TabInfo();
    JdbcInfo jdbcInfo = new JdbcInfo();

    int rowCnt = 0;
    int loadCnt = 0;

    int commitCnt = 0;
    int commitMaxCnt = 10000;

    int logCnt = 10000;

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    String qry = "";


    // 동시잡 체크
    int exeCnt = 0;


    public LoadTab(){}

    public LoadTab(JdbcInfo jdbcInfo){
        this.jdbcInfo = jdbcInfo;
        this.conn = new DbCon().getConnection(jdbcInfo);
    }

    public LoadTab(TabInfo tabInfo){
        this.tabInfo = tabInfo;
        tabInfo.getJdbcInfo().setUrl(tabInfo.getJdbcInfo().getUrl().replace("log4jdbc:", ""));
        this.conn = new DbCon().getConnection(tabInfo.getJdbcInfo());
    }

    public static void main(String[] args) {
        TabInfo tabInfo = new TabInfo();
        tabInfo.getJdbcInfo().setUrl("jdbc:oracle:thin:@localhost:1521/orcl");
        tabInfo.getJdbcInfo().setUsrId("cellplan");
        tabInfo.getJdbcInfo().setUsrPw("cell_2012");
        tabInfo.getJdbcInfo().setDb("Oracle");
        tabInfo.getJdbcInfo().setDriver("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        tabInfo.setJdbcNm("CELLPLAN");
        tabInfo.setOwner("CELLPLAN");
        tabInfo.setTabNm("ANALYSIS_RESULT");
        //tabInfo.setTabNm("DU");

        LoadTab loadTab = new LoadTab(tabInfo);
        loadTab.executeInsertFileToDb();
    }

    public void executeInsertStringToDb(String tabNm, String whereDel, String insQry) {
        Map<String, Object> result = new HashMap<String, Object>();

        // 쿼리 변수
        String qry = "";

        try {
            stmt = conn.createStatement();

            /***************************************************************
             * DELETE 테이블
             ***************************************************************/
            //qry = "DELETE FROM "+owner+"."+tabNm;
            qry = "DELETE FROM "+tabNm + " " + whereDel;
            logger.info(qry);
            stmt.execute(qry);

            /***************************************************************
             * INSERT 테이블
             ***************************************************************/
            qry = setDbmsSql(insQry);
            logger.info(qry);
            stmt.execute(qry);

            result.put("isSuccess", true);

        } catch ( Exception e ) {
            logger.info("\n"+qry);
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        } finally { DBClose(); }
    }


    public void executeInsertFileToDb() {

        logger.info("executeInsertFileToDb "+tabInfo.getOwner()+"."+tabInfo.getTabNm()+" start");
        Map<String, Object> result = new HashMap<String, Object>();

        // 파일 변수
        String encoding = "ms949";
        //String encoding = "euc-kr";
        //String encoding = "utf-8";

        // 쿼리 변수
        String qry = "";

        try {

            stmt = conn.createStatement();

            /***************************************************************
             * DELETE 테이블
             ***************************************************************/
            qry = "DELETE FROM "+tabInfo.getOwner()+"."+tabInfo.getTabNm();
            logger.info(qry);
            stmt.execute(qry);

            String pathFileNoNm = "";

            for (int i = 1; i < 1000; i++) {
                pathFileNoNm = App.excelPath+tabInfo.getTabNm()+"_"+String.format("%03d",i)+".sql";
                if(new File(pathFileNoNm).exists()) {
                    System.out.println(FileUtil.detectEncoding(pathFileNoNm));
                    //executeInsert(pathFileNoNm, encoding);
                }
            }

        } catch ( Exception e ) {
            logger.info("\n"+qry);
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        } finally { DBClose(); }
    }

    public void executeInsert(String pathFileNm, String encoding) {

        logger.info(pathFileNm);

        while(exeCnt > 10) {
            try { Thread.sleep( (int)(Math.random()*100)); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        exeCnt++;
        //System.out.println("jdbcInfo.getDriver()="+this.jdbcInfo.getDriver());
        Connection conn = new DbCon().getConnection(tabInfo.getJdbcInfo());

        try {
            Statement stmt = conn.createStatement();

            /***************************************************************
             * INSERT 테이블
             ***************************************************************/
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathFileNm),encoding));
            qry = " ";
            while (br.ready()) {
                loadCnt++;
                commitCnt++;
                qry += br.readLine()+"\n";

                if(commitCnt%commitMaxCnt == 0) {
                   stmt.execute(setDbmsSql(qry));
                   logger.info("loadCnt=========="+loadCnt);
                   commitCnt = 0;
                   qry = "";
                }
            }

            if(commitCnt < commitMaxCnt) {
                logger.info("loadCnt=========="+loadCnt);
                stmt.execute(setDbmsSql(qry));
            }

            exeCnt--;

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }


    public String setDbmsSql(String qry) {
        String retQry = "";

        if(tabInfo.getJdbcInfo().getDb().toUpperCase().equals("ORACLE")){
            retQry += "BEGIN\n";
            retQry += qry;
            retQry += "END;\n";

        } else {
            retQry += qry;
        }
        System.out.println(retQry);
        return retQry;
    }

    void DBClose(){ try { stmt.close(); conn.close();} catch (SQLException e) { e.printStackTrace(); } }

}