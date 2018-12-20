/*******************************************************************************
@title:테이블DAO를 생성하는 인터페이스 
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
package net.pmosoft.pony.dams.table.dynamic;

import java.util.List;
import java.util.Map;

public interface TabDaoFactory {

    /*****************************************************************************
     *                              테이블 정보
     *****************************************************************************/
    /* 
     * 컬럼 정보 리턴
     * @param DB접속정보 및 DB유저명
     * */
    public List<Map<String, Object>> selectMetaTabColList(Map<String,String> params);
    
    /*
     * 테이블 정보 리턴
     * @param DB접속정보 및 DB유저명
     * */
    public List<Map<String, Object>> selectMetaTabList(Map<String,String> params);

    
    /*
     * CREATE TABLE 스크립트 리턴
     * @param DB접속정보 및 테이블명
     * */
    public String selectCreateTabScript(Map<String,String> params);
    
    /*
     * DROP TABLE 스크립트 리턴
     * @param DB접속정보 및 테이블명
     * */
    public String selectDropTabScript(Map<String,String> params);

    /*
     * TABLE COMMENT 스크립트 리턴
     * @param DB접속정보 및 테이블명
     * */
    public String selectTabCommentScript(Map<String,String> params);

    /*
     * COLUMN COMMENT 스크립트 리턴
     * @param DB접속정보 및 테이블명
     * */
    public String selectColCommentScript(Map<String,String> params);

    /*
     * GRANT 스크립트 리턴
     * @param DB접속정보 및 테이블명,TO유저명
     * */
    public String selectGrantUsrScript(Map<String,String> params);
    
    /*
     * INDEX 스크립트 리턴
     * @param DB접속정보 및 테이블명 
     * */
    public String selectIndexScript(Map<String,String> params);
    
    
    /*****************************************************************************
     *                                 쿼리
     *****************************************************************************/
    /*
     * 테이블 전컬럼 데이터 리턴
     * @param DB접속정보 및 테이블명 및 rowcnt
     * */
    public List<Map<String, Object>> selectTabData(Map<String,String> params);
    
    /*
     * 쿼리 데이터 리턴
     * @param DB접속정보 및 쿼리 및 rowcnt
     * */
    public List<Map<String, Object>> selectQryData(Map<String,String> params) throws Exception;

    
    /*
     * 쿼리의 최종컬럼 및 컬럼한글명을 메타정보를 이용하여 맵핑후 리턴
     * @param DB접속정보 및 쿼리
     * @return 컬럼|컬럼한글명|컬럼속성|키|널
     * */
    public List<Map<String, Object>> selectQryColInfo(Map<String,String> params);
    
    
    /*****************************************************************************
     *                                 ETT
     *****************************************************************************/
    /*
     * Csv 리턴(모든 컬럼)
     * @param DB접속정보 및 테이블명
     * */
    public List<Map<String, Object>> selectCsvData(Map<String,String> params) throws Exception;
    
    /*
     * Insert 문장 리턴(모든 컬럼)
     * @param DB접속정보 및 테이블명
     * */
    public String selectInsertData(Map<String,String> params) throws Exception;

}

