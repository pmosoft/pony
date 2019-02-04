/*******************************************************************************
@title:테이블 컨트롤러
@description-start
@description-end
@developer:피승현
@progress-rate:80%
@update-history-start
-------------------------------------------------------------------------------
|   날짜   |수정자|내용
-------------------------------------------------------------------------------
|2017.11.01|피승현|최초개발
-------------------------------------------------------------------------------
@update-history-end
********************************************************************************/

package net.pmosoft.pony.dams.table;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.pmosoft.pony.comm.util.StringUtil;
import net.pmosoft.pony.dams.jdbc.JdbcInfo;
import net.pmosoft.pony.dams.jdbc.JdbcInfoDao;

@Service
public class TabInfoSrv {

    private static Logger logger = LoggerFactory.getLogger(TabInfoSrv.class);

    @Autowired
    private TabInfoDao tabInfoDao;

    @Autowired
    private JdbcInfoDao jdbcInfoDao;

    @Autowired
    private TabInfoValidatorSrv tabValidatorSrv;

    /**********************************************************************************
    *
    *                               JDBC Mybatis Session
    *
    **********************************************************************************/
    private SqlSession sqlSession(TabInfo inVo){

        //System.out.println("sqlSession");
        JdbcInfo jdbcInfo = new JdbcInfo(); jdbcInfo.setJdbcNm(inVo.getJdbcNm());
        JdbcInfo jdbcVo = jdbcInfoDao.selectJdbcInfo(jdbcInfo);
        //System.out.println("jdbcVo=="+jdbcVo.getDriver());
        //daoClassPath = (String) codeList.get(0).get("CD_PARAM1");

        String driver = "";
        //if     (jdbcVo.getDriver().equals("oracle" )) driver = "oracle.jdbc.driver.OracleDriver";
        //else if(jdbcVo.getDriver().equals("mariadb")) driver = "org.mariadb.jdbc.Driver";
        //else if(jdbcVo.getDriver().equals("sqlite" )) driver = "org.sqlite.JDBC";

        Properties props = new Properties();
        props.put("driver"      , "net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
        props.put("url"         , jdbcVo.getUrl()  );
        props.put("username"    , jdbcVo.getUsrId());
        props.put("password"    , jdbcVo.getUsrPw());
        props.put("mapper"      , "net/pmosoft/pony/dams/table/TabInfo"+StringUtil.replaceFirstCharUpperCase(jdbcVo.getDriver())+"Dyn.xml");

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

    /**********************************************************************************
    *
    *                               테이블정보 추출 및 저장
    *
    **********************************************************************************/

    /*
     * (추출) 테이블메타정보 조회후 추출테이블정보테이블에 저장후 정보 리턴
     * @param jdbcNm,owner,tabNm
     * */
    public Map<String, Object> selectMetaTabInfoList(TabInfo inVo){
        Map<String, Object> result = new HashMap<String, Object>();
        int rowCnt = 0;
        int commitCnt = 500;

        try{
            // 1단계 : DB 메타 테이블컬럼정보 조회
            List<TabInfo> tabInfoOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectMetaTabInfoList(inVo);
            logger.info("tabInfoOutVoList="+tabInfoOutVoList.size());

            // 2단계 : 메타테이블컬럼정보 삭제
            tabInfoDao.deleteMetaTabInfo(inVo);

            // 3단계 : 메타테이블컬럼정보 삽입
            List<TabInfo> tabInfoList = new ArrayList<TabInfo>();

            for (int i = 0; i  < tabInfoOutVoList.size(); i++) {
                //tabInfoDao.insertMetaTabInfo(tabInfoOutVoList.get(i));
                tabInfoList.add(tabInfoOutVoList.get(i));
                if(i%commitCnt == 0) {
                    tabInfoDao.insertMetaTabInfoBulk(tabInfoList);
                    tabInfoList.clear();
                     //rowCnt = 0;
                }
            }

            if(rowCnt < commitCnt) {
                logger.debug("rowCnt2=========="+rowCnt);
                tabInfoDao.insertMetaTabInfoBulk(tabInfoList);
            }

            result.put("isSuccess", true);
            result.put("tabInfoOutVoList", tabInfoOutVoList);
            //result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
    * (비교) 추출테이블정보테이블과 현재 테이블정보테이블과 비교한 정보를 리턴한다.
    */
    public Map<String, Object> selectCmpTabInfoList(TabInfo inVo) {

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            List<TabInfo> tabInfoOutVoList = tabInfoDao.selectCmpTabInfoList(inVo);;
            result.put("isSuccess", true);
            result.put("tabInfoOutVoList", tabInfoOutVoList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
    * (저장)
    */
    public Map<String, Object> saveCmpTabInfoList(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            tabInfoDao.insertCmpTabInfo(inVo);
            result.put("isSuccess", true);
            result.put("usrMsg", "입력 되었습니다");
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
        }

        return result;
    }

    /**
    * (삭제)
    */
    public Map<String, Object> deleteTabInfo(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            tabInfoDao.deleteTabInfo(inVo);
            result.put("isSuccess", true);
            result.put("usrMsg", "입력 되었습니다");
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
        }

        return result;
    }

    /**
    * (조회)
    */
    public Map<String, Object> selectTabInfoList(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            List<TabInfo> tabInfoOutVoList = tabInfoDao.selectTabInfoList(inVo);
            result.put("isSuccess", true);
            result.put("tabInfoOutVoList", tabInfoOutVoList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
    * (조회)
    */
    public Map<String, Object> selectTabList(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            List<TabInfo> tabInfoOutVoList = tabInfoDao.selectTabList(inVo);
            //System.out.println(tabInfoOutVoList.size());
            result.put("isSuccess", true);
            result.put("tabInfoOutVoList", tabInfoOutVoList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
    * (조회)
    */
    public Map<String, Object> selectColList(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            List<TabInfo> tabInfoOutVoList = tabInfoDao.selectColList(inVo);
            result.put("isSuccess", true);
            result.put("tabInfoOutVoList", tabInfoOutVoList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
    * (조회)
    */
    public Map<String, Object> selectCreateScript(List<TabInfo> inVo){
        System.out.println("selectCreateScript");
        System.out.println("inVo.size()="+inVo.size());

        Map<String, Object> result = new HashMap<String, Object>();
        String str = "";
        String pk = "";


        try{
            for (int i = 0; i < inVo.size(); i++) {
                if(inVo.get(i).chk){
                    inVo.get(i).setOrderBy("1");
                    List<TabInfo> tab = tabInfoDao.selectTabInfoList(inVo.get(i));
                    //System.out.println("outVo="+outVo.size());
                    str += "--DROP TABLE "+inVo.get(i).getOwner()+"."+inVo.get(i).getTabNm()+";\n";
                    str += "CREATE TABLE "+inVo.get(i).getOwner()+"."+inVo.get(i).getTabNm()+"\n";
                    str += "(\n";
                    pk  += " CONSTRAINT "+inVo.get(i).getTabNm()+"_PK PRIMARY KEY(";

                    for (int j = 0; j < tab.size(); j++) {
                        str += (j>0) ? "," : " ";
                        str += tab.get(j).colNm +" "+ tab.get(j).dataTypeDesc +" "+ tab.get(j).nullable+"\n";
                        //System.out.println("tabNm="+tabNm);
                        if(tab.get(j).pk.equals("Y")) pk += tab.get(j).colNm + ",";
                    }
                    pk += ")";pk = pk.replace(",)", ")");
                    str += pk+"\n";
                    str += ");\n";
                    pk = "";
                }
            }
            result.put("isSuccess", true);
            result.put("createScript", str);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
    * (조회)
    */
    public Map<String, Object> selectTabQryList(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            List<Map<String,Object>> tabQryOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectTabQryList(inVo);
            logger.info("tabInfoOutVoList="+tabQryOutVoList.size());
            //logger.info("tabInfoOutVoList="+tabQryOutVoList.get(0).get(0));

            result.put("isSuccess", true);
            result.put("tabQryOutVoList", tabQryOutVoList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }



    /*
     * (테스트) 커넥션 테스트
     * @param jdbcNm,owner,tabNm
     * */
    public Map<String, Object> testJdbcInfo(TabInfo inVo){
        Map<String, Object> result = new HashMap<String, Object>();

        try{
            List<TabInfo> tabInfoOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectMetaTabInfoList(inVo);
            result.put("isSuccess", true);
            result.put("tabInfoOutVoList", tabInfoOutVoList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

//    public Map<String, Object> selectInsertData(Map<String,String> params){
//
//        Map<String, Object> result = new HashMap<String, Object>();
//        try{
//            //TabDaoFactory tabInfoDaoFactory = (TabDaoFactory) Class.forName("net.pmosoft.pony.dams.table.dynamic.TabMariaDbDao").newInstance();
//            TabDaoFactory tabInfoDaoFactory = (TabDaoFactory) Class.forName( findDao(params) ).newInstance();
//            String list = tabInfoDaoFactory.selectInsertData(params);
//
//            result.put("isSuccess", true);
//            result.put("data", list);
//        } catch (Exception e){
//            result.put("isSuccess", false);
//            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
//            result.put("errSysMsg", e.getMessage());
//            //e.printStackTrace();
//        }
//        return result;
//    }
//
//    public Map<String, Object> selectTabData(Map<String,String> params){
//
//        Map<String, Object> result = new HashMap<String, Object>();
//        System.out.println("paramsaaaaaaaaaaaaaaaaaaaaaaaaaa="+params);
//
//        try{
//            //TabDaoFactory tabInfoDaoFactory = (TabDaoFactory) Class.forName("net.pmosoft.pony.dams.table.dynamic.TabMariaDbDao").newInstance();
//            TabDaoFactory tabInfoDaoFactory = (TabDaoFactory) Class.forName( findDao(params) ).newInstance();
//            List<Map<String,Object>> list = tabInfoDaoFactory.selectTabData(params);
//
//            result.put("isSuccess", true);
//            result.put("data", list);
//        } catch (Exception e){
//            result.put("isSuccess", false);
//            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
//            result.put("errSysMsg", e.getMessage());
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public Map<String, Object> selectQryData(Map<String,String> params){
//
//        Map<String, Object> result = new HashMap<String, Object>();
//
//        try{
//            //TabDaoFactory tabInfoDaoFactory = (TabDaoFactory) Class.forName("net.pmosoft.pony.dams.table.dynamic.TabMariaDbDao").newInstance();
//            TabDaoFactory tabInfoDaoFactory = (TabDaoFactory) Class.forName( findDao(params) ).newInstance();
//            List<Map<String,Object>> list = tabInfoDaoFactory.selectQryData(params);
//
//            result.put("isSuccess", true);
//            result.put("data", list);
//        } catch (Exception e){
//            result.put("isSuccess", false);
//            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
//            result.put("errSysMsg", e.getMessage());
//            //e.printStackTrace();
//        }
//        return result;
//    }
//
//    public Map<String, Object> selectCsvData(Map<String,String> params){
//
//        Map<String, Object> result = new HashMap<String, Object>();
//
//
//        try{
//            //TabDaoFactory tabInfoDaoFactory = (TabDaoFactory) Class.forName("net.pmosoft.pony.dams.table.dynamic.TabMariaDbDao").newInstance();
//            TabDaoFactory tabInfoDaoFactory = (TabDaoFactory) Class.forName( findDao(params) ).newInstance();
//            List<Map<String,Object>> list = tabInfoDaoFactory.selectCsvData(params);
//
//            result.put("isSuccess", true);
//            result.put("data", list);
//        } catch (Exception e){
//            result.put("isSuccess", false);
//            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
//            result.put("errSysMsg", e.getMessage());
//            //e.printStackTrace();
//        }
//        return result;
//    }
//
//
//    /**********************************************************************************
//    *
//    *                                   Excel Upload
//    *
//    **********************************************************************************/
//
//    public Map<String, Object> insertExcelTabColList(Map<String,String> params){
//
//
//        Map<String, Object> result = new HashMap<String, Object>();
//
//        try{
//
//            String data = params.get("data");
//            Gson gson = new Gson();
//            Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
//            List<Map<String,Object>> listParams  = gson.fromJson(data, type);
//
//            // 1.단계 : 메타테이블컬럼정보 삭제
//            tabInfoDao.deleteMetaTabCol(params);
//
//            // 2단계 : 메타테이블컬럼정보 삽입
//            for (int i = 0; i  < listParams.size(); i++) {
//                tabInfoDao.insertMetaTabCol(listParams.get(i));
//            }
//
//            // 3단계 : 메타테이블정보 삽입
//            tabInfoDao.deleteMetaTab(params);
//
//            for (int i = 0; i  < listParams.size(); i++) {
//                if  (tabInfoDao.selectMetaTabCnt(listParams.get(i))==0) {
//                    tabInfoDao.insertMetaTab(listParams.get(i));
//                }
//            }
//
//            result.put("isSuccess", true);
//            //result.put("data", list);
//        } catch (Exception e){
//            result.put("isSuccess", false);
//            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
//            result.put("errSysMsg", e.getMessage());
//            e.printStackTrace();
//        }
//        return result;
//    }
//


}
