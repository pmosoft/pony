package net.pmosoft.pony.syst.usr;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
/**
 * @author Administrator
 *
 */
@RestController
public class UsrCtrl {

	@Autowired
	private UsrSrv UsrSrv;
 
	@RequestMapping(value = "/usr/selectUsrList")
//	public Map<String, Object> selectUsrList(@RequestParam("searchCondition") String searchCondition) {
	public Map<String, Object> selectUsrList(@RequestParam Map<String,String> params) {
		return UsrSrv.selectUsrList(params);
	}
	
	@RequestMapping(value = "/usr/saveUsr")
	public Map<String, Object> saveUsr(@RequestParam Map<String,String> params) {
		return UsrSrv.saveUsr(params);
	}	
	 
/*	@InitBinder
	protected void initBinder(WebDataBinder binder){
		binder.setValidator(new UsrValidator());
	}
*/	

	@RequestMapping(value = "/usr/deleteUsr")
	public Map<String, Object> deleteUsr(@RequestParam Map<String,String> params) {
		return UsrSrv.deleteUsr(params);
	}	
	
	@RequestMapping(value = "/test")
	public Map<String, Object> test() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("name", "피승현");
		result.put("age", 50);
		return result;
	}
	
	
	
}
