package net.pmosoft.pony.dams.jdbc;

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
public class JdbcInfoValidatorSrv {

	//String configLocation = "classpath:springJdbcOracle.xml"; // src/main/resources/springJdbcOracle.xml
	//AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
	//WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(((HttpServletRequest) request).getSession().getServletContext());
	//JdbcInfoDao JdbcInfoDao = ctx.getBean("JdbcInfoDao",JdbcInfoDao.class);
    //@Autowired
    //private WebApplicationContext webContext; // WebApplicationContext 주입

    //JdbcInfoDao JdbcInfoDao = webContext.getBean("JdbcInfoDao",JdbcInfoDao.class);

    
    public Map<String, String> validateSaveJdbcInfo(List<Map<String,String>> target) {
    

		//System.out.println("validateSaveJdbcInfo");

		Map<String, String> errors = new HashMap<String, String>();
		//System.out.println("validateSaveJdbcInfo11");

        if (target.size() == 0) {
            errors.put("errUsrMsg", "저장 정보가 없습니다.");
        }    		
		
//		if (target.get("JdbcInfo_ID").length() < 5 || target.get("JdbcInfo_ID").length() > 15) {
//			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (target.get("JdbcInfo_EMAIL").length() < 5 || target.get("JdbcInfo_EMAIL").length() > 15) {
//			errors.put("errUsrMsg", "이메일 형식이 아닙니다.");
//		} else if  (target.get("JdbcInfo_PW").length() < 5 || target.get("JdbcInfo_PW").length() > 15) {
//			errors.put("errUsrMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (!target.get("JdbcInfo_PW").equals(target.get("JdbcInfo_PW2"))) {
//			errors.put("errUsrMsg", "암호와 암호확인을 일치시켜 주십시요.");
//		} else if  (target.get("JdbcInfo_NM").length() < 5 || target.get("JdbcInfo_NM").length() > 15) {
//			errors.put("errUsrMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		}
		//System.out.println("validateSaveJdbcInfo55");

		return errors;
	}


	public Map<String, String> validateDeleteJdbcInfo(Map<String, String> target) {

		Map<String, String> errors = new HashMap<String, String>();
//		if (target.get("JdbcInfo_ID").length() < 5 || target.get("JdbcInfo_ID").length() > 15) {
//			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
//		} else if  (JdbcInfoDao.selectJdbcInfoCnt(target)==0) {
//			errors.put("errUsrMsg", "아이디가 미존재합니다.");
//		}

		return errors;
	}

}
