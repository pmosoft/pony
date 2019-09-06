package net.pmosoft.pony.dams.table;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.pmosoft.pony.dams.table.dynamic.TabDaoFactory;


public class TabInfoTest  {

    private static Logger logger = LoggerFactory.getLogger(TabInfoTest.class);

    public static TabInfo tabInfo = new TabInfo();
    public static TabInfoSrv tabInfoSrv = new TabInfoSrv();
    
    TabInfoTest(){
        tabInfo.jdbcInfo.setUrl("jdbc:log4jdbc:mariadb://pmosoft.net:3306/sttl");
        tabInfo.jdbcInfo.setUsrId("sttl");
        tabInfo.jdbcInfo.setUsrPw("s1234");
        tabInfo.jdbcInfo.setDriver("Mariadb");

        tabInfo.setOwner("sttl");
    }

    /*
    private static SqlSession sqlSession(TabInfo inVo){

        Properties props = new Properties();
        props.put("driver"      , "net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        props.put("url"         , "jdbc:log4jdbc:mariadb://pmosoft.net:3306/sttl");
        props.put("username"    , "sttl");
        props.put("password"    , "s1234");
        props.put("mapper"      , "net/pmosoft/pony/dams/table/TabInfoMariadbDyn.xml");

        SqlSession session = null;

        try {
            InputStream inputStream = Resources.getResourceAsStream("jdbcinfo-mybatis-config.xml");
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, props);
            session = sqlSessionFactory.openSession(false);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return session;
    }

*/
    public static void main(String[] args) {
        //testSelectMetaTabInfoList();
        //selectSelColScriptTest();
        selectTabQryListTest();
        //selectCreateScriptTest();
    }

    public static void selectTabQryListTest() {
        TabInfoSrv tabInfoSrv = new TabInfoSrv();
        TabInfo tabInfo = new TabInfo();
        tabInfo.getJdbcInfo().setUrl("jdbc:log4jdbc:mariadb://pmosoft.net:3306/sttl");
        tabInfo.getJdbcInfo().setUsrId("sttl");
        tabInfo.getJdbcInfo().setUsrPw("s1234");
        tabInfo.getJdbcInfo().setDriver("Mariadb");
        tabInfo.setOwner("sttl"); tabInfo.setTabNm("TSYUR00020");
        tabInfo.setChkSelect(false);tabInfo.setTxtSelect("Select *");
        tabInfo.setOrderBy("1");
        Map<String, Object> map = tabInfoSrv.selectTabQryList(tabInfo);
        System.out.println("selectTabQryListTest="+map.get("errUsrMsg"));
        System.out.println("selectTabQryListTest="+map.get("tabQryOutVoList"));
    }

    public static void selectCreateScriptTest() {
        TabInfoSrv tabInfoSrv = new TabInfoSrv();
        List<TabInfo> inVo = new ArrayList<TabInfo>();
        TabInfo tabInfo = new TabInfo();
        tabInfo.getJdbcInfo().setUrl("jdbc:log4jdbc:mariadb://pmosoft.net:3306/sttl");
        tabInfo.getJdbcInfo().setUsrId("sttl");
        tabInfo.getJdbcInfo().setUsrPw("s1234");
        tabInfo.getJdbcInfo().setDriver("Mariadb");
        tabInfo.setChk(true);
        tabInfo.setOwner("sttl"); tabInfo.setTabNm("TSYUR00020");
        inVo.add(tabInfo);
        Map<String, Object> map = tabInfoSrv.selectCreateScript(inVo);
        System.out.println(map.get("createScript"));
    }

    public static void selectMetaTabInfoListTest() {
        TabInfoSrv tabInfoSrv = new TabInfoSrv();
        TabInfo inVo = new TabInfo();
        inVo.setOwner("sttl"); inVo.setTabNm("TSYUR00010");
        //List<TabInfo> tabInfoOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectMetaTabInfoList(inVo);
    }

}