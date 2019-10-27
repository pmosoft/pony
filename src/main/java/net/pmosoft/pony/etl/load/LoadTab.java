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
    int commitMaxCnt = 1;

    int logCnt = 1;

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    String qry = "";

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
        tabInfo.getJdbcInfo().setUrl("jdbc:oracle:thin:@192.168.0.6:1521/orcl");
        tabInfo.getJdbcInfo().setUsrId("cellplan");
        tabInfo.getJdbcInfo().setUsrPw("cell_2012");
        tabInfo.getJdbcInfo().setDb("Oracle");
        tabInfo.getJdbcInfo().setDriver("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        tabInfo.setJdbcNm("CELLPLAN");
        tabInfo.setOwner("CELLPLAN");
        tabInfo.setTabNm("BD_LOSS");
        //tabInfo.setTabNm("DU");

        LoadTab loadTab = new LoadTab(tabInfo);
        try {
            loadTab.executeInsertFileToDb();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void executeInsertStringToDb(String tabNm, String whereDel, String insQry) throws Exception {
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
            e.printStackTrace();
            throw e;
        } finally { DBClose(); }
    }


    public void executeInsertFileToDb()  throws Exception {

        logger.info("executeInsertFileToDb "+tabInfo.getOwner()+"."+tabInfo.getTabNm()+" start");
        Map<String, Object> result = new HashMap<String, Object>();

        // 파일 변수
        String pathFileNm = App.excelPath+tabInfo.getTabNm()+".sql";
        String encoding = "";

        // 쿼리 변수
        String qry = "";

        File f = new File(pathFileNm);
        if(!f.exists()) logger.info("No exists File");
        if(!f.canRead()) logger.info("Read protected");

        try {

            stmt = conn.createStatement();

            /***************************************************************
             * DELETE 테이블
             ***************************************************************/
            qry = "DELETE FROM "+tabInfo.getOwner()+"."+tabInfo.getTabNm();
            logger.info(qry);
            stmt.execute(qry);

            /***************************************************************
             * INSERT 테이블
             ***************************************************************/
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(pathFileNm),"euc-kr"));
            qry = " ";
            while (br.ready()) {
                loadCnt++;
                commitCnt++;
                qry += br.readLine()+"\n";
                //logger.info(qry);

                if(commitCnt%commitMaxCnt == 0) {
                   try { stmt.execute(setDbmsSql(qry));} catch (Exception e) {}
                   logger.info("loadCnt=========="+loadCnt);
                   commitCnt = 0;
                   qry = "";
                }
            }

            if(commitCnt < commitMaxCnt) {
                logger.info("loadCnt=========="+loadCnt);
                try { stmt.execute(setDbmsSql(qry));} catch (Exception e) {}
                stmt.execute(setDbmsSql(qry));
            }
            result.put("isSuccess", true);
            logger.info("executeInsertFileToDb "+tabInfo.getOwner()+"."+tabInfo.getTabNm()+" loadCnt="+loadCnt+" end");

        } catch ( Exception e ) {
            logger.info("\n"+qry);
            e.printStackTrace();
            throw e;
        } finally { DBClose(); }
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

        return retQry;
    }

    void DBClose(){ try { stmt.close(); conn.close();} catch (SQLException e) { e.printStackTrace(); } }

}