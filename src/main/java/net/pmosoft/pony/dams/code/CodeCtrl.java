package net.pmosoft.pony.dams.code;

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
public class CodeCtrl {

    @Autowired
    private CodeSrv codeSrv;

    /**
     * 코드목록 조회
     */
    @RequestMapping(value = "/dams/code/selectCodeList")
    public Map<String, Object> selectCodeList(@RequestBody Code code) {
        return codeSrv.selectCodeList(code);
    }

    /**
     * 코드등록목록 조회
     */
    @RequestMapping(value = "/dams/code/selectCodeRegList")
    public Map<String, Object> selectCodeRegList(@RequestParam Map<String, String> params) {
        return codeSrv.selectCodeRegList(params);
    }

    /**
     * 코드콤보 조회
     */
    @RequestMapping(value = "/dams/code/selectCodeCombo")
    public Map<String, Object> selectCodeCombo(@RequestParam Map<String, String> params) {
        return codeSrv.selectCodeCombo(params);
    }


    /**
     * 코드확장목록 조회
     */
    @RequestMapping(value = "/dams/code/selectCodeExtList")
    public Map<String, Object> selectCodeExtList(@RequestParam Map<String, String> params) {
        return codeSrv.selectCodeExtList(params);
    }

    /**
     * 코드확장등록목록 조회
     */
    @RequestMapping(value = "/dams/code/selectCodeExtRegList")
    public Map<String, Object> selectCodeExtRegList(@RequestParam Map<String, String> params) {
        return codeSrv.selectCodeExtRegList(params);
    }



    /**
     * 코드 저장(Excel)
     */
    @RequestMapping(value = "/dams/code/insertCodeExcel")
    public Map<String, Object> insertCodeExcel() {
        return codeSrv.insertCodeExcel();
    }


    /**
     * 코드 저장(Multi:json)
     */
    @RequestMapping(value = "/dams/code/saveCode")
    public Map<String, Object> saveCode(@RequestParam Map<String,String> params) {
        return codeSrv.saveCode(params);
    }

    /**
     * 코드확장 저장(Multi:json)
     */
    @RequestMapping(value = "/dams/code/saveCodeExt")
    public Map<String, Object> saveCodeExt(@RequestParam Map<String,String> params) {
        return codeSrv.saveCodeExt(params);
    }

    /**
     * 코드 삭제(Multi:json)
     */
    @RequestMapping(value = "/dams/code/deleteCode")
    public Map<String, Object> deleteCode(@RequestParam Map<String,String> params) {
        return codeSrv.deleteCode(params);
    }

    /**
     * 코드확장 삭제(Multi:json)
     */
    @RequestMapping(value = "/dams/code/deleteCodeExt")
    public Map<String, Object> deleteCodeExt(@RequestParam Map<String,String> params) {
        return codeSrv.deleteCodeExt(params);
    }

}
