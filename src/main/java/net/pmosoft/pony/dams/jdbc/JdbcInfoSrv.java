package net.pmosoft.pony.dams.jdbc;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Service
public class JdbcInfoSrv {

    @Autowired
    private JdbcInfoDao jdbcInfoDao;

    @Autowired
    private JdbcInfoValidatorSrv jdbcInfoValidatorSrv;


    public Map<String, Object> saveJdbcInfo(JdbcInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try{

            Map<String, String> errors = new HashMap<String, String>();
            errors = jdbcInfoValidatorSrv.validateSaveJdbcInfo(inVo);
            if(errors.size()>0){
                result.put("isSuccess", false);
                result.put("errUsrMsg", errors.get("errUsrMsg"));
            } else {
                if  (jdbcInfoDao.selectJdbcInfoCnt(inVo)==0) {
                    jdbcInfoDao.insertJdbcInfo(inVo);
                } else {
                    jdbcInfoDao.updateJdbcInfo(inVo);
                }
                result.put("isSuccess", true);
                result.put("usrMsg", "저장 되었습니다");
            }
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생되었습니다.");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> selectJdbcInfoList(JdbcInfo jdbcInfo){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<JdbcInfo> jdbcInfoOutVoList = null;
            jdbcInfoOutVoList = jdbcInfoDao.selectJdbcInfoList(jdbcInfo);

            Gson gson = new Gson();
            //System.out.println(gson.toJson(jdbcInfoOutVoList));

            result.put("isSuccess", true);
            result.put("jdbcInfoOutVoList", jdbcInfoOutVoList);
            result.put("jdbcInfoOutVoJson", gson.toJson(jdbcInfoOutVoList));
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> selectComboJdbcList(JdbcInfo jdbcInfo){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<JdbcInfo> jdbcInfoOutVoList = null;
            jdbcInfoOutVoList = jdbcInfoDao.selectComboJdbcList(jdbcInfo);

            Gson gson = new Gson();
            //System.out.println(gson.toJson(jdbcInfoOutVoList));

            result.put("isSuccess", true);
            result.put("jdbcInfoOutVoList", jdbcInfoOutVoList);
            result.put("jdbcInfoOutVoJson", gson.toJson(jdbcInfoOutVoList));
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }





    public Map<String, Object> deleteJdbcInfo(JdbcInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            jdbcInfoDao.deleteJdbcInfo(inVo);
            result.put("isSuccess", true);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> testJdbcInfo(JdbcInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            jdbcInfoDao.deleteJdbcInfo(inVo);
            result.put("isSuccess", true);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


}
