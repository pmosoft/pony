package net.pmosoft.pony.etcl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EtclSrv {

	@Autowired
	private EtclDao etclDao;

	@Autowired
	private EtclValidatorSrv etclValidatorSrv;

	public Map<String, Object> selectEtclList(Map<String,String> params){

		Map<String, Object> result = new HashMap<String, Object>();

		List<Map<String,Object>> list = null;
		try{
			list = etclDao.selectEtclList(params);;
			result.put("isSuccess", true);
			result.put("data", list);
		} catch (Exception e){
			result.put("isSuccess", false);
			result.put("errUserMsg", "시스템 장애가 발생하였습니다");
			result.put("errSysMsg", e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public Map<String, Object> saveEtcl(Map<String,String> params){


		System.out.println(etclDao.selectEtclCnt(params));

		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, String> errors = new HashMap<String, String>();
		errors = etclValidatorSrv.validateSaveEtcl(params);

		if(errors.size()>0){
			result.put("isSuccess", false);
			result.put("errUserMsg", errors.get("errUserMsg"));
			return result;
		} else {
			try{
		    	result.put("isSuccess", true);

			    if  (etclDao.selectEtclCnt(params)==0) {
			    	etclDao.insertEtcl(params);
			    	result.put("msg", "입력 되었습니다");
			    } else {
			    	etclDao.updateEtcl(params);
			    	result.put("msg", "갱신 되었습니다");
			    }
			} catch (Exception e){
				e.printStackTrace();
				result.put("errUserMsg", "시스템 장애가 발생되었습니다.");
			}
			return result;
		}
	}

	public Map<String, Object> deleteEtcl(Map<String,String> params){

		Map<String, Object> result = new HashMap<String, Object>();

		Map<String, String> errors = new HashMap<String, String>();
		errors = etclValidatorSrv.validateDeleteEtcl(params);
		if(errors.size()>0){
			//model.addAttribute("tbEtcl", tbEtcl);
			result.put("isSuccess", false);
			result.put("errUserMsg", errors.get("errUserMsg"));
			System.out.println(result);
			return result;
		} else {
			etclDao.deleteEtcl(params);
			result.put("isSuccess", true);
			result.put("msg", "삭제 되었습니다");
			return result;
		}
	}

	
}
