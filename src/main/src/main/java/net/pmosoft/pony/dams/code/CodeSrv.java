package net.pmosoft.pony.dams.code;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


@Service
public class CodeSrv {

    @Autowired
    private CodeDao codeDao;

    @Autowired
    private CodeValidatorSrv codeValidatorSrv;

    public Map<String, Object> selectCodeList(Code code){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Code> codeOutVoList = null;
            codeOutVoList = codeDao.selectCodeList(code);;
            result.put("isSuccess", true);
            result.put("codeOutVoList", codeOutVoList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> selectCodeRegList(Map<String, String> params) {

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Map<String, Object>> list = null;
            list = codeDao.selectCodeRegList(params);
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

    public Map<String, Object> selectCodeExtList(Map<String, String> params) {

        Map<String, Object> result = new HashMap<String, Object>();

        List<Map<String, Object>> list = null;
        try {
            list = codeDao.selectCodeExtList(params);
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
    
    public Map<String, Object> selectCodeExtRegList(Map<String, String> params) {
        //System.out.println("params221 searchValue=" + params.get("searchValue"));

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Map<String, Object>> list = null;
            list = codeDao.selectCodeExtRegList(params);
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
    
    
    public Map<String, Object> selectCodeCombo(Map<String, String> params) {
        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Map<String, Object>> list = null;            
            list = codeDao.selectCodeCombo(params);
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
        
    
    
    public Map<String, Object> saveCode(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            String data = params.get("data");
            Gson gson = new Gson(); 
            Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
            List<Map<String,String>> listParams  = gson.fromJson(data, type);
    
            Map<String, String> errors = new HashMap<String, String>();
            errors = codeValidatorSrv.validateSaveCode(listParams);
            if(errors.size()>0){ 
                result.put("isSuccess", false);
                result.put("errUsrMsg", errors.get("errUsrMsg"));
            } else {
                for (int i = 0; i  < listParams.size(); i++) {
                    if  (codeDao.selectCodeCnt(listParams.get(i))==0) {
                        codeDao.insertCode(listParams.get(i));
                    } else {
                        codeDao.updateCode(listParams.get(i));
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

    public Map<String, Object> saveCodeExt(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
        
            String data = params.get("data");
            Gson gson = new Gson(); 
            Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
            List<Map<String,String>> listParams  = gson.fromJson(data, type);
    
            Map<String, String> errors = new HashMap<String, String>();
            errors = codeValidatorSrv.validateSaveCode(listParams);
            if(errors.size()>0){ 
                result.put("isSuccess", false);
                result.put("errUsrMsg", errors.get("errUsrMsg"));
            } else {
                for (int i = 0; i  < listParams.size(); i++) {
                    if  (codeDao.selectCodeExtCnt(listParams.get(i))==0) {
                        codeDao.insertCodeExt(listParams.get(i));
                    } else {
                        codeDao.updateCodeExt(listParams.get(i));
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
    
    
    public Map<String, Object> deleteCode(Map<String,String> params){

        Map<String, Object> result = new HashMap<String, Object>();
        
        try {
            String data = params.get("data");
            Gson gson = new Gson(); 
            Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
            List<Map<String,String>> listParams  = gson.fromJson(data, type);
            
            Map<String, String> errors = new HashMap<String, String>();
            errors = codeValidatorSrv.validateDeleteCode(params);
            if(errors.size()>0){
                result.put("isSuccess", false);
                result.put("errUsrMsg", errors.get("errUsrMsg"));
            } else {
                for (int i = 0; i < listParams.size(); i++) {
                    codeDao.deleteCode(listParams.get(i));
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
  
    public Map<String, Object> deleteCodeExt(Map<String,String> params){
        Map<String, Object> result = new HashMap<String, Object>();
        
        try {
            String data = params.get("data");
            Gson gson = new Gson(); 
            Type type = new TypeToken<List<Map<String,String>>>() {}.getType();
            List<Map<String,String>> listParams  = gson.fromJson(data, type);
            
            Map<String, String> errors = new HashMap<String, String>();
            errors = codeValidatorSrv.validateDeleteCode(params);
            if(errors.size()>0){
                result.put("isSuccess", false);
                result.put("errUsrMsg", errors.get("errUsrMsg"));
            } else {
                for (int i = 0; i < listParams.size(); i++) {
                    codeDao.deleteCodeExt(listParams.get(i));
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
