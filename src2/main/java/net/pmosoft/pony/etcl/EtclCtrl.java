package net.pmosoft.pony.etcl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EtclCtrl {

	@Autowired
	private EtclSrv etclSrv;

	/**
	 * select EtclList
	 */
	@RequestMapping(value = "/etcl/selectEtclList")
	public Map<String, Object> selectEtclList(@RequestParam Map<String,String> params) {
		return etclSrv.selectEtclList(params);
	}

	/**
	 * saveEtcl
	 */
	@RequestMapping(value = "/etcl/saveEtcl")
	public Map<String, Object> saveEtcl(@RequestParam Map<String,String> params) {
		return etclSrv.saveEtcl(params);
	}

	/**
	 * deleteEtcl
	 */
	@RequestMapping(value = "/etcl/deleteEtcl")
	public Map<String, Object> deleteEtcl(@RequestParam Map<String,String> params) {
		return etclSrv.deleteEtcl(params);
	}

}
