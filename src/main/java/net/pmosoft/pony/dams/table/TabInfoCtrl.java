package net.pmosoft.pony.dams.table;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 테이블에 모든 정보에 이쪽으로 모았지만, 추후 하부 패키지로 나눌수 있으며
 * 현재 하부 패키지 구조를 주석으로 표현했다.(2018-04-14 피승현)
 * ctl은 인터페이스 역할을 하며 하부 srv이하의 메소드 순서는 중요하지 않다. 찾기로 찾는다.
 */
@RestController
@CrossOrigin(origins="http://localhost:4202")
public class TabInfoCtrl {

    @Autowired
    private TabInfoSrv tabSrv;

    /**********************************************************************************
    *
    *                              (범용) 테이블 정보
    *
    **********************************************************************************/

    /*
     * 컬럼정보 리턴(메타정보 이용)
     * @param DB접속정보 및 DB유저명 ==> (검색조건으로 변경요. 2018-04-14 피승현)
     * Meta란 말을 전부 삭제한다. pony 테이블정보 쪽을 local로 붙여준다.
     * */
    @RequestMapping(value = "/dams/table/selectMetaTabInfoList")
    public Map<String, Object> selectMetaTabInfoList(@RequestBody TabInfo inVo) {
        return tabSrv.selectMetaTabInfoList(inVo);
    }

    /*
     * 테이블정보 리턴(메타정보 이용)
     * @param DB접속정보 및 DB유저명 ==> (검색조건으로 변경요. 2018-04-14 피승현)
     * */
    @RequestMapping(value = "/dams/table/selectMetaTabList")
    public Map<String, Object> selectMetaTabList(@RequestParam Map<String,String> params) {
        System.out.println("selectMetaTabList");
        return tabSrv.selectMetaTabList(params);
    }

    /* (개발중) 테이블 스크립트 리턴(메타정보 이용)
     * @param DB접속정보 및 테이블명
     * */
    @RequestMapping(value = "/dams/table/selectMetaTabScript")
    public Map<String, Object> selectMetaTabScript(@RequestParam Map<String,String> params) {
        return tabSrv.selectTabList(params);
    }


    /**********************************************************************************
    *
    *                              (범용) 테이블 CRUD
    *
    **********************************************************************************/
    /*
     * 테이블 데이터 리턴
     * @param DB접속정보 및 테이블명 혹은 쿼리 및 rowcnt
     * */
    @RequestMapping(value = "/dams/table/selectQryData")
    public Map<String, Object>  selectQryData(@RequestParam Map<String,String> params){
        System.out.println("params="+params);
        return tabSrv.selectQryData(params);
    }


    /**********************************************************************************
    *
    *                              (범용) 테이블 ETT
    *
    **********************************************************************************/

    /*
     * (개발중) Insert 문장 리턴(메타 정보 이용)
     * @param DB접속정보 및 쿼리 및 rowcnt
     * */
    @RequestMapping(value = "/dams/table/selectInsertData")
    public Map<String, Object> selectInsertData(@RequestParam Map<String,String> params){
        return tabSrv.selectInsertData(params);
    }


    /*
     * (개발중) 로드 스크립트 리턴(메타정보 이용)
     * @param DB접속정보 및 테이블명
     * */
    @RequestMapping(value = "/dams/table/selectMetaLodScript")
    public Map<String, Object> selectMetaLodScript(@RequestParam Map<String,String> params) {
        return tabSrv.selectTabList(params);
    }

    /*
     * (개발) CSV 데이터 리턴
     * @param DB접속정보 및 쿼리 및 rowcnt
     * */
    @RequestMapping(value = "/dams/table/downloadCsvData")
    public Map<String, Object>  selectCsvData(@RequestParam Map<String,String> params){
        return tabSrv.selectCsvData(params);
    }



    /**********************************************************************************
    *
    *                              (pony) 테이블 정보
    *
    *********************************************************************************/

    /*
     * pony 테이블컬럼정보를 리턴
     * @param 조회 조건값
     * 추후 이름을 selectLocalTabColList로 변경해 준다.
     * */
    @RequestMapping(value = "/dams/table/selectTabColList")
    public Map<String, Object> selectTabColList(@RequestParam Map<String,String> params) {
        System.out.println(params);
        return tabSrv.selectTabColList(params);
    }

