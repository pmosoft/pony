//package net.pmosoft.pony.dams.table.dynamic;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import net.pmosoft.pony.comm.util.StringUtil;
//
//import org.junit.Ignore;
//import org.junit.Test;
//
//
//public class TabDynamicTest {
//
//    @Test @Ignore
//    public void rightPad() throws Exception {
//
//        String c02 = "CD_NM";
//        c02= StringUtil.rightPad(c02, 25);
//        System.out.println("StringUtil.rightPad(c02, 25)="+StringUtil.rightPad(c02, 25));
//        System.out.println("c02="+c02);
//
//    }
//
//    @Test
//    public void selectCreateTabScript() throws Exception {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("datasource"   , "pony");
//        params.put("dbDriver"  , "org.hsqldb.jdbcDriver");
//        params.put("dbConn"    , "jdbc:log4jdbc:mariadb://pmosoft.net:3306/pony");
//        params.put("dbUser"    , "pony");
//        params.put("dbPassword", "f1234");
//        params.put("dbType"    , "MARIADB");
//        params.put("dbOwner"   , "pony");
//        params.put("TAB_NM"    , "TDACM00060");
////        params.put("qry"       , "SELECT * FROM TDACM00060");
//
//        String result = "";
//
//        TabDaoFactory tabDaoFactory = (TabDaoFactory) Class.forName("net.pmosoft.pony.dams.table.dynamic.TabMariadbDao").newInstance();
//        //result += tabDaoFactory.selectDropTabScript(params);
//        result += tabDaoFactory.selectCreateTabScript(params);
//
//        System.out.println( result );
//
//        //System.out.println("listMeta="+listMeta);
//    }
//
//    @Test @Ignore
//    public void selectMetaTabColList() throws Exception {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("datasource"   , "pony");
//        params.put("dbDriver"  , "org.hsqldb.jdbcDriver");
//        params.put("dbConn"    , "jdbc:log4jdbc:mariadb://pmosoft.net:3306/pony");
//        params.put("dbUser"    , "pony");
//        params.put("dbPassword", "f1234");
//        params.put("dbType"    , "MARIADB");
//        params.put("dbOwner"   , "pony");
//        params.put("TAB_NM"    , "TDACM00060");
//        params.put("qry"       , "SELECT * FROM TDACM00060");
//
//        TabDaoFactory tabDaoFactory = (TabDaoFactory) Class.forName("net.pmosoft.pony.dams.table.dynamic.TabMariadbDao").newInstance();
//        List<Map<String,Object>> listMeta = tabDaoFactory.selectMetaTabColList(params);
//
//        System.out.println("listMeta="+listMeta);
//    }
//
//    @Test @Ignore
//    public void selectQryData() throws Exception {
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("datasource"   , "pony");
//        params.put("dbDriver"  , "org.hsqldb.jdbcDriver");
//        params.put("dbConn"    , "jdbc:log4jdbc:mariadb://pmosoft.net:3306/pony");
//        params.put("dbUser"    , "pony");
//        params.put("dbPassword", "f1234");
//        params.put("dbType"    , "MARIADB");
//        params.put("dbOwner"   , "pony");
//        params.put("TAB_NM"    , "TDACM00060");
//        params.put("qry"       , "SELECT * FROM TDACM00060");
//
//        TabDaoFactory tabDaoFactory = (TabDaoFactory) Class.forName("net.pmosoft.pony.dams.table.dynamic.TabMariadbDao").newInstance();
//        List<Map<String,Object>> listMeta = tabDaoFactory.selectQryData(params);
//
//        System.out.println("listMeta="+listMeta);
//    }
//
//}
