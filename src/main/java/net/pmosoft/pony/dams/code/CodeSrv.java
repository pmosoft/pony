package net.pmosoft.pony.dams.code;


import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.pmosoft.pony.comm.App;
import net.pmosoft.pony.comm.util.ExcelUtil;
import net.pmosoft.pony.comm.util.FileUtil;


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
                        //codeDao.insertCode(listParams.get(i));
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
                    //codeDao.deleteCode(listParams.get(i));
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


    public Map<String, Object> insertCodeExcel(){
        System.out.println("aaaaaaaaaaaaa");
        Map<String, Object> result = new HashMap<String, Object>();

        try {

            ExcelUtil eu = new ExcelUtil();
            List<Map<String,String>> list = eu.xlsToList("c:/pony/excel/code-list-upload.xls");
            System.out.println("list.size()=="+list.size());

            for (int i = 0; i < list.size(); i++) {
                Code code = new Code();
                code.setCdGrp(list.get(i).get("cdGrp"));
                code.setCdGrpNm(list.get(i).get("cdGrpNm"));
                code.setCdId(list.get(i).get("cdId"));
                code.setCdIdNm(list.get(i).get("cdIdNm"));
                code.setCd(list.get(i).get("cd"));
                code.setCdNm(list.get(i).get("cdNm"));
                code.setCdDesc(list.get(i).get("cdDesc"));
                code.setCdTyCd(list.get(i).get("cdTyCd"));
                code.setCdSeq(Math.round(Float.parseFloat(list.get(i).get("cdSeq"))));
                code.setRegUsrId("admin");
                code.setUpdUsrId("admin");

                codeDao.insertCode(code);
            }

            result.put("isSuccess", true);
            result.put("usrMsg", "삭제 되었습니다.");
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Object> selectInsStat(Code code){

        Map<String, Object> result = new HashMap<String, Object>();

        try {
            List<Code> codeOutVoList = null;
            codeOutVoList = codeDao.selectCodeList(code);;

            String qry = "";
            String s00 = "";
            s00 += " \n CREATE TABLE TDACM00060 (                                                                      ";
            s00 += " \n       CD_GRP         VARCHAR(20)  NOT NULL -- COMMENT '코드그룹'       -- META                     ";
            s00 += " \n     , CD_GRP_NM      VARCHAR(20)      NULL -- COMMENT '코드그룹명'      -- 메타                              ";
            s00 += " \n     , CD_ID          VARCHAR(20)  NOT NULL -- COMMENT '코드아이디'     -- COL_STS_CD                ";
            s00 += " \n     , CD_ID_NM       VARCHAR(20)      NULL -- COMMENT '코드아이디명'    -- 컬럼상태코드                      ";
            s00 += " \n     , CD             VARCHAR(20)  NOT NULL -- COMMENT '코드'          -- 01                      ";
            s00 += " \n     , CD_NM          VARCHAR(500)     NULL -- COMMENT '코드명'        -- 요청                                       ";
            s00 += " \n     , CD_DESC        VARCHAR(500)     NULL -- COMMENT '코드설명'                                   ";
            s00 += " \n     , CD_TY_CD_NM    VARCHAR(500)     NULL -- COMMENT '코드유형코드'    -- 1:필드 2:화면 3:프로그램     ";
            s00 += " \n     , CD_SEQ         INT              NULL -- COMMENT '코드순서'                              ";
            s00 += " \n     , REG_DTM        VARCHAR(14)      NULL -- COMMENT '등록일시'                              ";
            s00 += " \n     , REG_USR_ID     VARCHAR(20)      NULL -- COMMENT '등록자'                               ";
            s00 += " \n     , UPD_DTM        VARCHAR(14)      NULL -- COMMENT '변경일시'                             ";
            s00 += " \n     , UPD_USR_ID     VARCHAR(20)      NULL -- COMMENT '변경자'                               ";
            s00 += " \n     , PRIMARY KEY(CD_GRP,CD_ID,CD)                                                         ";
            s00 += " \n );\n";

            //qry = s00;

            String s01 = "INSERT INTO TDACM00060 VALUES (";
            String s02 = "", s03 = "";

            for (int i = 0; i < codeOutVoList.size(); i++) {
                s02 += "'" +codeOutVoList.get(i).getCdGrp()   + "'";
                s02 += ",'"+codeOutVoList.get(i).getCdGrpNm() + "'";
                s02 += ",'"+codeOutVoList.get(i).getCdId()    + "'";
                s02 += ",'"+codeOutVoList.get(i).getCdIdNm()  + "'";
                s02 += ",'"+codeOutVoList.get(i).getCd()      + "'";
                s02 += ",'"+codeOutVoList.get(i).getCdNm()    + "'";
                s02 += ",'"+codeOutVoList.get(i).getCdDesc()  + "'";
                s02 += ",'"+codeOutVoList.get(i).getCdTyCd()  + "'";
                s02 += ", "+codeOutVoList.get(i).getCdSeq()   + " ";
                s02 += ",'"+codeOutVoList.get(i).getRegDtm()  + "'";
                s02 += ",'"+codeOutVoList.get(i).getRegUsrId()+ "'";
                s02 += ",'"+codeOutVoList.get(i).getUpdDtm()  + "'";
                s02 += ",'"+codeOutVoList.get(i).getUpdUsrId()+ "');\n";

                s03 += s01 + s02;
                //System.out.println(qry);
                s02 = "";
            }

            qry = s00+s03;
            System.out.println(qry);

            // 메모패드로 출력
            FileUtil.stringToFile(qry, App.excelPath+"insertStatCode.sql");
            Runtime run = Runtime.getRuntime ();
            run.exec ("cmd /c start notepad++.exe "+App.excelPath+"insertStatCode.sql");


            result.put("isSuccess", true);
            //result.put("codeOutVoList", codeOutVoList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }


}
