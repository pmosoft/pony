package net.pmosoft.pony.comm.util;

import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.sql.DataSource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
 
import org.apache.log4j.Logger;
import org.springframework.ui.ModelMap;
 
/**
 * Copyright 2005 by Korea Health Industry Development Institute. All rights reserved
 *
 * This software is the proprietary information of
 * Korea Health Industry Development Institute.
 * Use is subject to license terms.
 *
 * <p>Title: DB Connection Util</p>
 *
 * <p>Description: Connection을 관리하는 유틸리티(Singleton 패턴 적용).</p>
 *
 * <p>Copyright: Copyright (c) 2005 SystemGate Corp. All rights reserved.</p>
 *
 * <p>Company:  </p>
 *
 * @author youngsin, bae
 * @version 1.0
 * @since jdk1.4.2
 */
public class JsonUtil {
  private DataSource datasource = null;
  private Context itx;
  private static JsonUtil jsonUtil;
  //private static final String lookupName = "java:comp/env/jdbc/wemsin";
  private static final String lookupName = "";
  private Logger log = Logger.getLogger(this.getClass().getName());
  /**
   * 싱글톤 패턴의 생성자
   * @return DBUtil
   */
  public static JsonUtil getInstance() {
    if (jsonUtil == null) {
    	jsonUtil = new JsonUtil();
    }
    return jsonUtil;
  }

  /**
   * Json data create
   * @return DataSource 데이터 소스
   * @throws NamingException
   */

  public String getJsonData(List<?> list, ModelMap model) {
		JSONArray jsonArray = new JSONArray();
		model.addAttribute("jsonList", jsonArray.fromObject(list));
	return jsonArray.fromObject(list).toString();
  }
  
  /**
   * Json data create
   * @return DataSource 데이터 소스
   * @throws NamingException
   */

  public String getJsonData(Map<?, ?> map, ModelMap model) {
		model.addAttribute("jsonList", JSONObject.fromObject(JSONSerializer.toJSON(map)));
	return JSONObject.fromObject(JSONSerializer.toJSON(map)).toString();
  }
}
