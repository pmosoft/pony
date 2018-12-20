package net.pmosoft.pony.dams.table;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

//@SpringBootConfiguration
//(classes=net.pmosoft.pony.FframeApplication.class)
@WebAppConfiguration // 웹 컨텍스트 테스트 활성화

@Service
public class TabValidatorSrv {

	//String configLocation = "classpath:springJdbcOracle.xml"; // src/main/resources/springJdbcOracle.xml
	//AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
	//WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(((HttpServletRequest) request).getSession().getServletContext());
	//TabDao TabDao = ctx.getBean("TabDao",TabDao.class);
    //@Autowired
    //private WebApplicationContext webContext; // WebApplicationContext 주입

    //TabDao TabDao = webContext.getBean("TabDao",TabDao.class);

    /**********************************************************************************
    *
    *                                  TabCol
    *
    **********************************************************************************/
    public Map<String, String> validateSaveTabCol(Map<String, String> target) {

        System.out.println("validateSaveTabCol");
 
        Map<String, String> errors = new HashMap<String, String>();
        System.out.println("validateSaveTabCol11");

//      if (target.get("TabCol_ID").length() < 5 || target.get("TabCol_ID").length() > 15) {
//          errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (target.get("TabCol_EMAIL").length() < 5 || target.get("TabCol_EMAIL").length() > 15) {
//          errors.put("errUsrMsg", "이메일 형식이 아닙니다.");
//      } else if  (target.get("TabCol_PW").length() < 5 || target.get("TabCol_PW").length() > 15) {
//          errors.put("errUsrMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      } else if  (!target.get("TabCol_PW").equals(target.get("TabCol_PW2"))) {
//          errors.put("errUsrMsg", "암호와 암호확인을 일치시켜 주십시요.");
//      } else if  (target.get("TabCol_NM").length() < 5 || target.get("TabCol_NM").length() > 15) {
//          errors.put("errUsrMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//      }
        System.out.println("validateSaveTabCol55");

        return errors;
    }


    public Map<String, String> validateDeleteTabCol(List<Map<String,String>> target) {

        Map<String, String> errors = new HashMap<String, String>();
        if (target.size() == 0) {
            errors.put("errUsrMsg", "삭제 정보가 없습니다.");
        }    

        return errors;
    }
    
    /**********************************************************************************
    *
    *                                  Tab
    *
    **********************************************************************************/
	public Map<String, String> validateSaveTab(Map<String, String> target) {

		System.out.println("validateSaveTab");

		Map<String, String> errors = new HashMap<String, String>();
		System.out.println("validateSaveTab11");

//		if (target.get("Tab_ID").length() < 5 || target.get("Tab_ID").length() > 15) {
//			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (target.get("Tab_EMAIL").length() < 5 || target.get("Tab_EMAIL").length() > 15) {
//			errors.put("errUsrMsg", "이메일 형식이 아닙니다.");
//		} else if  (target.get("Tab_PW").length() < 5 || target.get("Tab_PW").length() > 15) {
//			errors.put("errUsrMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (!target.get("Tab_PW").equals(target.get("Tab_PW2"))) {
//			errors.put("errUsrMsg", "암호와 암호확인을 일치시켜 주십시요.");
//		} else if  (target.get("Tab_NM").length() < 5 || target.get("Tab_NM").length() > 15) {
//			errors.put("errUsrMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		}
		System.out.println("validateSaveTab55");

		return errors;
	}


	public Map<String, String> validateDeleteTab(List<Map<String,String>> target) {

		Map<String, String> errors = new HashMap<String, String>();
		if (target.size() == 0) {
			errors.put("errUsrMsg", "삭제 정보가 없습니다.");
//		} else if  (TabDao.selectTabCnt(target)==0) {
//			errors.put("errUsrMsg", "아이디가 미존재합니다.");
		}

		return errors;
	}

}
