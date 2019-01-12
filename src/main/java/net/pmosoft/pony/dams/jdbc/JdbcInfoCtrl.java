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
     * JDBC 정보 저장
     */
    @RequestMapping(value = "/dams/jdbc/saveJdbcInfo")
    public Map<String, Object> saveJdbcInfo(@RequestBody JdbcInfo inVo) {
        return jdbcInfoSrv.saveJdbcInfo(inVo);
    }

    /**
     * JDBC 정보 조회
     */
    @RequestMapping(value = "/dams/jdbc/selectJdbcInfoList")
    public Map<String, Object> selectJdbcInfoList(@RequestBody JdbcInfo inVo) {
        return jdbcInfoSrv.selectJdbcInfoList(inVo);
    }

    /**
     * JDBC 정보 삭제
     */
    @RequestMapping(value = "/dams/jdbc/deleteJdbcInfo")
    public Map<String, Object> deleteJdbcInfo(@RequestBody JdbcInfo inVo) {
        return jdbcInfoSrv.deleteJdbcInfo(inVo);
    }

    /**
     * JDBC 연결 테스트
     */
    @RequestMapping(value = "/dams/jdbc/testJdbcInfo")
    public Map<String, Object> testJdbcInfo(@RequestBody JdbcInfo inVo) {
        return jdbcInfoSrv.testJdbcInfo(inVo);
    }

}
