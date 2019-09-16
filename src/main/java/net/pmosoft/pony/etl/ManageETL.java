package net.pmosoft.pony.etl;

import java.util.ArrayList;

import net.pmosoft.pony.dams.table.TabInfo;
import net.pmosoft.pony.etl.extract.ExtractTab;
import net.pmosoft.pony.etl.load.LoadTab;

public class ManageETL {

    String asTabNm = "";
    String toTabNm = "";
    String tarPathNm = "c:/.../";
    
    TabInfo srcTabInfo = new TabInfo();
    TabInfo tarTabInfo = new TabInfo();
    
    public ManageETL(){}
    public ManageETL(TabInfo srcTabInfo,TabInfo tarTabInfo){ 
        this.srcTabInfo = srcTabInfo; this.tarTabInfo = tarTabInfo;
    }
    public ManageETL(String asTabNm,String toTabNm){ this.asTabNm = asTabNm; this.toTabNm = toTabNm;}
    
    public static void main(String[] args) {
        
        TabInfo srcTabInfo = new TabInfo();
        srcTabInfo.getJdbcInfo().setUrl("jdbc:log4jdbc:oracle:thin:@localhost:9951/IAMLTE");
        srcTabInfo.getJdbcInfo().setUsrId("cellplan");
        srcTabInfo.getJdbcInfo().setUsrPw("cell_2012");
        srcTabInfo.getJdbcInfo().setDb("Oracle");
        srcTabInfo.getJdbcInfo().setDriver("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        srcTabInfo.setJdbcNm("CELLPLAN");
        srcTabInfo.setOwner("CELLPLAN"); 

        TabInfo tarTabInfo = new TabInfo();
        tarTabInfo.getJdbcInfo().setUrl("jdbc:oracle:thin:@localhost:1521/orcl");
        tarTabInfo.getJdbcInfo().setUsrId("cellplan");
        tarTabInfo.getJdbcInfo().setUsrPw("cell_2012");
        tarTabInfo.getJdbcInfo().setDb("Oracle");
        tarTabInfo.getJdbcInfo().setDriver("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        tarTabInfo.setJdbcNm("CELLPLAN");
        tarTabInfo.setOwner("CELLPLAN"); 
        
        ManageETL manageETL = new ManageETL(srcTabInfo,tarTabInfo);
        //manageETL.executeDbToDb("ANALYSIS_RESULT");
        //manageETL.executeDbToDb("ANALYSIS_LIST");
        manageETL.executeDbToDbBatchEos();        
    }

    // 단일이관 - 소스 테이블명과 타켓 테이블명 동일
    public void executeDbToDbBatchEos() {
        
        ArrayList<String> tabNmList = new ArrayList<String>();
        //tabNmList.add("ANALYSIS_LIST");
        //tabNmList.add("ANALYSIS_RESULT");
        tabNmList.add("CELLDB_5G_EXCEL_IMPORT");
        tabNmList.add("COLOR");
        ////////tabNmList.add("COLOR_USER");
        tabNmList.add("DU");
        //tabNmList.add("GEO_GEOMETRYQUERY");
        //tabNmList.add("LTESECTORPARAMETER");
        //tabNmList.add("LTESYSTEM");
        //tabNmList.add("LTETRAFFIC");
        //tabNmList.add("LTE_IO_ANALYSIS");
        //tabNmList.add("MOBILE_PARAMETER");
        //tabNmList.add("NRSECTORPARAMETER");
        //tabNmList.add("NRSYSTEM");
        //tabNmList.add("NRUETRAFFIC");
        //tabNmList.add("NR_CANDIDATE_PARAMETER");
        //tabNmList.add("NR_CANDIDATE_SITE_INFO");
        //tabNmList.add("RU");
        //tabNmList.add("RU_ANTENA");
        //tabNmList.add("SCENARIO");
        //tabNmList.add("SCHEDULE");
        //tabNmList.add("SECTOR");
        //tabNmList.add("SITE");
        //tabNmList.add("SITE_DRAW_OPTION");
        //tabNmList.add("SWING_PARAMETER");
        //tabNmList.add("TILT_BUILDING_DENSITY");
        //tabNmList.add("TILT_BUILDING_DENSITY_BK");
        //tabNmList.add("TILT_NR_ANT_LIST");
        //tabNmList.add("TILT_NR_PARAMETER");
        //tabNmList.add("TILT_RUNRESULT");
        //tabNmList.add("TILT_SCENARIO_EVO");
        //tabNmList.add("TILT_SCENARIO_FINE");
        //tabNmList.add("TILT_TARGET_INFO");
        executeDbToDbBatch(tabNmList);
    }
    
    
    // 단일이관 - 소스 테이블명과 타켓 테이블명 동일
    public void executeDbToDb(String tabNm) {
        srcTabInfo.setTabNm(tabNm); new ExtractTab(srcTabInfo).executeTab();
        tarTabInfo.setTabNm(tabNm); new LoadTab(tarTabInfo).executeInsertFileToDb();        
    }

    // 단일이관 - 소스 테이블명과 타켓 테이블명 상이
    public void executeDbToDb(String srcTabNm, String tarTabNm) {
        srcTabInfo.setTabNm(srcTabNm); new ExtractTab(srcTabInfo).executeTab();
        tarTabInfo.setTabNm(tarTabNm); new LoadTab(tarTabInfo).executeInsertFileToDb();        
    }    

    // 다수이관 - 소스 테이블명과 타켓 테이블명 동일
    public void executeDbToDbBatch(ArrayList<String> tabNmList) {
        for (int i = 0; i < tabNmList.size(); i++) {
            srcTabInfo.setTabNm(tabNmList.get(i)); new ExtractTab(srcTabInfo).executeTab();
            tarTabInfo.setTabNm(tabNmList.get(i)); new LoadTab(tarTabInfo).executeInsertFileToDb();        
        }
    }
    
    // 다수이관 - 소스 테이블명과 타켓 테이블명 상이
    public void executeDbToDbBatch(ArrayList<String> srcTabNmList,ArrayList<String> tarTabNmList) {
        for (int i = 0; i < srcTabNmList.size(); i++) {
            srcTabInfo.setTabNm(srcTabNmList.get(i)); new ExtractTab(srcTabInfo).executeTab();
            tarTabInfo.setTabNm(tarTabNmList.get(i)); new LoadTab(tarTabInfo).executeInsertFileToDb();        
        }
    }
    
}