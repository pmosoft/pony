package net.pmosoft.pony.dams.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.pmosoft.pony.comm.util.FileUtil;
import net.pmosoft.pony.dams.table.dynamic.TabDaoFactory;

public class TabInfoSrvOnlyServer2 {


    private static Logger logger = LoggerFactory.getLogger(TabInfoSrvOnlyServer2.class);

    static String pathFileNm = "C:.../dams/table/src.dat";

    public Connection conn = null;
    public Statement stmt = null;
    public ResultSet rs = null;
    public TabDaoFactory tabDaoFactory = null;

    public Map<String, String> params = new HashMap<String, String>();

    TabInfoSrvOnlyServer2(){
        params.put("datasource", "pony");
        params.put("dbDriver", "org.mariadb.jdbc.Driver");
        params.put("dbConn","jdbc:mariadb://pmosoft.net:3306/sttl");
        params.put("dbUser","sttl");
        params.put("dbPassword","s1234");
        params.put("dbType", "MARIADB");
        params.put("dbOwner","sttl");

        try { tabDaoFactory = (TabDaoFactory) Class.forName("net.pmmosoft.p...TabOracleDao").newInstance(); } catch (Exception e) { e.printStackTrace(); }
        //DBConn();
    }

    public static void main(String[] args) {
        testInsertStat();
        //testParseSrctoTabInfo();
    }

    public static void testInsertStat() {

        String tabNm = "";  String condition = "";
        TabInfoSrvOnlyServer2 tabInfoSrv = new TabInfoSrvOnlyServer2();
        tabNm = "aaa";
        condition = " WHERE AA = 'SS'";
        tabInfoSrv.insertStat(tabNm,condition);

    }

    public static void testmultiInsertStat() {
        TabInfoSrvOnlyServer2 tabInfoSrv = new TabInfoSrvOnlyServer2();
        tabInfoSrv.multiInsertStat();

    }

    public static void testParseSrcToTabInfo(){

        parseSrcToTabInfo(pathFileNm);
    }

    // 소스를 파싱하여 테이블 정보를 추출하여 출력
    public static void parseSrcToTabInfo(String pathFileNm) {

        String str = ""; String rule = ""; String tabNm = ""; Pattern p; Matcher m;

        ArrayList<String> srcList = FileUtil.fileToList(pathFileNm);
        Set<String> uTabSet =new HashSet<String>();

        rule =" W[a-zA-Z][0-9][a-zA-Z][0-9] ";
        for (int i=0;i<srcList.size();i++){
            str = srcList.get(i);
            p = Pattern.compile(rule); m = p.matcher(str);
            while(m.find()) {
                tabNm = m.group();
                tabNm = tabNm.replace(" ","");
                uTabSet.add(tabNm);
            }
        }

        List<String> tabList = new ArrayList<String>(uTabSet);
        Collections.sort(tabList);

        String tabHnm = "";
        TabInfoData tabInfoData = new TabInfoData();
        for(int i=0; i<tabList.size();i++){
            tabNm = tabList.get(i);
            for(int j=0; j <tabInfoData.tabList.size();j++){
                if(tabInfoData.tabList.get(j).tabNm.equals(tabList.get(i))){
                    tabHnm = tabInfoData.tabList.get(j).tabHnm;
                    System.out.println(tabNm+" --"+tabHnm);
                    break;
                }
            }
        }
        for (int i=0; i < tabList.size(); i++) {
        }
    }

     // 테이블명단위 insert문 생성(다수 테이블)
    public void multiInsertStat(){
            insertStat("aaa","");
            insertStat("bbb","");
    }

    //  테이블명단위 insert문 생성(싱글 테이블)
    public void insertStat(String tabNm, String condition){
            params.put("TAB_NM",tabNm);
            params.put("CONDITION",condition);
            try {
                System.out.println(tabDaoFactory.selectInsertData(params));
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }

    void DBConn(){
        String DB_URL = "jdbc:oracle:thin:@!)....:!841:Randb"; String DB_USER = "AAA"; String DB_PASSWORD = "asdd";

            try {
                Class.forName("oracle.jdbc.dirver.Oracledriver");
                try {
                    conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
    }

    void DBClose() { rs=null;stmt=null; conn=null;}
}