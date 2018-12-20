package net.pmosoft.pony.dams.term;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


@Service
public class TermSrv {

	@Autowired
	private TermDao termDao;

	@Autowired
	private TermValidatorSrv termValidatorSrv;

	/**********************************************************************************
	 *
	 *                                  Term
	 *
	 **********************************************************************************/

    public Map<String, Object> selectExtTermList(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();

        List<Map<String,Object>> list = null;
        try{
            
            // 1.1단계 : 표준용어 조회
            list = termDao.selectExtTermList(params);;
            
            // 1.2단계 : 표준용어임시테이블 삭제                
            termDao.deleteExtTermList(params);

            // 1.3단계 : 표준용어임시테이블 삽입                
            for (int i = 0; i  < list.size(); i++) {
                termDao.insertExtTermList(list.get(i));
            }
            
            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }	

    public Map<String, Object> selectCmpTermList(@RequestParam Map<String,String> params) {
         
        Map<String, Object> result = new HashMap<String, Object>();
 
        System.out.println("params="+params);
        try{
            List<Map<String,Object>> list = termDao.selectCmpTermList(params);;
            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }     
    
	
	public Map<String, Object> selectTermList(Map<String,String> params){

		Map<String, Object> result = new HashMap<String, Object>();

		List<Map<String,Object>> list = null;
		try{
			list = termDao.selectTermList(params);;
			result.put("isSuccess", true);
			result.put("data", list);
		} catch (Exception e){
			result.put("isSuccess", false);
			result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
			result.put("errSysMsg", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public Map<String, Object> saveTerm(Map<String,String> params){


		System.out.println(termDao.selectTermCnt(params));

		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, String> errors = new HashMap<String, String>();
		errors = termValidatorSrv.validateSaveTerm(params);

		System.out.println("11");
		if(errors.size()>0){
			System.out.println("22");

			//model.addAttribute("tbTerm", tbTerm);
			result.put("isSuccess", false);
			result.put("errUsrMsg", errors.get("errUsrMsg"));
			return result;
		} else {
			System.out.println("33");

			try{
		    	result.put("isSuccess", true);

			    if  (termDao.selectTermCnt(params)==0) {
			    	termDao.insertTerm(params);
			    	result.put("usrMsg", "입력 되었습니다");
			    } else {
			    	termDao.updateTerm(params);
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

	public Map<String, Object> deleteTerm(Map<String,String> params){

		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, String> errors = new HashMap<String, String>();
		errors = termValidatorSrv.validateDeleteTerm(params);
		if(errors.size()>0){
			//model.addAttribute("tbTerm", tbTerm);
			result.put("isSuccess", false);
			result.put("errUsrMsg", errors.get("errUsrMsg"));
			System.out.println(result);
			return result;
		} else {
			termDao.deleteTerm(params);
			result.put("isSuccess", true);
			result.put("usrMsg", "삭제 되었습니다");
			return result;
		}
	}

    /**********************************************************************************
    *
    *                                  Pack
    *
    **********************************************************************************/
	
	
    public Map<String, Object> selectPackInfoList(Map<String,String> params){
        System.out.println("start PackInfoSrv selectPackInfoList");
        
        System.out.println("params221 searchValue="+params.get("searchValue"));
        
        Map<String, Object> result = new HashMap<String, Object>();

        List<Map<String,Object>> list = null;
        try{
            list = termDao.selectPackInfoList(params);;
            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;      
    }

    public Map<String, Object> savePackInfo(Map<String,String> params){

        
        System.out.println(termDao.selectPackInfoCnt(params));      
        
        Map<String, Object> result = new HashMap<String, Object>();

        Map<String, String> errors = new HashMap<String, String>();
        errors = termValidatorSrv.validateSavePackInfo(params);
        
        System.out.println("11");
        if(errors.size()>0){
            System.out.println("22");
            
            //model.addAttribute("tbPackInfo", tbPackInfo);
            result.put("isSuccess", false);
            result.put("errUsrMsg", errors.get("errUsrMsg"));
            return result;
        } else {
            System.out.println("33");
            
            try{
                result.put("isSuccess", true);
                
                if  (termDao.selectPackInfoCnt(params)==0) {
                    termDao.insertPackInfo(params);
                    result.put("usrMsg", "입력 되었습니다");
                } else {
                    termDao.updatePackInfo(params);
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

    public Map<String, Object> deletePackInfo(Map<String,String> params){
        
        Map<String, Object> result = new HashMap<String, Object>();

        Map<String, String> errors = new HashMap<String, String>();
        errors = termValidatorSrv.validateDeletePackInfo(params);
        if(errors.size()>0){
            //model.addAttribute("tbPackInfo", tbPackInfo);
            result.put("isSuccess", false);
            result.put("errUsrMsg", errors.get("errUsrMsg"));
            System.out.println(result);
            return result;
        } else {     
            termDao.deletePackInfo(params);
            result.put("isSuccess", true);
            result.put("usrMsg", "삭제 되었습니다");
            return result;          
        }   
    }
	
	
}
