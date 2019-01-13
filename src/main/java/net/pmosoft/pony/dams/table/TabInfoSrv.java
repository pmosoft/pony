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
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.pmosoft.pony.comm.util.StringUtil;
import net.pmosoft.pony.dams.jdbc.JdbcInfo;
import net.pmosoft.pony.dams.jdbc.JdbcInfoDao;
import net.pmosoft.pony.dams.table.dynamic.TabDaoFactory;

@Service
public class TabInfoSrv {

    @Autowired
    private TabInfoDao tabDao;

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

        JdbcInfo jdbcInfo = new JdbcInfo(); jdbcInfo.setJdbcNm(inVo.getCondJdbcNm());
        JdbcInfo jdbcVo = jdbcInfoDao.selectJdbcInfo(jdbcInfo);
        //daoClassPath = (String) codeList.get(0).get("CD_PARAM1");

        String driver = "";
        if     (jdbcVo.getDriver().equals("oracle" )) driver = "oracle.jdbc.driver.OracleDriver";
        else if(jdbcVo.getDriver().equals("mariadb")) driver = "org.mariadb.jdbc.Driver";
        else if(jdbcVo.getDriver().equals("sqlite" )) driver = "org.sqlite.JDBC";

        Properties props = new Properties();
        props.put("driver"      , driver         );
        props.put("url"         , jdbcVo.getUrl()  );
        props.put("username"    , jdbcVo.getUsrId());
        props.put("password"    , jdbcVo.getUsrPw());
        props.put("mapper"      , "net/pmosoft/pony/dams/table/TabInfo"+StringUtil.replaceFirstCharUpperCase(jdbcVo.getDriver())+"Dao.xml");

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
    *                                    Meta
    *
    **********************************************************************************/
    public Map<String, Object> selectMetaTabInfoList(TabInfo inVo){
        Map<String, Object> result = new HashMap<String, Object>();

        try{
            List<TabInfo> tabInfoOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectMetaTabInfoList(inVo);
            System.out.println("tabInfoOutVoList="+tabInfoOutVoList.get(1).getColNm());

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

    public Map<String, Object> selectMetaTabList(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            //TabDaoFactory tabDaoFactory = (TabDaoFactory) Class.forName("net.pmosoft.pony.dams.table.dynamic.TabMariaDbDao").newInstance();
            TabDaoFactory tabDaoFactory = (TabDaoFactory) Class.forName( findDao(params) ).newInstance();
            List<Map<String,Object>> list = tabDaoFactory.selectMetaTabList(params);

            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


    public Map<String, Object> selectInsertData(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();
        try{
            //TabDaoFactory tabDaoFactory = (TabDaoFactory) Class.forName("net.pmosoft.pony.dams.table.dynamic.TabMariaDbDao").newInstance();
            TabDaoFactory tabDaoFactory = (TabDaoFactory) Class.forName( findDao(params) ).newInstance();
            String list = tabDaoFactory.selectInsertData(params);

            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            //e.printStackTrace();
        }
        return result;
    }

    /**********************************************************************************
    *
    *                               ExtractMetaTabCol
    *
    **********************************************************************************/
    public Map<String, Object> selectExtractMetaTabColList(Map<String,String> params){

        // params.put("CD_ID_NM", "DB_CONN_CD");
        // params.put("CD", "01");

        Map<String, Object> result = new HashMap<String, Object>();


//        List<Map<String,Object>> codeList = codeDao.selectCodeExt(params);;
//        String dbDriver   = (String) codeList.get(0).get("CD_PARAM2");
//        String dbConn     = (String) codeList.get(0).get("CD_PARAM3");
//        String dbUser     = (String) codeList.get(0).get("CD_PARAM4");
//        String dbPassword = (String) codeList.get(0).get("CD_PARAM5");
//        //conn.getConnection(dbDriver, dbConn, dbUser, dbPassword);

        try{

            List<Map<String,Object>> list = null;

            // 1.1단계 : DB 메타 테이블컬럼정보 조회
            //TabDaoFactory tabDaoFactory = (TabDaoFactory) Class.forName("net.pmosoft.pony.dams.table.dynamic.TabMariaDbDao").newInstance();
            TabDaoFactory tabDaoFactory = (TabDaoFactory) Class.forName( findDao(params) ).newInstance();
            List<Map<String,Object>> listMeta = tabDaoFactory.selectMetaTabColList(params);

            // 1.2단계 : 메타테이블컬럼정보 삭제
            tabDao.deleteMetaTabCol(params);


            // 1.3단계 : 메타테이블컬럼정보 삽입
            for (int i = 0; i  < listMeta.size(); i++) {
                tabDao.insertMetaTabCol(listMeta.get(i));
            }

            // 2.1단계 : 메타테이블정보 삭제
            tabDao.deleteMetaTab(params);

            // 2.2단계 : 메타테이블정보 조회
            List<Map<String,Object>> listMeta2 = tabDaoFactory.selectMetaTabList(params);

            // 2.3단계 : 메타테이블정보 삽입
            for (int i = 0; i  < listMeta2.size(); i++) {
                tabDao.insertMetaTab(listMeta2.get(i));
            }

            // 3단계 : 메타테이블컬럼정보 조회
            //list = tabDao.selectMetaTabColList(params);

            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

//  public Map<String, Object> selectMetaTabColList(Map<String,String> params){
//
//      Map<String, Object> result = new HashMap<String, Object>();
//
//      try {
//          String step = params.get("step");
//          List<Map<String,Object>> list = null;
//
//          if(step.equals("1")) {
//              tabDao.deleteMetaTabCol(params);
//              tabDao.insertMetaTabColList(params);
//              list = tabDao.selectMetaTabColList(params);;
//          } else if(step.equals("2")){
//              list = tabDao.selectCmpTabColList(params);;
//          }
//          result.put("isSuccess", true);
//          result.put("data", list);
//      } catch (Exception e){
//          result.put("isSuccess", false);
//          result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
//          result.put("errSysMsg", e.getMessage());
//          e.printStackTrace();
//      }
//      return result;
//  }

    private String findDao(Map<String,String> params){

        String daoClassPath = "";
        Map<String,String> params2 = new HashMap<String,String>();

        params2.put("CD_ID_NM","DYN_DAO_CD");
        params2.put("CD_NM",params.get("dbType"));
//        List<Map<String,Object>> codeList = codeDao.selectCodeExt(params2);
//        daoClassPath = (String) codeList.get(0).get("CD_PARAM1");

        return daoClassPath;
    }

    public Map<String, Object> selectCmpTabColList(@RequestParam Map<String,String> params) {

        Map<String, Object> result = new HashMap<String, Object>();

        System.out.println("params="+params);
        try{
            List<Map<String,Object>> list = tabDao.selectCmpTabColList(params);;
            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> insertCmpTabColList(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            tabDao.insertCmpTabCol(params);
            tabDao.insertCmpTab(params);

            result.put("isSuccess", true);
            result.put("usrMsg", "입력 되었습니다");
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
        }

        return result;
    }

    /**********************************************************************************
    *
    *                                  Tab
    *
    **********************************************************************************/

    public Map<String, Object> selectTabColList(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            List<Map<String,Object>> list = tabDao.selectTabColList(params);;
            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> saveTabCol(String params){

        Map<String, Object> result = new HashMap<String, Object>();
        return result;

    }

    public Map<String, Object> deleteTabCol(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            String data = params.get("data");
            Gson gson = new Gson();
            Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
            List<Map<String,String>> listParams  = gson.fromJson(data, type);

            Map<String, String> errors = new HashMap<String, String>();
            errors = tabValidatorSrv.validateDeleteTabCol(listParams);
            if(errors.size()>0){
                result.put("isSuccess", false);
                result.put("errUsrMsg", errors.get("errUsrMsg"));
            } else {
                for (int i = 0; i < listParams.size(); i++) {
                    tabDao.deleteTabCol(listParams.get(i));
                }
                result.put("isSuccess", true);
                result.put("usrMsg", "삭제 되었습니다.");
            }
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> selectTabList(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            List<Map<String,Object>> list = tabDao.selectTabList(params);;
            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> saveTab(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            String data = params.get("data");
            Gson gson = new Gson();
            Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
            List<Map<String,String>> listParams  = gson.fromJson(data, type);

            Map<String, String> errors = new HashMap<String, String>();
            errors = tabValidatorSrv.validateSaveTab(params);
            if(errors.size()>0){
                result.put("isSuccess", false);
                result.put("errUsrMsg", errors.get("errUsrMsg"));
            } else {
                for (int i = 0; i  < listParams.size(); i++) {
                    if  (tabDao.selectTabCnt(params)==0) {
                        tabDao.insertTab(params);
                    } else {
                        tabDao.updateTab(params);
                    }
                }
                result.put("isSuccess", true);
                result.put("usrMsg", "저장 되었습니다");
            }
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생되었습니다.");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> deleteTab(Map<String,String> params){
        Map<String, Object> result = new HashMap<String, Object>();
        return result;
    }

    public Map<String, Object> selectTabData(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();
        System.out.println("paramsaaaaaaaaaaaaaaaaaaaaaaaaaa="+params);

        try{
            //TabDaoFactory tabDaoFactory = (TabDaoFactory) Class.forName("net.pmosoft.pony.dams.table.dynamic.TabMariaDbDao").newInstance();
            TabDaoFactory tabDaoFactory = (TabDaoFactory) Class.forName( findDao(params) ).newInstance();
            List<Map<String,Object>> list = tabDaoFactory.selectTabData(params);

            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> selectQryData(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            //TabDaoFactory tabDaoFactory = (TabDaoFactory) Class.forName("net.pmosoft.pony.dams.table.dynamic.TabMariaDbDao").newInstance();
            TabDaoFactory tabDaoFactory = (TabDaoFactory) Class.forName( findDao(params) ).newInstance();
            List<Map<String,Object>> list = tabDaoFactory.selectQryData(params);

            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            //e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> selectCsvData(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();


        try{
            //TabDaoFactory tabDaoFactory = (TabDaoFactory) Class.forName("net.pmosoft.pony.dams.table.dynamic.TabMariaDbDao").newInstance();
            TabDaoFactory tabDaoFactory = (TabDaoFactory) Class.forName( findDao(params) ).newInstance();
            List<Map<String,Object>> list = tabDaoFactory.selectCsvData(params);

            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            //e.printStackTrace();
        }
        return result;
    }


    /**********************************************************************************
    *
    *                                   Excel Upload
    *
    **********************************************************************************/

    public Map<String, Object> insertExcelTabColList(Map<String,String> params){


        Map<String, Object> result = new HashMap<String, Object>();

        try{

            String data = params.get("data");
            Gson gson = new Gson();
            Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
            List<Map<String,Object>> listParams  = gson.fromJson(data, type);

            // 1.단계 : 메타테이블컬럼정보 삭제
            tabDao.deleteMetaTabCol(params);

            // 2단계 : 메타테이블컬럼정보 삽입
            for (int i = 0; i  < listParams.size(); i++) {
                tabDao.insertMetaTabCol(listParams.get(i));
            }

            // 3단계 : 메타테이블정보 삽입
            tabDao.deleteMetaTab(params);

            for (int i = 0; i  < listParams.size(); i++) {
                if  (tabDao.selectMetaTabCnt(listParams.get(i))==0) {
                    tabDao.insertMetaTab(listParams.get(i));
                }
            }

            result.put("isSuccess", true);
            //result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }



}
