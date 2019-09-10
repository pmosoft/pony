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
public class TabInfoDynCtrl {

    @Autowired
    private TabInfoDynSrv tabInfoDynSrv;
    
   /**
     * JDBC 연결 테스트
     */
    @RequestMapping(value = "/dams/table/testJdbcInfo")
    public Map<String, Object> testJdbcInfo(@RequestBody TabInfo inVo) {
        return tabInfoDynSrv.testJdbcInfo(inVo);
    }

    /**********************************************************************************
    *
    *                                       유틸
    *
    **********************************************************************************/
    /*
    * Select Script 생성 (SELECT)
    */
    @RequestMapping(value = "/dams/table/selectSelectScript")
    public Map<String, Object> selectSelectScript(@RequestBody TabInfo inVo){
    return tabInfoDynSrv.selectSelectScript(inVo);
    }

    /*
     * Create Script 생성
     */
    @RequestMapping(value = "/dams/table/selectCreateScript")
    public Map<String, Object> selectCreateScript(@RequestBody List<TabInfo> inVo){
        return tabInfoDynSrv.selectCreateScript(inVo);
    }
    
    /*
     * 테이블 쿼리 데이터 리턴
     */
    @RequestMapping(value = "/dams/table/selectTabQryList")
    public Map<String, Object> selectTabQryList(@RequestBody TabInfo inVo){
        return tabInfoDynSrv.selectTabQryList(inVo);
    }


    
    /*
     * 다운로드 Insert 문장
     */
    @RequestMapping(value = "/dams/table/downloadInsStat")
    public Map<String, Object> downloadInsStat(@RequestBody TabInfo inVo){
        return tabInfoDynSrv.downloadInsStat(inVo);
    }

    /*
     * 다운로드 Excel로 데이터 출력
     */
    @RequestMapping(value = "/dams/table/downloadExcel")
    public Map<String, Object> downloadExcel(@RequestBody TabInfo inVo){
        return tabInfoDynSrv.downloadExcel(inVo);
    }

    /*
     * 다운로드 Comma File
     */
    @RequestMapping(value = "/dams/table/downloadCommaFile")
    public Map<String, Object> downloadCommaFile(@RequestBody TabInfo inVo){
        return tabInfoDynSrv.downloadCommaFile(inVo);
    }

    /*
     * 다운로드 Bar File
     */
    @RequestMapping(value = "/dams/table/downloadBarFile")
    public Map<String, Object> downloadBarFile(@RequestBody TabInfo inVo){
        return tabInfoDynSrv.downloadBarFile(inVo);
    }

    /*
     * 테이블 데이터 건수조회후 갱신SQL 출력 및 로컬메테이블 테이블건수 갱신
     */
    @RequestMapping(value = "/dams/table/updateTabRowsUpdateScript")
    public Map<String, Object> updateTabRowsUpdateScript(@RequestBody List<TabInfo> inVo){
        return tabInfoDynSrv.updateTabRowsUpdateScript(inVo);
    }
}
