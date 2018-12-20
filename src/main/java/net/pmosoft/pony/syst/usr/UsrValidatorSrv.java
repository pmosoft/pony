package net.pmosoft.pony.syst.usr;

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
public class UsrValidatorSrv {

	@Autowired
	private UsrDao UsrDao;

	//String configLocation = "classpath:springJdbcOracle.xml"; // src/main/resources/springJdbcOracle.xml
	//AbstractApplicationContext ctx = new GenericXmlApplicationContext(configLocation);
	//WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(((HttpServletRequest) request).getSession().getServletContext());	
	//UsrDao UsrDao = ctx.getBean("UsrDao",UsrDao.class);
    //@Autowired
    //private WebApplicationContext webContext; // WebApplicationContext 주입
 	
    //UsrDao UsrDao = webContext.getBean("UsrDao",UsrDao.class);
	
	public Map<String, String> validateSaveUsr(Map<String, String> target) {

		
		Map<String, String> errors = new HashMap<String, String>();
		if (target.get("USR_ID").length() < 5 || target.get("USR_ID").length() > 15) {
			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
		} else if  (target.get("USR_EMAIL").length() < 5 || target.get("USR_EMAIL").length() > 15) {
			errors.put("errUsrMsg", "이메일 형식이 아닙니다.");
		} else if  (target.get("USR_PW").length() < 5 || target.get("USR_PW").length() > 15) {
			errors.put("errUsrMsg", "유저암호를 5자리에서 14자리로 입력해 주시기 바랍니다.");
		} else if  (!target.get("USR_PW").equals(target.get("USR_PW2"))) {
			errors.put("errUsrMsg", "암호와 암호확인을 일치시켜 주십시요.");
		} else if  (target.get("USR_NM").length() < 5 || target.get("USR_NM").length() > 15) {
			errors.put("errUsrMsg", "성명을 5자리에서 14자리로 입력해 주시기 바랍니다.");
		}
		return errors;
	}
	
	
	public Map<String, String> validateDeleteUsr(Map<String, String> target) {
		
		Map<String, String> errors = new HashMap<String, String>();
		if (target.get("USR_ID").length() < 5 || target.get("USR_ID").length() > 15) {
			errors.put("errUsrMsg", "유저아이디를 5자리에서 14자리로 입력해 주시기 바랍니다.");
		} else if  (UsrDao.selectUsrCnt(target)==0) {
			errors.put("errUsrMsg", "아이디가 미존재합니다.");
		}	
			
		return errors;
	}
	
}
