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

    public Map<String, Object> selectJdbcInfoList(JdbcInfo jdbcInfo){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<JdbcInfo> jdbcInfoOutVoList = null;
            jdbcInfoOutVoList = jdbcInfoDao.selectJdbcInfoList(jdbcInfo);;
            result.put("isSuccess", true);
            result.put("jdbcInfoOutVoList", jdbcInfoOutVoList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> selectJdbcInfoRegList(Map<String, String> params) {

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Map<String, Object>> list = null;
            list = jdbcInfoDao.selectJdbcInfoRegList(params);
            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e) {
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }   

    public Map<String, Object> selectJdbcInfoExtList(Map<String, String> params) {

        Map<String, Object> result = new HashMap<String, Object>();

        List<Map<String, Object>> list = null;
        try {
            list = jdbcInfoDao.selectJdbcInfoExtList(params);
            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e) {
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }       
    
    public Map<String, Object> selectJdbcInfoExtRegList(Map<String, String> params) {
        //System.out.println("params221 searchValue=" + params.get("searchValue"));

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Map<String, Object>> list = null;
            list = jdbcInfoDao.selectJdbcInfoExtRegList(params);
            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e) {
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }       
    
    
    public Map<String, Object> selectJdbcInfoCombo(Map<String, String> params) {
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Map<String, Object>> list = null;            
            list = jdbcInfoDao.selectJdbcInfoCombo(params);
            result.put("isSuccess", true);
            result.put("data", list);
        } catch (Exception e) {
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }   
        
    
    
    public Map<String, Object> saveJdbcInfo(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            String data = params.get("data");
            Gson gson = new Gson(); 
            Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
            List<Map<String,String>> listParams  = gson.fromJson(data, type);
    
            Map<String, String> errors = new HashMap<String, String>();
            errors = jdbcInfoValidatorSrv.validateSaveJdbcInfo(listParams);
            if(errors.size()>0){ 
                result.put("isSuccess", false);
                result.put("errUsrMsg", errors.get("errUsrMsg"));
            } else {
                for (int i = 0; i  < listParams.size(); i++) {
                    if  (jdbcInfoDao.selectJdbcInfoCnt(listParams.get(i))==0) {
                        jdbcInfoDao.insertJdbcInfo(listParams.get(i));
                    } else {
                        jdbcInfoDao.updateJdbcInfo(listParams.get(i));
                    }
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

    public Map<String, Object> saveJdbcInfoExt(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
        
            String data = params.get("data");
            Gson gson = new Gson(); 
            Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
            List<Map<String,String>> listParams  = gson.fromJson(data, type);
    
            Map<String, String> errors = new HashMap<String, String>();
            errors = jdbcInfoValidatorSrv.validateSaveJdbcInfo(listParams);
            if(errors.size()>0){ 
                result.put("isSuccess", false);
                result.put("errUsrMsg", errors.get("errUsrMsg"));
            } else {
                for (int i = 0; i  < listParams.size(); i++) {
                    if  (jdbcInfoDao.selectJdbcInfoExtCnt(listParams.get(i))==0) {
                        jdbcInfoDao.insertJdbcInfoExt(listParams.get(i));
                    } else {
                        jdbcInfoDao.updateJdbcInfoExt(listParams.get(i));
                    }
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
    
    
    public Map<String, Object> deleteJdbcInfo(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();
        
        try {
            String data = params.get("data");
            Gson gson = new Gson(); 
            Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
            List<Map<String,String>> listParams  = gson.fromJson(data, type);
            
            Map<String, String> errors = new HashMap<String, String>();
            errors = jdbcInfoValidatorSrv.validateDeleteJdbcInfo(params);
            if(errors.size()>0){
                result.put("isSuccess", false);
                result.put("errUsrMsg", errors.get("errUsrMsg"));
            } else {
                for (int i = 0; i < listParams.size(); i++) {
                    jdbcInfoDao.deleteJdbcInfo(listParams.get(i));
                }
                result.put("isSuccess", true);
                result.put("usrMsg", "삭제 되었습니다.");
            }
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
  
    public Map<String, Object> deleteJdbcInfoExt(Map<String,String> params){
        Map<String, Object> result = new HashMap<String, Object>();
        
        try {
            String data = params.get("data");
            Gson gson = new Gson(); 
            Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
            List<Map<String,String>> listParams  = gson.fromJson(data, type);
            
            Map<String, String> errors = new HashMap<String, String>();
            errors = jdbcInfoValidatorSrv.validateDeleteJdbcInfo(params);
            if(errors.size()>0){
                result.put("isSuccess", false);
                result.put("errUsrMsg", errors.get("errUsrMsg"));
            } else {
                for (int i = 0; i < listParams.size(); i++) {
                    jdbcInfoDao.deleteJdbcInfoExt(listParams.get(i));
                }
                result.put("isSuccess", true);
                result.put("usrMsg", "삭제 되었습니다.");
            }
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    
}
