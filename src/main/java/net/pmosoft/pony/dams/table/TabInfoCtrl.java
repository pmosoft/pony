package net.pmosoft.pony.dams.table;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import net.pmosoft.pony.dams.jdbc.JdbcInfo;

/**
 * 테이블에 모든 정보에 이쪽으로 모았지만, 추후 하부 패키지로 나눌수 있으며
 * 현재 하부 패키지 구조를 주석으로 표현했다.(2018-04-14 피승현)
 * ctl은 인터페이스 역할을 하며 하부 srv이하의 메소드 순서는 중요하지 않다. 찾기로 찾는다.
 */
@RestController
@CrossOrigin(origins="http://localhost:4202")
public class TabInfoCtrl {

    @Autowired
    private TabInfoSrv tabInfoSrv;

    /**********************************************************************************
    *
    *                               테이블정보 추출 및 저장
    *
    **********************************************************************************/


    /*
     * (추출) 테이블메타정보 조회후 추출테이블정보테이블에 저장후 정보 리턴
     * @param jdbcNm,owner,tabNm
     * */
    @RequestMapping(value = "/dams/table/selectMetaTabInfoList")
    public Map<String, Object> selectMetaTabInfoList(@RequestBody TabInfo inVo) {
        return tabInfoSrv.selectMetaTabInfoList(inVo);
    }

     /**
     * (비교) 추출테이블정보테이블과 현재 테이블정보테이블과 비교한 정보를 리턴한다.
     */
    @RequestMapping(value = "/dams/table/selectCmpTabInfoList")
    public Map<String, Object> selectCmpTabInfoList(@RequestBody TabInfo inVo) {
        return tabInfoSrv.selectCmpTabInfoList(inVo);
    }

     /**
     * (반영) 신규-변경 테이블정보를 반영한다
     */
    @RequestMapping(value = "/dams/table/saveCmpTabInfoList")
    public Map<String, Object> insertCmpTabInfoList(@RequestBody TabInfo inVo) {
        return tabInfoSrv.saveCmpTabInfoList(inVo);
    }

    /**
    * (조회) 추출되어 저장된 테이블정보를 조회한다.
    */
    @RequestMapping(value = "/dams/table/selectTabInfoList")
    public Map<String, Object> selectTabInfoList(@RequestBody TabInfo inVo) {
        return tabInfoSrv.selectTabInfoList(inVo);
    }

    /**
    * (조회) 테이블목록을 조회한다.
    */
    @RequestMapping(value = "/dams/table/selectTabList")
    public Map<String, Object> selectTabList(@RequestBody TabInfo inVo) {
        return tabInfoSrv.selectTabList(inVo);
    }

    /**
    * (조회) 컬럼목록을 조회한다.
    */
    @RequestMapping(value = "/dams/table/selectColList")
    public Map<String, Object> selectColList(@RequestBody TabInfo inVo) {
        return tabInfoSrv.selectColList(inVo);
    }

