package net.pmosoft.pony.gens.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TestTmplSrv {

	@Autowired
	private TestTmplDao testTmplDao;

	@Autowired
	private TestTmplValidatorSrv testTmplValidatorSrv;

	public Map<String, Object> selectTestTmplList(Map<String,String> params){

		Map<String, Object> result = new HashMap<String, Object>();

		List<Map<String,Object>> list = null;
		try{
			list = testTmplDao.selectTestTmplList(params);;
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

	public Map<String, Object> saveTestTmpl(Map<String,String> params){


		System.out.println(testTmplDao.selectTestTmplCnt(params));

		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, String> errors = new HashMap<String, String>();
		errors = testTmplValidatorSrv.validateSaveTestTmpl(params);

		if(errors.size()>0){
			result.put("isSuccess", false);
			result.put("errUsrMsg", errors.get("errUsrMsg"));
			return result;
		} else {
			try{
		    	result.put("isSuccess", true);

			    if  (testTmplDao.selectTestTmplCnt(params)==0) {
			    	testTmplDao.insertTestTmpl(params);
			    	result.put("usrMsg", "입력 되었습니다");
			    } else {
			    	testTmplDao.updateTestTmpl(params);
			    	result.put("usrMsg", "갱신 되었습니다");
			    }
			} catch (Exception e){
				e.printStackTrace();
				result.put("errUsrMsg", "시스템 장애가 발생되었습니다.");
			}
			return result;
		}
	}

	public Map<String, Object> deleteTestTmpl(Map<String,String> params){

		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, String> errors = new HashMap<String, String>();
		errors = testTmplValidatorSrv.validateDeleteTestTmpl(params);
		if(errors.size()>0){
			//model.addAttribute("tbTestTmpl", tbTestTmpl);
			result.put("isSuccess", false);
			result.put("errUsrMsg", errors.get("errUsrMsg"));
			System.out.println(result);
			return result;
		} else {
			testTmplDao.deleteTestTmpl(params);
			result.put("isSuccess", true);
			result.put("usrMsg", "삭제 되었습니다");
			return result;
		}
	}

	
}
