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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

import net.pmosoft.pony.comm.App;
import net.pmosoft.pony.comm.util.ExcelUtil;
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

    public static void main(String[] args) {
        TabInfoSrv tabInfoSrv = new TabInfoSrv();
         tabInfoSrv.selectColScriptTest();
        //tabInfoSrv.selectTabQryListTest();

    }


    /**********************************************************************************
    *
    *                               JDBC Mybatis Session
    *
    **********************************************************************************/
    private SqlSession sqlSession(TabInfo inVo){
        JdbcInfo jdbcVo = new JdbcInfo();
        Properties props = new Properties();
        props.put("driver"      , "net.sf.log4jdbc.sql.jdbcapi.DriverSpy");

        if(inVo.jdbcInfo.getUrl() != null){
            props.put("url"         , inVo.jdbcInfo.getUrl()  );
            props.put("username"    , inVo.jdbcInfo.getUsrId());
            props.put("password"    , inVo.jdbcInfo.getUsrPw());
            props.put("mapper"      , "net/pmosoft/pony/dams/table/TabInfo"+StringUtil.replaceFirstCharUpperCase(inVo.jdbcInfo.getDriver())+"Dyn.xml");
        } else {
            JdbcInfo jdbcInfo = new JdbcInfo();;
            jdbcInfo.setJdbcNm(inVo.getJdbcNm());
            jdbcVo = jdbcInfoDao.selectJdbcInfo(jdbcInfo);
            //daoClassPath = (String) codeList.get(0).get("CD_PARAM1");
            props.put("url"         , jdbcVo.getUrl()  );
            props.put("username"    , jdbcVo.getUsrId());
            props.put("password"    , jdbcVo.getUsrPw());
            props.put("mapper"      , "net/pmosoft/pony/dams/table/TabInfo"+StringUtil.replaceFirstCharUpperCase(jdbcVo.getDriver())+"Dyn.xml");
        }

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
     * (반영) 신규-변경 테이블정보를 반영한다
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
     * (삭제) 테이블정보를 삭제한다
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
     * (조회) 추출되어 저장된 테이블정보를 조회한다.
     */
    public Map<String, Object> selectTabInfoList(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        System.out.println("inVo2====="+inVo.chkWhereTabs);
        System.out.println("inVo3====="+inVo.whereTabs);
        System.out.println(StringUtil.inParams(inVo.whereTabs));
        inVo.setWhereInTabs(StringUtil.inParams(inVo.whereTabs));
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
     * (조회) 테이블목록을 조회한다.
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
     * (조회) 컬럼목록을 조회한다.
     */
    public Map<String, Object> selectColList(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        System.out.println("inVo.whereTabs=="+inVo.whereTabs);
        //if(inVo.chkWhereTabs) inVo.whereTabs = "";

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

    /**********************************************************************************
    *
    *                                       유틸
    *
    **********************************************************************************/

    public void selectTabQryListTest() {
        TabInfo tabInfo = new TabInfo();
        tabInfo.jdbcInfo.setUrl("jdbc:log4jdbc:mariadb://pmosoft.net:3306/sttl");
        tabInfo.jdbcInfo.setUsrId("sttl");
        tabInfo.jdbcInfo.setUsrPw("s1234");
        tabInfo.jdbcInfo.setDriver("Mariadb");
        tabInfo.setOwner("sttl"); tabInfo.setTabNm("TSYUR00020");
        tabInfo.setChkSelect(true);tabInfo.setTxtSelect("Select *");
        Map<String, Object> map = selectTabQryList(tabInfo);
        //System.out.println(map.get("sqlScript"));
    }


    /*
     * 동적 쿼리문 결과 리턴
     */
    public String getCommonQry(TabInfo inVo){
        String qry = "";
        if(inVo.isChkSelect() && !inVo.isChkWhere()) {
            qry += inVo.txtSelect;
            qry += " FROM " + inVo.getOwner()+"."+inVo.getTabNm()+"\n";
        } else if(inVo.isChkSelect() && inVo.isChkWhere()) {
            qry += inVo.txtSelect;
            qry += " FROM " + inVo.getOwner()+"."+inVo.getTabNm()+"\n";
            qry += inVo.getTxtWhere();
        } else if(!inVo.isChkSelect() && inVo.isChkWhere()) {
            qry += "SELECT * FROM " + inVo.getOwner()+"."+inVo.getTabNm()+"\n";
            qry += inVo.getTxtWhere();
        } else {
            qry += "SELECT * FROM " + inVo.getOwner()+"."+inVo.getTabNm();
        }
        inVo.setQry(qry);

        return qry;
    }

    /*
     * 테이블 쿼리 데이터 리턴
     */
    public Map<String, Object> selectTabQryList(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();
        try{
            inVo.setQry(getCommonQry(inVo));
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
     * Create Script 생성
     */
    public Map<String, Object> selectCreateScript(List<TabInfo> inVo){
        System.out.println("selectCreateScript");
        System.out.println("inVo.size()="+inVo.size());

        Map<String, Object> result = new HashMap<String, Object>();
        String str = "";
        String pk = "";
        boolean isPk = false;


        try{
            for (int i = 0; i < inVo.size(); i++) {
                if(inVo.get(i).chk){
                    inVo.get(i).setOrderBy("1");
                    List<TabInfo> tab = sqlSession(inVo.get(i)).getMapper(TabInfoDao.class).selectMetaTabInfoList(inVo.get(i));
                    //System.out.println("outVo="+outVo.size());
                    str += "--DROP TABLE "+inVo.get(i).getOwner()+"."+inVo.get(i).getTabNm()+";\n";
                    str += "CREATE TABLE "+inVo.get(i).getOwner()+"."+inVo.get(i).getTabNm()+"\n";
                    str += "(\n";
                    pk  += ",CONSTRAINT "+inVo.get(i).getTabNm()+"_PK PRIMARY KEY(";

                    for (int j = 0; j < tab.size(); j++) {
                        str += (j>0) ? "," : " ";
                        str += tab.get(j).colNm +" "+ tab.get(j).dataTypeDesc +" "+ tab.get(j).nullable+"\n";
                        //System.out.println("tabNm="+tabNm);
                        if(tab.get(j).pk.equals("Y")) {pk += tab.get(j).colNm + ",";isPk=true;}
                    }
                    pk += ")";pk = pk.replace(",)", ")");
                    System.out.println("isPk="+isPk);
                    if(isPk) str += pk+"\n";
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

    public void selectColScriptTest() {
        TabInfo tabInfo = new TabInfo();
        tabInfo.jdbcInfo.setUrl("jdbc:log4jdbc:mariadb://pmosoft.net:3306/sttl");
        tabInfo.jdbcInfo.setUsrId("sttl");
        tabInfo.jdbcInfo.setUsrPw("s1234");
        tabInfo.jdbcInfo.setDriver("Mariadb");
        tabInfo.setOwner("sttl"); tabInfo.setTabNm("TSYUR00010");
        Map<String, Object> map = selectColScript(tabInfo);
        System.out.println(map.get("sqlScript"));
    }


    /*
     * Select Script 생성 (SELECT)
     */
    public Map<String, Object> selectColScript(TabInfo inVo){
        Map<String, Object> result = new HashMap<String, Object>();
        result = selectSelectScript(inVo,1);
        return result;
    }

    /*
     * Select Script 생성 (SELECT + FROM)
     */
    public Map<String, Object> selectColFromScript(TabInfo inVo){
        Map<String, Object> result = new HashMap<String, Object>();
        result = selectSelectScript(inVo,2);
        return result;
    }


    /*
     * Select Script 생성 (SELECT + FROM + WHERE)
     */
    public Map<String, Object> selectColFromWhereScript(TabInfo inVo){
        Map<String, Object> result = new HashMap<String, Object>();
        result = selectSelectScript(inVo,3);
        return result;
    }

    /*
     * Select Script 메인 모듈
     */
    public Map<String, Object> selectSelectScript(TabInfo inVo, int cd){

        Map<String, Object> result = new HashMap<String, Object>();
        String str = "";
        String pk = "";
        String pkCol = "";
        boolean isPk = false;
        int maxColLen = 0;
        try{
            List<TabInfo> tab = sqlSession(inVo).getMapper(TabInfoDao.class).selectMetaTabInfoList(inVo);
            str += "SELECT \n";


            for (int i = 0; i < tab.size(); i++) {
                if(maxColLen <= tab.get(i).colNm.length()) maxColLen = tab.get(i).colNm.length();
            }

            System.out.println(maxColLen);
            for (int i = 0; i < tab.size(); i++) {
                str += (i>0) ? "     , " : "       ";
                System.out.println(tab.get(i).colNm+"="+tab.get(i).colNm.length() );
                //System.out.println(StringUtil.padRight("a",maxColLen-tab.get(i).colNm.length()));
                str += tab.get(i).colNm + StringUtil.padRight(" ",maxColLen-tab.get(i).colNm.length()) + "    -- "+ tab.get(i).colHnm+"\n";
                //System.out.println("tabNm="+tabNm);
                if(tab.get(i).pk.equals("Y")) { pkCol += "AND "+tab.get(i).colNm + " = ''\n"; }
            }
            if(cd==2||cd==3) {
                str += "FROM   "+inVo.owner.toUpperCase()+"."+inVo.tabNm+"\n";
            }
            if(cd==3) {
                str += "WHERE 1=1 "+"\n";
                str += pkCol+"\n";
            }

            result.put("isSuccess", true);
            result.put("sqlScript", str);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }



    /*
    * 다운로드 Insert 문장
    * */
    public Map<String, Object> downloadInsStat(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();
        String fileNm = "insertStat.sql";
        try{
            int rowCnt = sqlSession(inVo).getMapper(TabInfoDao.class).selectDataCnt(inVo);
            if(rowCnt <= 10000) {
                String str = "";
                String s01 = "INSERT INTO "+inVo.getOwner()+"."+inVo.getTabNm()+" VALUES (";
                String s02 = "";
                PrintWriter writer = null;
                writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(App.excelPath+fileNm)));
                List<TabInfo> tabInfoOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectMetaTabInfoList(inVo);
                inVo.setQry(getCommonQry(inVo));
                List<Map<String,Object>> insStatOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectTabQryList(inVo);
                logger.info("tabInfoOutVoList="+insStatOutVoList.size());
                for (int i = 0; i < insStatOutVoList.size(); i++) {
                    for (int j = 0; j < tabInfoOutVoList.size(); j++) {
                        if(tabInfoOutVoList.get(j).getDataTypeNm().trim().toUpperCase().matches("NUMBER|INT|NUMERIC")||
                           insStatOutVoList.get(i).get(tabInfoOutVoList.get(j).getColNm())==null
                          )
                          s02 += insStatOutVoList.get(i).get(tabInfoOutVoList.get(j).getColNm()) + ",";
                        else
                          s02 += "'"+insStatOutVoList.get(i).get(tabInfoOutVoList.get(j).getColNm()) + "',";
                    }
                    str = s01 + s02 + ");"; str = str.replace(",);",");");
                    writer.println(str);
                    str = "";s02 = "";
                }
                writer.close();
                Runtime run = Runtime.getRuntime ();
                run.exec ("cmd /c start notepad.exe "+App.excelPath+fileNm);
                result.put("isSuccess", true);
            } else {
                result.put("isSuccess", false);
                result.put("errUsrMsg", "10,000건이하만 Insert 문장으로 출력됩니다. "+rowCnt+"건 입니다. 파일로 출력하시기 바랍니다.");
            }
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /*
    * 다운로드 Excel로 Data 출력
    * */
    public Map<String, Object> downloadExcel(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();
        String fileNm = "insertStat.xls";
        try{
            int rowCnt = sqlSession(inVo).getMapper(TabInfoDao.class).selectDataCnt(inVo);
            if(rowCnt <= 65000) {
                List<Map<String,Object>> insStatOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectInsStat(inVo);
                logger.info("tabInfoOutVoList="+insStatOutVoList.size());
                ExcelUtil excelDown = new ExcelUtil();
                excelDown.downListToExcel2(insStatOutVoList,App.excelPath+fileNm);
                Runtime run = Runtime.getRuntime ();
                run.exec ("cmd /c start excel.exe "+App.excelPath+fileNm);
                result.put("isSuccess", true);
            } else {
                result.put("isSuccess", false);
                result.put("errUsrMsg", "65,000건이하만 엑셀로 출력됩니다. 건수가 "+rowCnt+"입니다. 파일로 출력하시기 바랍니다.");
            }
            //result.put("tabQryOutVoList", tabQryOutVoList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /*
    * 다운로드 CSV(Comma) Data 출력
    * */
    public Map<String, Object> downloadCommaFile(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();
        String fileNm = "csv.dat";
        try{
            int rowCnt = sqlSession(inVo).getMapper(TabInfoDao.class).selectDataCnt(inVo);
            if(rowCnt <= 1000000) {
                String str = "";
                PrintWriter writer = null;
                writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(App.excelPath+fileNm)));
                List<TabInfo> tabInfoOutVoList = tabInfoDao.selectTabInfoList(inVo);
                List<Map<String,Object>> insStatOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectInsStat(inVo);
                logger.info("tabInfoOutVoList="+insStatOutVoList.size());
                for (int i = 0; i < insStatOutVoList.size(); i++) {
                    for (int j = 0; j < insStatOutVoList.get(i).size(); j++) {
                        str += insStatOutVoList.get(i).get(tabInfoOutVoList.get(j).getColNm())+ ",";
                    }
                    str = str.replaceAll(",$","");
                    writer.println(str);
                    str = "";
                    if(i%1000==0) logger.info("처리건수="+i);
                }
                writer.close();
                logger.info("처리완료");
                Runtime run = Runtime.getRuntime ();
                run.exec ("cmd /c start notepad.exe "+App.excelPath+fileNm);
                result.put("isSuccess", true);
            } else {
                result.put("isSuccess", false);
                result.put("errUsrMsg", "1,000,000건이하만 출력됩니다. "+rowCnt+"건 입니다.");
            }
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    /*
    * 다운로드 수직바 Data 출력
    * */
    public Map<String, Object> downloadBarFile(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();
        String fileNm = "csv.dat";
        try{
            int rowCnt = sqlSession(inVo).getMapper(TabInfoDao.class).selectDataCnt(inVo);
            if(rowCnt <= 1000000) {
                String str = "";
                PrintWriter writer = null;
                writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(App.excelPath+fileNm)));
                List<TabInfo> tabInfoOutVoList = tabInfoDao.selectTabInfoList(inVo);
                List<Map<String,Object>> insStatOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectInsStat(inVo);
                logger.info("tabInfoOutVoList="+insStatOutVoList.size());
                for (int i = 0; i < insStatOutVoList.size(); i++) {
                    for (int j = 0; j < insStatOutVoList.get(i).size(); j++) {
                        str += insStatOutVoList.get(i).get(tabInfoOutVoList.get(j).getColNm())+ "|";
                    }
                    str = str.replaceAll("\\|$","");
                    writer.println(str);
                    str = "";
                }
                writer.close();
                Runtime run = Runtime.getRuntime ();
                run.exec ("cmd /c start notepad.exe "+App.excelPath+fileNm);
                result.put("isSuccess", true);
            } else {
                result.put("isSuccess", false);
                result.put("errUsrMsg", "1,000,000건이하만 출력됩니다. "+rowCnt+"건 입니다.");
            }
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


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