    /**
     * JDBC 연결 테스트
     */
    @RequestMapping(value = "/dams/table/testJdbcInfo")
    public Map<String, Object> testJdbcInfo(@RequestBody TabInfo inVo) {
        return tabInfoSrv.testJdbcInfo(inVo);
    }



//    /* (개발중) 테이블 스크립트 리턴(메타정보 이용)
//     * */
//    @RequestMapping(value = "/dams/table/selectMetaTabScript")
//    public Map<String, Object> selectMetaTabScript(@RequestParam Map<String,String> params) {
//        return null;
//    }


//    /**********************************************************************************
//    *
//    *                              (범용) 테이블 CRUD
//    *
//    **********************************************************************************/
//    /*
//     * 테이블 데이터 리턴
//     * @param DB접속정보 및 테이블명 혹은 쿼리 및 rowcnt
//     * */
//    @RequestMapping(value = "/dams/table/selectQryData")
//    public Map<String, Object>  selectQryData(@RequestParam Map<String,String> params){
//        System.out.println("params="+params);
//        return tabInfoSrv.selectQryData(params);
//    }
//
//    /**********************************************************************************
//    *
//    *                              (범용) 테이블 ETT
//    *
//    **********************************************************************************/
//
//    /*
//     * (개발중) Insert 문장 리턴(메타 정보 이용)
//     * @param DB접속정보 및 쿼리 및 rowcnt
//     * */
//    @RequestMapping(value = "/dams/table/selectInsertData")
//    public Map<String, Object> selectInsertData(@RequestParam Map<String,String> params){
//        return tabInfoSrv.selectInsertData(params);
//    }
//
//
//    /*
//     * (개발중) 로드 스크립트 리턴(메타정보 이용)
//     * @param DB접속정보 및 테이블명
//     * */
//    @RequestMapping(value = "/dams/table/selectMetaLodScript")
//    public Map<String, Object> selectMetaLodScript(@RequestParam Map<String,String> params) {
//        return tabInfoSrv.selectTabList(params);
//    }
//
//    /*
//     * (개발) CSV 데이터 리턴
//     * @param DB접속정보 및 쿼리 및 rowcnt
//     * */
//    @RequestMapping(value = "/dams/table/downloadCsvData")
//    public Map<String, Object>  selectCsvData(@RequestParam Map<String,String> params){
//        return tabInfoSrv.selectCsvData(params);
//    }
//
//
//
//    /**********************************************************************************
//    *
//    *                              (pony) 테이블 정보
//    *
//    *********************************************************************************/
//
//    /*
//     * pony 테이블컬럼정보를 리턴
//     * @param 조회 조건값
//     * 추후 이름을 selectLocalTabColList로 변경해 준다.
//     * */
//    @RequestMapping(value = "/dams/table/selectTabColList")
//    public Map<String, Object> selectTabColList(@RequestParam Map<String,String> params) {
//        System.out.println(params);
//        return tabInfoSrv.selectTabColList(params);
//    }
//
//    /*
//     * pony 테이블컬럼정보를 저장
//     * @param 테이블컬럼정보
//     * 추후 이름을 selectLocalTabColList로 변경해 준다.
//     * */
//    @RequestMapping(value = "/dams/table/saveTabCol")
//    public Map<String, Object> saveTabCol(@RequestParam String params) {
//        return tabInfoSrv.saveTabCol(params);
//    }
//
//    /*
//     * pony 테이블컬럼정보를 삭제
//     * @param 조회 조건값
//     * 추후 이름을 selectLocalTabColList로 변경해 준다.
//     * */
//    @RequestMapping(value = "/dams/table/deleteTabCol")
//    public Map<String, Object> deleteTabCol(@RequestParam Map<String,String> params) {
//        return tabInfoSrv.deleteTabCol(params);
//    }
//
//    /*
//     * pony 테이블정보를 리턴
//     * @param 조회 조건값
//     * 추후 이름을 selectLocalTabColList로 변경해 준다.
//     * */
//    @RequestMapping(value = "/dams/table/selectTabList")
//    public Map<String, Object> selectTabList(@RequestParam Map<String,String> params) {
//        return tabInfoSrv.selectTabList(params);
//    }
//
//    /*
//     * pony 테이블정보를 저장
//     * @param 테이블정보
//     * 추후 이름을 selectLocalTabColList로 변경해 준다.
//     * */
//    @RequestMapping(value = "/dams/table/saveTab")
//    public Map<String, Object> saveTab(@RequestParam Map<String,String> params) {
//        return tabInfoSrv.saveTab(params);
//    }
//
//    /*
//     * pony 테이블정보를 삭제
//     * @param 조회 조건값
//     * 추후 이름을 selectLocalTabColList로 변경해 준다.
//     * */
//    @RequestMapping(value = "/dams/table/deleteTab")
//    public Map<String, Object> deleteTab(@RequestParam Map<String,String> params) {
//        return tabInfoSrv.deleteTab(params);
//    }
//
//    /*
//     * 테이블 데이터 리턴
//     * @param DB접속정보 및 테이블명 및 rowcnt
//     * 추후 이름을 selectLocalTabColList로 변경해 준다.
//     * */
//    @RequestMapping(value = "/dams/table/selectTabData")
//    public Map<String, Object>  selectTabData(@RequestParam Map<String,String> params){
//        return tabInfoSrv.selectTabData(params);
//    }
//

//
//    /**********************************************************************************
//    *
//    *                                   Excel Upload
//    *
//    **********************************************************************************/
//
//    /**
//     * 엑셀 업로드된 테이블정보를 임시 테이블에 저장한다.
//     */
//    @RequestMapping(value = "/dams/table/insertExcelTabColList")
//    public Map<String, Object> insertExcelTabColList(@RequestParam Map<String,String> params) {
//        return tabInfoSrv.insertExcelTabColList(params);
//    }

}
