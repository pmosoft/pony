package net.pmosoft.pony.syst.usr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UsrSrv {
	
	@Autowired
	private UsrDao UsrDao;

	@Autowired
	private UsrValidatorSrv UsrValidatorSrv;
	
	public Map<String, Object> selectUsrList(Map<String,String> params){
		System.out.println("start UsrSrv selectUsrList");
		
		System.out.println("params111 searchKeyCombo="+params.get("searchKeyCombo"));
		System.out.println("params221 searchValue="+params.get("searchValue"));
		
		Map<String, Object> result = new HashMap<String, Object>();

		List<Map<String,Object>> list = null;
		try{
			list = UsrDao.selectUsrList(params);;
			result.put("isSuccess", true);
			result.put("data", list);
            result.put("total", list.size());			
		} catch (Exception e){
			result.put("isSuccess", false);
			result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
			result.put("errSysMsg", e.getMessage());
			e.printStackTrace();
		}
		return result;		
	}
 
	
	public Map<String, Object> saveUsr(Map<String,String> params){

		
		System.out.println(UsrDao.selectUsrCnt(params));		
		
		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, String> errors = new HashMap<String, String>();
		errors = UsrValidatorSrv.validateSaveUsr(params);
		if(errors.size()>0){
			//model.addAttribute("tbUsr", tbUsr);
			result.put("isSuccess", false);
			result.put("errUsrMsg", errors.get("errUsrMsg"));
			return result;
		} else {	 
			try{
		    	result.put("isSuccess", true);
				
			    if  (UsrDao.selectUsrCnt(params)==0) {
			    	UsrDao.insertUsr(params);
			    	result.put("usrMsg", "입력 되었습니다");
			    } else {
			    	UsrDao.updateUsr(params);
			    	result.put("usrMsg", "갱신 되었습니다");
			    }	
			} catch (Exception e){
				e.printStackTrace();
				result.put("errUsrMsg", "시스템 장애가 발생되었습니다.");
				//result.put("errSysMsg", e.toString());
			}
			return result;
		}	
	}

	public Map<String, Object> deleteUsr(Map<String,String> params){
		
		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, String> errors = new HashMap<String, String>();
		errors = UsrValidatorSrv.validateDeleteUsr(params);
		if(errors.size()>0){
			//model.addAttribute("tbUsr", tbUsr);
			result.put("isSuccess", false);
			result.put("errUsrMsg", errors.get("errUsrMsg"));
			System.out.println(result);
			return result;
		} else {	 
			UsrDao.deleteUsr(params);
			result.put("isSuccess", true);
			result.put("usrMsg", "삭제 되었습니다");
			return result;			
		}	
	}
	
}
