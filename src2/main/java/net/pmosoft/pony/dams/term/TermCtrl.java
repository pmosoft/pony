package net.pmosoft.pony.dams.term;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 */
@RestController
public class TermCtrl {

	@Autowired
	private TermSrv termSrv;

	/**********************************************************************************
	 *
	 *                                  Term
	 *
	 **********************************************************************************/

    /*
     * (추출) 테이블정보에서 표준용어를 추출하여 임시테이블 삭제, 저장, 조회후 정보 리턴
     * @param 
     * */
    @RequestMapping(value = "/dams/term/selectExtTermList")
    public Map<String, Object> selectExtTermList(@RequestParam Map<String,String> params) {
        return termSrv.selectExtTermList(params);
    }

    /**
     * (비교) 기등록된 표준용어와 비교한 정보를 조회한다.
     */
    @RequestMapping(value = "/dams/table/selectCmpTermList")
    public Map<String, Object> selectCmpTermList(@RequestParam Map<String,String> params) { 
        return termSrv.selectCmpTermList(params);
    }
    
    
    /*
     * 표준용어를 리턴
     * @param 검색조건
     * */
	@RequestMapping(value = "/dams/term/selectTermList")
	public Map<String, Object> selectTermList(@RequestParam Map<String,String> params) {
		return termSrv.selectTermList(params);
	}
	

    /*
     * 표준용어를 저장
     * @param 표준용어 리스트
     * */
	@RequestMapping(value = "/dams/term/saveTerm")
	public Map<String, Object> saveTerm(@RequestParam Map<String,String> params) {
		return termSrv.saveTerm(params);
	}

    /*
     * 표준용어를 삭제
     * @param 표준용어 리스트
     * */
	@RequestMapping(value = "/dams/term/deleteTerm")
	public Map<String, Object> deleteTerm(@RequestParam Map<String,String> params) {
		return termSrv.deleteTerm(params);
	}
	
    /**********************************************************************************
    *
    *                                   Package
    *
    **********************************************************************************/
    /**
     * selectcPackInfoList
     */
    @RequestMapping(value = "/dams/pack/selectPackList")
    public Map<String, Object> selectPackInfoList(@RequestParam Map<String,String> params) {
        return termSrv.selectPackInfoList(params);
    }

    /**
     * savePackInfo
     */
    @RequestMapping(value = "/dams/pack/savePack")
    public Map<String, Object> savePackInfo(@RequestParam Map<String,String> params) {
        return termSrv.savePackInfo(params);
    }   

    /**
     * deletePackInfo
     */
    @RequestMapping(value = "/dams/pack/deletePack")
    public Map<String, Object> deletePackInfo(@RequestParam Map<String,String> params) {
        return termSrv.deletePackInfo(params);
    }   
    	
	

}
