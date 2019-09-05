package net.pmosoft.pony.etl;

import net.pmosoft.pony.dams.jdbc.JdbcInfo;
import net.pmosoft.pony.dams.table.TabInfo;
import net.pmosoft.pony.etl.extract.ExtractTab;
import net.pmosoft.pony.etl.load.InsertFileToDb;

public class ManageETL {

    String asTabNm = "";
    String toTabNm = "";
    String tarPathNm = "c:/.../";
    
    public ManageETL(){}
    public ManageETL(String asTabNm,String toTabNm){ this.asTabNm = asTabNm; this.toTabNm = toTabNm;}
    
    public static void main(String[] args) {
        String asTabNm = "SCENARIO";
        String toTabNm = "SCENARIO";
        
        ManageETL manageETL = new ManageETL(asTabNm,toTabNm);
        manageETL.execute();
    }
    
    public void execute() {

        //new ExtractTab(asTabNm).execute();
        //new InsertFileToDb(toTabNm).execute();
    }
    

    public void executeDbToDb() {
        JdbcInfo asJdbcInfo = new JdbcInfo();
        JdbcInfo toJdbcInfo = new JdbcInfo();
        TabInfo asTabInfo = new TabInfo();
        TabInfo toTabInfo = new TabInfo();        

        asJdbcInfo.setDriver("oracle.jdbc.driver.OracleDriver");
        asJdbcInfo.setUrl("jdbc:oracle:thin:@localhost:9951/IAMLTE");
        asJdbcInfo.setUsrId("cellplan");
        asJdbcInfo.setUsrPw("cell_2012");
        
        toJdbcInfo.setDriver("oracle.jdbc.driver.OracleDriver");
        toJdbcInfo.setUrl("jdbc:oracle:thin:@localhost:9951/IAMLTE");
        toJdbcInfo.setUsrId("cellplan");
        toJdbcInfo.setUsrPw("cell_2012");
        
        //new ExtractToFile(asTabNm).execute();
        //new InsertFileToDb(toTabNm).execute();
    }

}