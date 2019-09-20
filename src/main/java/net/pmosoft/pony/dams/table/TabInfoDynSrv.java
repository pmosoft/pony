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
import net.pmosoft.pony.comm.util.FileUtil;
import net.pmosoft.pony.comm.util.StringUtil;
import net.pmosoft.pony.dams.jdbc.JdbcInfo;
import net.pmosoft.pony.dams.jdbc.JdbcInfoDao;

@Service
public class TabInfoDynSrv {

    private static Logger logger = LoggerFactory.getLogger(TabInfoDynSrv.class);

    @Autowired
    private TabInfoDao tabInfoDao;

    @Autowired
    private JdbcInfoDao jdbcInfoDao;

    @Autowired
    private TabInfoSrv tabInfoSrv;
    
    
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
        
        if(inVo.jdbcInfo.getUrl().length() > 0){
            props.put("url"         , inVo.jdbcInfo.getUrl()  );
            props.put("username"    , inVo.jdbcInfo.getUsrId());
            props.put("password"    , inVo.jdbcInfo.getUsrPw());
            props.put("mapper"      , "net/pmosoft/pony/dams/table/TabInfo"+StringUtil.replaceFirstCharUpperCase(inVo.jdbcInfo.getDb())+"Dyn.xml");
            
        } else {
            jdbcVo = getJdbcInfo(inVo.getJdbcNm());
            props.put("url"         , jdbcVo.getUrl()  );
            props.put("username"    , jdbcVo.getUsrId());
            props.put("password"    , jdbcVo.getUsrPw());
            props.put("mapper"      , "net/pmosoft/pony/dams/table/TabInfo"+StringUtil.replaceFirstCharUpperCase(jdbcVo.getDb())+"Dyn.xml");
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

    /*
     * jdbcNm으로 JdbcInfo 리턴
     * */
    public JdbcInfo getJdbcInfo(String jdbcNm){
        JdbcInfo jdbcInfo = new JdbcInfo();
        jdbcInfo.setJdbcNm(jdbcNm);
        return jdbcInfoDao.selectJdbcInfo(jdbcInfo);
    }
    
    /**********************************************************************************
    *
    *                               해당DB메타데이터추출
    *
    **********************************************************************************/
    public void ____________해당DB메타데이터추출_____________(){}
    
    /*
     * 해당DB의 테이블메타정보 조회
     * */
    public Map<String, Object> selectMetaTabInfoList(TabInfo inVo){
        Map<String, Object> result = new HashMap<String, Object>();

        try{
            List<TabInfo> tabInfoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectMetaTabInfoList(inVo);
            result.put("isSuccess", true);
            result.put("tabInfoList", tabInfoList);
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
    *                               SQL스크립트생성
    *
    **********************************************************************************/
    public void ____________SQL스크립트생성_____________(){}
    
    /*
     * SELECT 문장 생성
     */
    public Map<String, Object> selectSelectScript(TabInfo inVo){
        
        logger.info("selectSelectScript");
        

        Map<String, Object> result = new HashMap<String, Object>();
        String qry = "";
        String cntQry = "SELECT COUNT(*) ";

        String db = "";
        String colNm = "";
        String cols = "";
        int maxColLen = 0;
        String pkCol = "";

        String where = "";
        
        try{
            List<TabInfo> tab = sqlSession(inVo).getMapper(TabInfoDao.class).selectMetaTabInfoList(inVo);
            
            //////////////////////////////////////////////////
            qry += "SELECT \n";
            //////////////////////////////////////////////////

            /// Columns Start /////////////////////////////////////////////////////////
            // SELECT 컬럼들중 길이가 가장 큰 컬럼의 길이를 산출한다            
            for (int i = 0; i < tab.size(); i++) {
                if(maxColLen <= tab.get(i).getColNm().length()) maxColLen = tab.get(i).getColNm().length();
            }

            // SELECT 컬럼들의 폭을 균등하게 맞추고 주석을 생성한다.
            logger.debug("maxColLen="+maxColLen);
            for (int i = 0; i < tab.size(); i++) {
                colNm = tab.get(i).getColNm();
                db = inVo.getJdbcInfo().getDb().toUpperCase();
                
                // 데이트 타입 변형조건일 경우 DBMS의 SQL규칙에 맞게 형변환 처리
                if (tab.get(i).isChgDate()||tab.get(i).getDataTypeNm().trim().toUpperCase().matches("DATE|TIMESTAMP")) {
                    if     (db.equals("ORACLE")) {colNm = "TO_CHAR("+colNm+",'YYYY-MM-DD HH24:MI:SS') AS "+colNm;}
                    else if(db.equals("ORACLE")) {colNm = "TO_CHAR("+colNm+",'YYYY-MM-DD HH24:MI:SS') AS "+colNm;}
                }
                
                // 컬럼 정보 생성
                cols += (i>0) ? "     , " : "       ";
                logger.debug(colNm+"="+colNm.length() );
                //System.out.println(StringUtil.padRight("a",maxColLen-tab.get(i).colNm.length()));
                cols += colNm + StringUtil.padRight(" ",maxColLen-colNm.length()) + "    -- "+ StringUtil.delCR(tab.get(i).getColHnm())+"\n";
                //System.out.println("tabNm="+tabNm);
                
                // PK컬럼 조건 정보 생성
                if(tab.get(i).getPk().equals("Y")) { pkCol += "AND "+tab.get(i).getColNm() + " LIKE '%'\n"; }
            }

            qry    += inVo.isChkSelect() ? inVo.getTxtSelect() +"\n" : cols;
            /// Columns End ///////////////////////////////////////////////////////////

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
                        
            result.put("tabInfoList", tab);
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

        int maxColLen = 0;        
        
        try{
            for (int i = 0; i < inVo.size(); i++) {
                if(inVo.get(i).chk){
                    inVo.get(i).setOrderBy("1");
                    inVo.get(i).setJdbcInfo(getJdbcInfo(inVo.get(i).getJdbcNm()));
                    List<TabInfo> tab = sqlSession(inVo.get(i)).getMapper(TabInfoDao.class).selectMetaTabInfoList(inVo.get(i));
                    
                    /// Columns Start /////////////////////////////////////////////////////////
                    // SELECT 컬럼들중 길이가 가장 큰 컬럼의 길이를 산출한다            
                    for (int j = 0; j < tab.size(); j++) {
                        if(maxColLen <= tab.get(j).getColNm().length()) maxColLen = tab.get(j).getColNm().length();
                    }                    

                    //System.out.println("outVo="+outVo.size());
                    qry += "--DROP TABLE "+inVo.get(i).getOwner()+"."+inVo.get(i).getTabNm()+";\n";
                    //qry += "--DROP TABLE "+inVo.get(i).getTabNm()+";\n";
                    qry += "CREATE TABLE "+inVo.get(i).getOwner()+"."+inVo.get(i).getTabNm()+"\n";
                    //qry += "CREATE TABLE "+inVo.get(i).getTabNm()+"\n";
                    qry += "(\n";
                    pk  += ",CONSTRAINT "+inVo.get(i).getTabNm()+"_PK PRIMARY KEY(";

                    // 컬럼 정보 생성
                    for (int j = 0; j < tab.size(); j++) {
                        qry += (j>0) ? "," : " ";                        
                        qry += setColOnDBMS(tab.get(j),inVo.get(i).getJdbcNm(),inVo.get(i).getTarJdbcNm(), maxColLen);
                        //System.out.println("tabNm="+tabNm);

                        // PK정보 생성
                        if(tab.get(j).pk.equals("Y")) {pk += tab.get(j).colNm + ",";isPk=true;}
                    }
                    pk += ")";pk = pk.replace(",)", ")");
                    //System.out.println("isPk="+isPk);
                    if(isPk) qry += pk+"\n";
                    qry += ");\n";
                    pk = ""; isPk=false;
                }
            }

            // 메모패드로 출력
            FileUtil.stringToFile(qry, App.excelPath+"createTableScript.sql");
            Runtime run = Runtime.getRuntime ();
            run.exec ("cmd /c start notepad++.exe "+App.excelPath+"createTableScript.sql");

            
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
     * DBMS별 컬럼정보 변형 생성
     */
    public String setColOnDBMS(TabInfo tabInfo,String srcJdbcNm,String tarJdbcNm, int maxColLen){
        String retCol = "";

        String srcDb = getJdbcInfo(srcJdbcNm).getDb();
        String tarDb = getJdbcInfo(tarJdbcNm).getDb();
        
        String colNm = tabInfo.getColNm().toString(); 
        String dataTypeDesc = tabInfo.getDataTypeDesc().toString(); 
        String nullable = tabInfo.getNullable().toString();

        
        
        System.out.println("srcDb=="+srcDb);
        System.out.println("tarDb=="+tarDb);
        if(srcDb.contains("oracle") && tarDb.contains("postgre")){
            dataTypeDesc = dataTypeDesc.replace("NUMBER"  , "NUMERIC");
            dataTypeDesc = dataTypeDesc.replace("VARCHAR2", "VARCHAR");
        } 
        retCol = colNm +StringUtil.padRight(" ",maxColLen-colNm.length())+ dataTypeDesc +" "+ nullable+"\n";
        
        return retCol;
    }

    
    /**********************************************************************************
    *
    *                               테이블데이터추출
    *
    **********************************************************************************/
    public void ____________테이블데이터추출_____________(){}

    /*
     * 테이블 쿼리 건수
     */
    public Map<String, Object> selectDataCnt(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();
        try{
            int rowCnt = sqlSession(inVo).getMapper(TabInfoDao.class).selectDataCnt(inVo);            
            result.put("isSuccess", true);
            result.put("rowCnt", rowCnt);
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
    * INSERT문장을 생성하고 파일로 저장
    * */
    public Map<String, Object> makeInsStatToFile(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();
        inVo.setPathFileNm(App.excelPath+inVo.getTabNm()+".sql");
        PrintWriter writer = null;

        try {
            // 테이블 메타 정보 조회
            List<TabInfo> tabInfoOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectMetaTabInfoList(inVo);

            // 테이블 조회 스크립트 생성
            inVo.setQry(selectSelectScript(inVo).get("sqlScript").toString());

            //
            List<Map<String,Object>> insStatOutVoList = sqlSession(inVo).getMapper(TabInfoDao.class).selectCommonQryList(inVo);
            logger.info("tabInfoOutVoList="+insStatOutVoList.size());
            
            String qry = "";
            String s01 = "INSERT INTO "+inVo.getOwner()+"."+inVo.getTabNm()+" VALUES (";
            String s02 = "";
            writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(inVo.getPathFileNm())));
            
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
            result.put("isSuccess", true);
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
    
    /*
    * 테이블 데이터 건수조회후 갱신SQL 출력 및 로컬메테이블 테이블건수 갱신
    * */
    public Map<String, Object> updateTabRowsUpdateScript(List<TabInfo> inVo){
        logger.info("updateTabRowsUpdateScript");

        Map<String, Object> result = new HashMap<String, Object>();
        List<TabInfo> updateVoList = new ArrayList<TabInfo>();
        
        String qry = "";
        try{
            for (int i = 0; i < inVo.size(); i++) {
                if(inVo.get(i).chk){
                    inVo.get(i).setCntQry("SELECT COUNT(*) FROM "+inVo.get(i).getOwner()+"."+inVo.get(i).getTabNm());
                    System.out.println(inVo.get(i).getCntQry());
                    int rowCnt = sqlSession(inVo.get(i)).getMapper(TabInfoDao.class).selectDataCnt(inVo.get(i));
                    if(inVo.get(i).getTabRows()!=rowCnt) {
                        TabInfo updateVo = new TabInfo();
                        updateVo.setOwner(inVo.get(i).getOwner());
                        updateVo.setTabNm(inVo.get(i).getTabNm());
                        updateVo.setJdbcNm(inVo.get(i).getJdbcNm());
                        updateVo.setTabRows(rowCnt);
                        
                        qry += " UPDATE TDACM00080 SET TAB_ROWS="+rowCnt;
                        qry += " WHERE JDBC_NM='"+inVo.get(i).getJdbcNm()+"'";
                        qry += " AND OWNER='"+inVo.get(i).getOwner()+"'";
                        qry += " AND TAB_NM='"+inVo.get(i).getTabNm()+"';";
                        qry += "\n";

                        //updateVo.setQry(qry);
                        updateVoList.add(updateVo);
                    }    
                }
            }
            
            if(updateVoList.size()>0) {
                logger.info("updateVoList.size()=="+updateVoList.size());
                tabInfoSrv.updateTabRows(updateVoList);
            }    
            
            result.put("isSuccess", true);
            result.put("tabRowsUpdateScript", qry);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
 
}
