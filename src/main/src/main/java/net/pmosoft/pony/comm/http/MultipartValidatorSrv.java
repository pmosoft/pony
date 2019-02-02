package net.pmosoft.pony.comm.http;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration // 웹 컨텍스트 테스트 활성화

@Service
public class MultipartValidatorSrv {

	public Map<String, String> validateSaveMultipart(Map<String, String> target) {

		System.out.println("validateSaveMultipart");

		Map<String, String> errors = new HashMap<String, String>();
		System.out.println("validateSaveMultipart11");

//		if (target.get("Multipart_ID").length() < 5 || target.get("Multipart_ID").length() > 15) {
//			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (target.get("Multipart_EMAIL").length() < 5 || target.get("Multipart_EMAIL").length() > 15) {
//			errors.put("errUsrMsg", "이메일 형식이 아닙니다.");
//		} else if  (target.get("Multipart_PW").length() < 5 || target.get("Multipart_PW").length() > 15) {
//			errors.put("errUsrMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (!target.get("Multipart_PW").equals(target.get("Multipart_PW2"))) {
//			errors.put("errUsrMsg", "암호와 암호확인을 일치시켜 주십시요.");
//		} else if  (target.get("Multipart_NM").length() < 5 || target.get("Multipart_NM").length() > 15) {
//			errors.put("errUsrMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		}
		System.out.println("validateSaveMultipart55");

		return errors;
	}


	public Map<String, String> validateDeleteMultipart(Map<String, String> target) {

		Map<String, String> errors = new HashMap<String, String>();
//		if (target.get("Multipart_ID").length() < 5 || target.get("Multipart_ID").length() > 15) {
//			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (MultipartDao.selectMultipartCnt(target)==0) {
//			errors.put("errUsrMsg", "아이디가 미존재합니다.");
//		}

		return errors;
	}

	
    public Map<String, String> validateSaveMultipartInfo(Map<String, String> target) {

        System.out.println("validateSaveMultipartInfo");
        
        Map<String, String> errors = new HashMap<String, String>();
        System.out.println("validateSaveMultipartInfo11");
        
//      if (target.get("MultipartInfo_ID").length() < 5 || target.get("MultipartInfo_ID").length() > 15) {
//          errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (target.get("MultipartInfo_EMAIL").length() < 5 || target.get("MultipartInfo_EMAIL").length() > 15) {
//          errors.put("errUsrMsg", "이메일 형식이 아닙니다.");
//      } else if  (target.get("MultipartInfo_PW").length() < 5 || target.get("MultipartInfo_PW").length() > 15) {
//          errors.put("errUsrMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (!target.get("MultipartInfo_PW").equals(target.get("MultipartInfo_PW2"))) {
//          errors.put("errUsrMsg", "암호와 암호확인을 일치시켜 주십시요.");
//      } else if  (target.get("MultipartInfo_NM").length() < 5 || target.get("MultipartInfo_NM").length() > 15) {
//          errors.put("errUsrMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      }
        System.out.println("validateSaveMultipartInfo55");
        
        return errors;
    }
    
    
    public Map<String, String> validateDeleteMultipartInfo(Map<String, String> target) {
        
        Map<String, String> errors = new HashMap<String, String>();
//      if (target.get("MultipartInfo_ID").length() < 5 || target.get("MultipartInfo_ID").length() > 15) {
//          errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (MultipartInfoDao.selectMultipartInfoCnt(target)==0) {
//          errors.put("errUsrMsg", "아이디가 미존재합니다.");
//      }   
            
        return errors;
    }
    	
	
	
}
