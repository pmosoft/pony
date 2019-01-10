package net.pmosoft.pony.dams.jdbc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
    
/**
 *
 */
@RestController
@CrossOrigin(origins="http://localhost:4202")
public class JdbcInfoCtrl {

    @Autowired
    private JdbcInfoSrv jdbcInfoSrv;

    /**
     * 코드목록 조회
     */
    @RequestMapping(value = "/dams/jdbc/selectJdbcInfoList")
    public Map<String, Object> selectJdbcInfoList(@RequestBody JdbcInfo jdbcInfo) {
        return jdbcInfoSrv.selectJdbcInfoList(jdbcInfo);
    }

    /**
     * 코드등록목록 조회
     */
    @RequestMapping(value = "/dams/jdbc/selectJdbcInfoRegList")
    public Map<String, Object> selectJdbcInfoRegList(@RequestParam Map<String, String> params) {
        return jdbcInfoSrv.selectJdbcInfoRegList(params);
    }   

    /**
     * 코드콤보 조회
     */
    @RequestMapping(value = "/dams/jdbc/selectJdbcInfoCombo")
    public Map<String, Object> selectJdbcInfoCombo(@RequestParam Map<String, String> params) {
        return jdbcInfoSrv.selectJdbcInfoCombo(params);
    }   


    /**
     * 코드확장목록 조회
     */
    @RequestMapping(value = "/dams/jdbc/selectJdbcInfoExtList")
    public Map<String, Object> selectJdbcInfoExtList(@RequestParam Map<String, String> params) {
        return jdbcInfoSrv.selectJdbcInfoExtList(params);
    }      
    
    /**
     * 코드확장등록목록 조회
     */
    @RequestMapping(value = "/dams/jdbc/selectJdbcInfoExtRegList")
    public Map<String, Object> selectJdbcInfoExtRegList(@RequestParam Map<String, String> params) {
        return jdbcInfoSrv.selectJdbcInfoExtRegList(params);
    }   
    
   
    /**
     * 코드 저장(Multi:json)
     */
    @RequestMapping(value = "/dams/jdbc/saveJdbcInfo")
    public Map<String, Object> saveJdbcInfo(@RequestParam Map<String,String> params) {
        return jdbcInfoSrv.saveJdbcInfo(params);
    }

    /**
     * 코드확장 저장(Multi:json)
     */
    @RequestMapping(value = "/dams/jdbc/saveJdbcInfoExt")
    public Map<String, Object> saveJdbcInfoExt(@RequestParam Map<String,String> params) {
        return jdbcInfoSrv.saveJdbcInfoExt(params);
    }
    
    /**
     * 코드 삭제(Multi:json)
     */
    @RequestMapping(value = "/dams/jdbc/deleteJdbcInfo")
    public Map<String, Object> deleteJdbcInfo(@RequestParam Map<String,String> params) {
        return jdbcInfoSrv.deleteJdbcInfo(params);
    }

    /**
     * 코드확장 삭제(Multi:json)
     */
    @RequestMapping(value = "/dams/jdbc/deleteJdbcInfoExt")
    public Map<String, Object> deleteJdbcInfoExt(@RequestParam Map<String,String> params) {
        return jdbcInfoSrv.deleteJdbcInfoExt(params);
    }   
    
}
