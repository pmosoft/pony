package net.pmosoft.pony.gens.test;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestTmplCtrl {

	@Autowired
	private TestTmplSrv testTmplSrv;
	
	/**
	 * select TestTmplList
	 */
	@RequestMapping(value = "/gens/test/selectTestTmplList")
	public Map<String, Object> selectTestTmplList(@RequestParam Map<String,String> params) {
		return testTmplSrv.selectTestTmplList(params);
	}

	/**
	 * saveTestTmpl
	 */
	@RequestMapping(value = "/gens/test/saveTestTmpl")
	public Map<String, Object> saveTestTmpl(@RequestParam Map<String,String> params) {
		return testTmplSrv.saveTestTmpl(params);
	}

	/**
	 * deleteTestTmpl
	 */
	@RequestMapping(value = "/gens/test/deleteTestTmpl")
	public Map<String, Object> deleteTestTmpl(@RequestParam Map<String,String> params) {
		return testTmplSrv.deleteTestTmpl(params);
	}

}
