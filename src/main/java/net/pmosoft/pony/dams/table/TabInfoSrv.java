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

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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


    /**********************************************************************************
    *
    *                               JDBC_Mybatis_SqlSession
    *
    **********************************************************************************/
    public void ________JDBC_Mybatis_SqlSession________(){}

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
    *                               Pony테이블모듈_로컬DB
    *
    **********************************************************************************/
    public void __________Pony테이블모듈_로컬DB__________(){}

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
        inVo.setWhereInTabs(StringUtil.inParams(inVo.whereTabs));
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
    public void _______________유틸_______________(){}
    
    /*
     * SELECT 문장 생성
     */
    public Map<String, Object> selectSelectScript(TabInfo inVo){
        
        logger.info("selectSelectScript");
        

        Map<String, Object> result = new HashMap<String, Object>();
        String qry = "";
        String cntQry = "SELECT COUNT(*) ";

        String cols = "";
        int maxColLen = 0;
        String pkCol = "";

        String where = "";
        
        try{
            List<TabInfo> tab = sqlSession(inVo).getMapper(TabInfoDao.class).selectMetaTabInfoList(inVo);
            
            //////////////////////////////////////////////////
            qry += "SELECT \n";
            //////////////////////////////////////////////////

            // SELECT 컬럼들중 길이가 가장 큰 컬럼의 길이를 산출한다            
            for (int i = 0; i < tab.size(); i++) {
                if(maxColLen <= tab.get(i).getColNm().length()) maxColLen = tab.get(i).getColNm().length();
            }

            // SELECT 컬럼들의 폭을 균등하게 맞추고 주석을 생성한다.
            logger.debug("maxColLen="+maxColLen);
            for (int i = 0; i < tab.size(); i++) {
                
                // 컬럼 정보 생성
                cols += (i>0) ? "     , " : "       ";
                logger.debug(tab.get(i).getColNm()+"="+tab.get(i).getColNm().length() );
                //System.out.println(StringUtil.padRight("a",maxColLen-tab.get(i).colNm.length()));
                cols += tab.get(i).getColNm() + StringUtil.padRight(" ",maxColLen-tab.get(i).getColNm().length()) + "    -- "+ tab.get(i).getColHnm()+"\n";
                //System.out.println("tabNm="+tabNm);
                
                // PK컬럼 조건 정보 생성
                if(tab.get(i).getPk().equals("Y")) { pkCol += "AND "+tab.get(i).getColNm() + " LIKE '%'\n"; }
            }

            ////////////////////////////////////////////////////////////////
            qry    += inVo.isChkSelect() ? inVo.getTxtSelect() +"\n" : cols;
            ////////////////////////////////////////////////////////////////

            ///////////////////////////////////////////////////////////////////////////
            qry    += "FROM   "+inVo.getOwner().toUpperCase()+"."+inVo.getTabNm()+"\n";
            cntQry += "FROM   "+inVo.getOwner().toUpperCase()+"."+inVo.getTabNm()+"\n";
            ///////////////////////////////////////////////////////////////////////////
            
            ///////////////////////////////////////////////////////////////////////////
            where = "WHERE 1=1 "+"\n" + pkCol;
            qry    += inVo.isChkWhere() ? inVo.getTxtWhere() +"\n" : where;
            cntQry += inVo.isChkWhere() ? inVo.getTxtWhere() +"\n" : "";
            ///////////////////////////////////////////////////////////////////////////

            result.put("isSuccess", true);
            result.put("sqlScript", qry);
            result.put("cntSqlScript", cntQry);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    
    /*
     * 테이블 생성 스크립드
     */
    public Map<String, Object> selectCreateScript(List<TabInfo> inVo){
        logger.info("selectCreateScript");
        //System.out.println("inVo.size()="+inVo.size());

        Map<String, Object> result = new HashMap<String, Object>();
        String qry = "";
        String pk = "";
        boolean isPk = false;

        try{
            for (int i = 0; i < inVo.size(); i++) {
                if(inVo.get(i).chk){
                    inVo.get(i).setOrderBy("1");
                    List<TabInfo> tab = sqlSession(inVo.get(i)).getMapper(TabInfoDao.class).selectMetaTabInfoList(inVo.get(i));
                    //System.out.println("outVo="+outVo.size());
                    qry += "--DROP TABLE "+inVo.get(i).getOwner()+"."+inVo.get(i).getTabNm()+";\n";
                    qry += "CREATE TABLE "+inVo.get(i).getOwner()+"."+inVo.get(i).getTabNm()+"\n";
                    qry += "(\n";
                    pk  += ",CONSTRAINT "+inVo.get(i).getTabNm()+"_PK PRIMARY KEY(";

                    for (int j = 0; j < tab.size(); j++) {
                        qry += (j>0) ? "," : " ";
                        qry += tab.get(j).colNm +" "+ tab.get(j).dataTypeDesc +" "+ tab.get(j).nullable+"\n";
                        //System.out.println("tabNm="+tabNm);
                        if(tab.get(j).pk.equals("Y")) {pk += tab.get(j).colNm + ",";isPk=true;}
                    }
                    pk += ")";pk = pk.replace(",)", ")");
                    //System.out.println("isPk="+isPk);
                    if(isPk) qry += pk+"\n";
                    qry += ");\n";
                    pk = "";
                }
            }
            result.put("isSuccess", true);
            result.put("createScript", qry);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    
    /*
     * 테이블 쿼리 데이터 생성
     */
    public Map<String, Object> selectTabQryList(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();
        try{
            
            inVo.setQry(selectSelectScript(inVo).get("sqlScript").toString());
            //inVo.setCntQry(selectSelectScript(inVo).get("cntSqlScript").toString());
                        
            //int rowCnt = sqlSession(inVo).getMapper(TabInfoDao.class).selectDataCnt(inVo);
            //if(rowCnt <= inVo.getLimitCnt()) {
                List<Map<String,Object>> tabQryOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectCommonQryList(inVo);
                logger.info("tabQryOutVoList="+tabQryOutVoList.size());
                result.put("isSuccess", true);
                result.put("tabQryOutVoList", tabQryOutVoList);
            //} else {
            //    result.put("isSuccess", false);
            //    result.put("errUsrMsg", "최대 처리 허용 데이터 건수는 "+inVo.getLimitCnt()+"건 이지만 "+rowCnt+"건이 조회되었습니다");
            //}

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
                String qry = "";
                String s01 = "INSERT INTO "+inVo.getOwner()+"."+inVo.getTabNm()+" VALUES (";
                String s02 = "";
                PrintWriter writer = null;
                writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(App.excelPath+fileNm)));
                List<TabInfo> tabInfoOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectMetaTabInfoList(inVo);
                inVo.setQry(selectSelectScript(inVo).get("sqlScript").toString());
                List<Map<String,Object>> insStatOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectCommonQryList(inVo);
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
                    qry = s01 + s02 + ");"; qry = qry.replace(",);",");");
                    writer.println(qry);
                    
                    qry = "";s02 = "";
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
                List<Map<String,Object>> insStatOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectCommonQryList(inVo);
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
                String qry = "";
                PrintWriter writer = null;
                writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(App.excelPath+fileNm)));
                List<TabInfo> tabInfoOutVoList = tabInfoDao.selectTabInfoList(inVo);
                List<Map<String,Object>> insStatOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectCommonQryList(inVo);
                logger.info("tabInfoOutVoList="+insStatOutVoList.size());
                for (int i = 0; i < insStatOutVoList.size(); i++) {
                    for (int j = 0; j < insStatOutVoList.get(i).size(); j++) {
                        qry += insStatOutVoList.get(i).get(tabInfoOutVoList.get(j).getColNm())+ ",";
                    }
                    qry = qry.replaceAll(",$","");
                    writer.println(qry);
                    qry = "";
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
                String qry = "";
                PrintWriter writer = null;
                writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(App.excelPath+fileNm)));
                List<TabInfo> tabInfoOutVoList = tabInfoDao.selectTabInfoList(inVo);
                List<Map<String,Object>> insStatOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectCommonQryList(inVo);
                logger.info("tabInfoOutVoList="+insStatOutVoList.size());
                for (int i = 0; i < insStatOutVoList.size(); i++) {
                    for (int j = 0; j < insStatOutVoList.get(i).size(); j++) {
                        qry += insStatOutVoList.get(i).get(tabInfoOutVoList.get(j).getColNm())+ "|";
                    }
                    qry = qry.replaceAll("\\|$","");
                    writer.println(qry);
                    qry = "";
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
}
