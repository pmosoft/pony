package net.pmosoft.pony.dams.table;

import java.util.List;
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
    *                               Pony테이블모듈_로컬DB
    *
    **********************************************************************************/
    /*
     * (추출) 테이블메타정보 조회후 추출테이블정보테이블에 저장후 정보 리턴
     */
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
     * (삭제) 테이블정보를 삭제한다
     */
    @RequestMapping(value = "/dams/table/deleteTabInfo")
    public Map<String, Object> deleteTabInfo(@RequestBody TabInfo inVo) {
        return tabInfoSrv.deleteTabInfo(inVo);
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


}
