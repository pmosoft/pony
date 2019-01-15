package net.pmosoft.pony.dams.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import net.pmosoft.pony.dams.table.TabInfo;
import net.pmosoft.pony.dams.table.TabInfoDao;
import net.pmosoft.pony.dams.table.TabInfoSrv;


@Service
public class JdbcInfoSrv {

    @Autowired
    private JdbcInfoDao jdbcInfoDao;

    @Autowired
    private JdbcInfoValidatorSrv jdbcInfoValidatorSrv;

    @Autowired
    private TabInfoSrv tabInfoSrv;

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

            //Gson gson = new Gson();
            //System.out.println(gson.toJson(jdbcInfoOutVoList));

            result.put("isSuccess", true);
            result.put("jdbcInfoOutVoList", jdbcInfoOutVoList);
            //result.put("jdbcInfoOutVoJson", gson.toJson(jdbcInfoOutVoList));
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> selectComboJdbcList(JdbcInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<JdbcInfo> jdbcInfoOutVoList = jdbcInfoDao.selectComboJdbcList(inVo);

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
}
