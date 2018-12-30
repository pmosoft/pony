package net.pmosoft.pony.gens.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration // 웹 컨텍스트 테스트 활성화

@Service
public class TestTmplValidatorSrv {

	public Map<String, String> validateSaveTestTmpl(Map<String, String> target) {

		System.out.println("validateSaveTestTmpl");

		Map<String, String> errors = new HashMap<String, String>();
		System.out.println("validateSaveTestTmpl11");

//		if (target.get("TestTmpl_ID").length() < 5 || target.get("TestTmpl_ID").length() > 15) {
//			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (target.get("TestTmpl_EMAIL").length() < 5 || target.get("TestTmpl_EMAIL").length() > 15) {
//			errors.put("errUsrMsg", "이메일 형식이 아닙니다.");
//		} else if  (target.get("TestTmpl_PW").length() < 5 || target.get("TestTmpl_PW").length() > 15) {
//			errors.put("errUsrMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (!target.get("TestTmpl_PW").equals(target.get("TestTmpl_PW2"))) {
//			errors.put("errUsrMsg", "암호와 암호확인을 일치시켜 주십시요.");
//		} else if  (target.get("TestTmpl_NM").length() < 5 || target.get("TestTmpl_NM").length() > 15) {
//			errors.put("errUsrMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		}
		System.out.println("validateSaveTestTmpl55");

		return errors;
	}


	public Map<String, String> validateDeleteTestTmpl(Map<String, String> target) {

		Map<String, String> errors = new HashMap<String, String>();
//		if (target.get("TestTmpl_ID").length() < 5 || target.get("TestTmpl_ID").length() > 15) {
//			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (TestTmplDao.selectTestTmplCnt(target)==0) {
//			errors.put("errUsrMsg", "아이디가 미존재합니다.");
//		}

		return errors;
	}

	
    public Map<String, String> validateSaveTestTmplInfo(Map<String, String> target) {

        System.out.println("validateSaveTestTmplInfo");
        
        Map<String, String> errors = new HashMap<String, String>();
        System.out.println("validateSaveTestTmplInfo11");
        
//      if (target.get("TestTmplInfo_ID").length() < 5 || target.get("TestTmplInfo_ID").length() > 15) {
//          errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (target.get("TestTmplInfo_EMAIL").length() < 5 || target.get("TestTmplInfo_EMAIL").length() > 15) {
//          errors.put("errUsrMsg", "이메일 형식이 아닙니다.");
//      } else if  (target.get("TestTmplInfo_PW").length() < 5 || target.get("TestTmplInfo_PW").length() > 15) {
//          errors.put("errUsrMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (!target.get("TestTmplInfo_PW").equals(target.get("TestTmplInfo_PW2"))) {
//          errors.put("errUsrMsg", "암호와 암호확인을 일치시켜 주십시요.");
//      } else if  (target.get("TestTmplInfo_NM").length() < 5 || target.get("TestTmplInfo_NM").length() > 15) {
//          errors.put("errUsrMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      }
        System.out.println("validateSaveTestTmplInfo55");
        
        return errors;
    }
    
    
    public Map<String, String> validateDeleteTestTmplInfo(Map<String, String> target) {
        
        Map<String, String> errors = new HashMap<String, String>();
//      if (target.get("TestTmplInfo_ID").length() < 5 || target.get("TestTmplInfo_ID").length() > 15) {
//          errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (TestTmplInfoDao.selectTestTmplInfoCnt(target)==0) {
//          errors.put("errUsrMsg", "아이디가 미존재합니다.");
//      }   
            
        return errors;
    }
    	
	
	
}