    /*
     * pony 테이블컬럼정보를 저장
     * @param 테이블컬럼정보
     * 추후 이름을 selectLocalTabColList로 변경해 준다.
     * */
    @RequestMapping(value = "/dams/table/saveTabCol")
    public Map<String, Object> saveTabCol(@RequestParam String params) {
        return tabSrv.saveTabCol(params);
    }

    /*
     * pony 테이블컬럼정보를 삭제
     * @param 조회 조건값
     * 추후 이름을 selectLocalTabColList로 변경해 준다.
     * */
    @RequestMapping(value = "/dams/table/deleteTabCol")
    public Map<String, Object> deleteTabCol(@RequestParam Map<String,String> params) {
        return tabSrv.deleteTabCol(params);
    }

    /*
     * pony 테이블정보를 리턴
     * @param 조회 조건값
     * 추후 이름을 selectLocalTabColList로 변경해 준다.
     * */
    @RequestMapping(value = "/dams/table/selectTabList")
    public Map<String, Object> selectTabList(@RequestParam Map<String,String> params) {
        return tabSrv.selectTabList(params);
    }

    /*
     * pony 테이블정보를 저장
     * @param 테이블정보
     * 추후 이름을 selectLocalTabColList로 변경해 준다.
     * */
    @RequestMapping(value = "/dams/table/saveTab")
    public Map<String, Object> saveTab(@RequestParam Map<String,String> params) {
        return tabSrv.saveTab(params);
    }

    /*
     * pony 테이블정보를 삭제
     * @param 조회 조건값
     * 추후 이름을 selectLocalTabColList로 변경해 준다.
     * */
    @RequestMapping(value = "/dams/table/deleteTab")
    public Map<String, Object> deleteTab(@RequestParam Map<String,String> params) {
        return tabSrv.deleteTab(params);
    }

    /*
     * 테이블 데이터 리턴
     * @param DB접속정보 및 테이블명 및 rowcnt
     * 추후 이름을 selectLocalTabColList로 변경해 준다.
     * */
    @RequestMapping(value = "/dams/table/selectTabData")
    public Map<String, Object>  selectTabData(@RequestParam Map<String,String> params){
        return tabSrv.selectTabData(params);
    }

    /**********************************************************************************
    *
    *                            ExtMetaTabColListView
    *
    **********************************************************************************/

    /*
     * (추출) 메타테이블컬럼정보 조회후 메타임시테이블 삭제, 저장, 조회후 정보 리턴
     * @param DB접속정보 및 DB유저명
     * */
    @RequestMapping(value = "/dams/table/selectExtractMetaTabColList")
    public Map<String, Object> selectExtractMetaTabColList(@RequestParam Map<String,String> params) {
        System.out.println("selectMetaTabColList");
        return tabSrv.selectExtractMetaTabColList(params);
    }

    /**
     * (비교) 메타임시테이블과 컬럼정보 테이블과 비교한 정보를 조회한다.
     */
    @RequestMapping(value = "/dams/table/selectCmpTabColList")
    public Map<String, Object> selectCmpTabColList(@RequestParam Map<String,String> params) {
        return tabSrv.selectCmpTabColList(params);
    }

    /**
     * (반영) 신규-변경 테이블정보를 반영한다
     */
    @RequestMapping(value = "/dams/table/insertCmpTabColList")
    public Map<String, Object> insertCmpTabColList(@RequestParam Map<String,String> params) {
        return tabSrv.insertCmpTabColList(params);
    }



    /**********************************************************************************
    *
    *                                   Excel Upload
    *
    **********************************************************************************/

    /**
     * 엑셀 업로드된 테이블정보를 임시 테이블에 저장한다.
     */
    @RequestMapping(value = "/dams/table/insertExcelTabColList")
    public Map<String, Object> insertExcelTabColList(@RequestParam Map<String,String> params) {
        return tabSrv.insertExcelTabColList(params);
    }

}
