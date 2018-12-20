package net.pmosoft.pony.etcl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration // 웹 컨텍스트 테스트 활성화

@Service
public class EtclValidatorSrv {

	public Map<String, String> validateSaveEtcl(Map<String, String> target) {

		System.out.println("validateSaveEtcl");

		Map<String, String> errors = new HashMap<String, String>();
		System.out.println("validateSaveEtcl11");

//		if (target.get("Etcl_ID").length() < 5 || target.get("Etcl_ID").length() > 15) {
//			errors.put("errUserMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (target.get("Etcl_EMAIL").length() < 5 || target.get("Etcl_EMAIL").length() > 15) {
//			errors.put("errUserMsg", "이메일 형식이 아닙니다.");
//		} else if  (target.get("Etcl_PW").length() < 5 || target.get("Etcl_PW").length() > 15) {
//			errors.put("errUserMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (!target.get("Etcl_PW").equals(target.get("Etcl_PW2"))) {
//			errors.put("errUserMsg", "암호와 암호확인을 일치시켜 주십시요.");
//		} else if  (target.get("Etcl_NM").length() < 5 || target.get("Etcl_NM").length() > 15) {
//			errors.put("errUserMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		}
		System.out.println("validateSaveEtcl55");

		return errors;
	}


	public Map<String, String> validateDeleteEtcl(Map<String, String> target) {

		Map<String, String> errors = new HashMap<String, String>();
//		if (target.get("Etcl_ID").length() < 5 || target.get("Etcl_ID").length() > 15) {
//			errors.put("errUserMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (EtclDao.selectEtclCnt(target)==0) {
//			errors.put("errUserMsg", "아이디가 미존재합니다.");
//		}

		return errors;
	}

	
    public Map<String, String> validateSaveEtclInfo(Map<String, String> target) {

        System.out.println("validateSaveEtclInfo");
        
        Map<String, String> errors = new HashMap<String, String>();
        System.out.println("validateSaveEtclInfo11");
        
//      if (target.get("EtclInfo_ID").length() < 5 || target.get("EtclInfo_ID").length() > 15) {
//          errors.put("errUserMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (target.get("EtclInfo_EMAIL").length() < 5 || target.get("EtclInfo_EMAIL").length() > 15) {
//          errors.put("errUserMsg", "이메일 형식이 아닙니다.");
//      } else if  (target.get("EtclInfo_PW").length() < 5 || target.get("EtclInfo_PW").length() > 15) {
//          errors.put("errUserMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (!target.get("EtclInfo_PW").equals(target.get("EtclInfo_PW2"))) {
//          errors.put("errUserMsg", "암호와 암호확인을 일치시켜 주십시요.");
//      } else if  (target.get("EtclInfo_NM").length() < 5 || target.get("EtclInfo_NM").length() > 15) {
//          errors.put("errUserMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      }
        System.out.println("validateSaveEtclInfo55");
        
        return errors;
    }
    
    
    public Map<String, String> validateDeleteEtclInfo(Map<String, String> target) {
        
        Map<String, String> errors = new HashMap<String, String>();
//      if (target.get("EtclInfo_ID").length() < 5 || target.get("EtclInfo_ID").length() > 15) {
//          errors.put("errUserMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (EtclInfoDao.selectEtclInfoCnt(target)==0) {
//          errors.put("errUserMsg", "아이디가 미존재합니다.");
//      }   
            
        return errors;
    }
    	
	
	
}
