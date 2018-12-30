package net.pmosoft.pony.dams.term;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

//@SpringBootConfiguration
//(classes=net.pmosoft.pony.FframeApplication.class)
@WebAppConfiguration // 웹 컨텍스트 테스트 활성화

@Service
public class TermValidatorSrv {

	//String configLocation = "classpath:springJdbcOracle.xml"; // src/main/resources/springJdbcOracle.xml
	//AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
	//WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(((HttpServletRequest) request).getSession().getServletContext());
	//TermDao TermDao = ctx.getBean("TermDao",TermDao.class);
    //@Autowired
    //private WebApplicationContext webContext; // WebApplicationContext 주입

    //TermDao TermDao = webContext.getBean("TermDao",TermDao.class);

	public Map<String, String> validateSaveTerm(Map<String, String> target) {

		System.out.println("validateSaveTerm");

		Map<String, String> errors = new HashMap<String, String>();
		System.out.println("validateSaveTerm11");

//		if (target.get("Term_ID").length() < 5 || target.get("Term_ID").length() > 15) {
//			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (target.get("Term_EMAIL").length() < 5 || target.get("Term_EMAIL").length() > 15) {
//			errors.put("errUsrMsg", "이메일 형식이 아닙니다.");
//		} else if  (target.get("Term_PW").length() < 5 || target.get("Term_PW").length() > 15) {
//			errors.put("errUsrMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (!target.get("Term_PW").equals(target.get("Term_PW2"))) {
//			errors.put("errUsrMsg", "암호와 암호확인을 일치시켜 주십시요.");
//		} else if  (target.get("Term_NM").length() < 5 || target.get("Term_NM").length() > 15) {
//			errors.put("errUsrMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		}
		System.out.println("validateSaveTerm55");

		return errors;
	}


	public Map<String, String> validateDeleteTerm(Map<String, String> target) {

		Map<String, String> errors = new HashMap<String, String>();
//		if (target.get("Term_ID").length() < 5 || target.get("Term_ID").length() > 15) {
//			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (TermDao.selectTermCnt(target)==0) {
//			errors.put("errUsrMsg", "아이디가 미존재합니다.");
//		}

		return errors;
	}

	
    public Map<String, String> validateSavePackInfo(Map<String, String> target) {

        System.out.println("validateSavePackInfo");
        
        Map<String, String> errors = new HashMap<String, String>();
        System.out.println("validateSavePackInfo11");
        
//      if (target.get("PackInfo_ID").length() < 5 || target.get("PackInfo_ID").length() > 15) {
//          errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (target.get("PackInfo_EMAIL").length() < 5 || target.get("PackInfo_EMAIL").length() > 15) {
//          errors.put("errUsrMsg", "이메일 형식이 아닙니다.");
//      } else if  (target.get("PackInfo_PW").length() < 5 || target.get("PackInfo_PW").length() > 15) {
//          errors.put("errUsrMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (!target.get("PackInfo_PW").equals(target.get("PackInfo_PW2"))) {
//          errors.put("errUsrMsg", "암호와 암호확인을 일치시켜 주십시요.");
//      } else if  (target.get("PackInfo_NM").length() < 5 || target.get("PackInfo_NM").length() > 15) {
//          errors.put("errUsrMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      }
        System.out.println("validateSavePackInfo55");
        
        return errors;
    }
    
    
    public Map<String, String> validateDeletePackInfo(Map<String, String> target) {
        
        Map<String, String> errors = new HashMap<String, String>();
//      if (target.get("PackInfo_ID").length() < 5 || target.get("PackInfo_ID").length() > 15) {
//          errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (PackInfoDao.selectPackInfoCnt(target)==0) {
//          errors.put("errUsrMsg", "아이디가 미존재합니다.");
//      }   
            
        return errors;
    }
    	
	
	
}
