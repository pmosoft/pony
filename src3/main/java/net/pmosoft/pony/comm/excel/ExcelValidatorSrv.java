package net.pmosoft.pony.comm.excel;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

@WebAppConfiguration // 웹 컨텍스트 테스트 활성화

@Service
public class ExcelValidatorSrv {

	public Map<String, String> validateSaveExcel(Map<String, String> target) {

		System.out.println("validateSaveExcel");

		Map<String, String> errors = new HashMap<String, String>();
		System.out.println("validateSaveExcel11");

//		if (target.get("Excel_ID").length() < 5 || target.get("Excel_ID").length() > 15) {
//			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (target.get("Excel_EMAIL").length() < 5 || target.get("Excel_EMAIL").length() > 15) {
//			errors.put("errUsrMsg", "이메일 형식이 아닙니다.");
//		} else if  (target.get("Excel_PW").length() < 5 || target.get("Excel_PW").length() > 15) {
//			errors.put("errUsrMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (!target.get("Excel_PW").equals(target.get("Excel_PW2"))) {
//			errors.put("errUsrMsg", "암호와 암호확인을 일치시켜 주십시요.");
//		} else if  (target.get("Excel_NM").length() < 5 || target.get("Excel_NM").length() > 15) {
//			errors.put("errUsrMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		}
		System.out.println("validateSaveExcel55");

		return errors;
	}


	public Map<String, String> validateDeleteExcel(Map<String, String> target) {

		Map<String, String> errors = new HashMap<String, String>();
//		if (target.get("Excel_ID").length() < 5 || target.get("Excel_ID").length() > 15) {
//			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (ExcelDao.selectExcelCnt(target)==0) {
//			errors.put("errUsrMsg", "아이디가 미존재합니다.");
//		}

		return errors;
	}

	
    public Map<String, String> validateSaveExcelInfo(Map<String, String> target) {

        System.out.println("validateSaveExcelInfo");
        
        Map<String, String> errors = new HashMap<String, String>();
        System.out.println("validateSaveExcelInfo11");
        
//      if (target.get("ExcelInfo_ID").length() < 5 || target.get("ExcelInfo_ID").length() > 15) {
//          errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (target.get("ExcelInfo_EMAIL").length() < 5 || target.get("ExcelInfo_EMAIL").length() > 15) {
//          errors.put("errUsrMsg", "이메일 형식이 아닙니다.");
//      } else if  (target.get("ExcelInfo_PW").length() < 5 || target.get("ExcelInfo_PW").length() > 15) {
//          errors.put("errUsrMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (!target.get("ExcelInfo_PW").equals(target.get("ExcelInfo_PW2"))) {
//          errors.put("errUsrMsg", "암호와 암호확인을 일치시켜 주십시요.");
//      } else if  (target.get("ExcelInfo_NM").length() < 5 || target.get("ExcelInfo_NM").length() > 15) {
//          errors.put("errUsrMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      }
        System.out.println("validateSaveExcelInfo55");
        
        return errors;
    }
    
    
    public Map<String, String> validateDeleteExcelInfo(Map<String, String> target) {
        
        Map<String, String> errors = new HashMap<String, String>();
//      if (target.get("ExcelInfo_ID").length() < 5 || target.get("ExcelInfo_ID").length() > 15) {
//          errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (ExcelInfoDao.selectExcelInfoCnt(target)==0) {
//          errors.put("errUsrMsg", "아이디가 미존재합니다.");
//      }   
            
        return errors;
    }
    	
	
	
}
