/*******************************************************************************
@title:테이블 컨트롤러
@description-start
@description-end
@developer:피승현
@progress-rate:80%
@update-history-start
-------------------------------------------------------------------------------
|   날짜   |수정자|내용
-------------------------------------------------------------------------------
|2017.11.01|피승현|최초개발
-------------------------------------------------------------------------------
@update-history-end
********************************************************************************/

package net.pmosoft.pony.dams.table;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.pmosoft.pony.comm.App;
import net.pmosoft.pony.comm.util.ExcelUtil;
import net.pmosoft.pony.comm.util.StringUtil;
import net.pmosoft.pony.dams.jdbc.JdbcInfo;
import net.pmosoft.pony.dams.jdbc.JdbcInfoDao;

@Service
@Slf4j
public class TabInfoSrv {

    @Autowired
    private TabInfoDao tabInfoDao;

    @Autowired
    private TabInfoDynSrv tabInfoDynSrv;

    /**********************************************************************************
    *
    *                               Pony테이블모듈_로컬DB
    *
    **********************************************************************************/
    public void __________Pony테이블모듈_로컬DB__________(){}

    /*
     * (추출) 테이블메타정보 조회후 추출테이블정보테이블에 저장후 정보 리턴
     * @param jdbcNm,owner,tabNm
     * */
    public Map<String, Object> selectMetaTabInfoList(TabInfo inVo){
        log.debug(">>>>> selectMetaTabInfoList");

        Map<String, Object> result = new HashMap<String, Object>();
        int rowCnt = 0;
        int commitCnt = 500;

        inVo.setTabNm("%");

        try{
            // 1단계 : DB 메타 테이블컬럼정보 조회
            List<TabInfo> tabInfoOutVoList = (List<TabInfo>) tabInfoDynSrv.selectMetaTabInfoList(inVo).get("tabInfoList");
            log.info("tabInfoOutVoList="+tabInfoOutVoList.size());

            // 2단계 : 메타테이블컬럼정보 삭제
            tabInfoDao.deleteMetaTabInfo(inVo);

            // 3단계 : 메타테이블컬럼정보 삽입
            List<TabInfo> tabInfoList = new ArrayList<TabInfo>();

            for (int i = 0; i  < tabInfoOutVoList.size(); i++) {
                //tabInfoDao.insertMetaTabInfo(tabInfoOutVoList.get(i));
                tabInfoList.add(tabInfoOutVoList.get(i));
                if(i%commitCnt == 0) {
                    tabInfoDao.insertMetaTabInfoBulk(tabInfoList);
                    tabInfoList.clear();
                     //rowCnt = 0;
                }
            }

            if(rowCnt < commitCnt) {
                log.debug("rowCnt2=========="+rowCnt);
                tabInfoDao.insertMetaTabInfoBulk(tabInfoList);
            }

            result.put("isSuccess", true);
            result.put("tabInfoOutVoList", tabInfoOutVoList);
            //result.put("data", list);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
    * (비교) 추출테이블정보테이블과 현재 테이블정보테이블과 비교한 정보를 리턴한다.
    */
    public Map<String, Object> selectCmpTabInfoList(TabInfo inVo) {

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            List<TabInfo> tabInfoOutVoList = tabInfoDao.selectCmpTabInfoList(inVo);;
            result.put("isSuccess", true);
            result.put("tabInfoOutVoList", tabInfoOutVoList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * (반영) 신규-변경 테이블정보를 반영한다
     */
    public Map<String, Object> saveCmpTabInfoList(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            tabInfoDao.insertCmpTabInfo(inVo);
            result.put("isSuccess", true);
            result.put("usrMsg", "입력 되었습니다");
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
        }

        return result;
    }

    /**
     * (삭제) 테이블정보를 삭제한다
     */
    public Map<String, Object> deleteTabInfo(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        try{
            tabInfoDao.deleteTabInfo(inVo);
            result.put("isSuccess", true);
            result.put("usrMsg", "입력 되었습니다");
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
        }

        return result;
    }

    /**
     * (조회) 추출되어 저장된 테이블정보를 조회한다.
     */
    public Map<String, Object> selectTabInfoList(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        System.out.println("inVo3====="+inVo.whereTabs);
        System.out.println(StringUtil.inParams(inVo.whereTabs));
        inVo.setWhereInTabs(StringUtil.inParams(inVo.whereTabs));
        try{
            List<TabInfo> tabInfoOutVoList = tabInfoDao.selectTabInfoList(inVo);
            result.put("isSuccess", true);
            result.put("tabInfoOutVoList", tabInfoOutVoList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * (조회) 테이블목록을 조회한다.
     */
    public Map<String, Object> selectTabList(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();
        inVo.setWhereInTabs(StringUtil.inParams(inVo.whereTabs));
        try{
            List<TabInfo> tabInfoOutVoList = tabInfoDao.selectTabList(inVo);
            //System.out.println(tabInfoOutVoList.size());
            result.put("isSuccess", true);
            result.put("tabInfoOutVoList", tabInfoOutVoList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * (조회) 컬럼목록을 조회한다.
     */
    public Map<String, Object> selectColList(TabInfo inVo){

        Map<String, Object> result = new HashMap<String, Object>();

        System.out.println("inVo.whereTabs=="+inVo.whereTabs);
        //if(inVo.chkWhereTabs) inVo.whereTabs = "";

        try{
            List<TabInfo> tabInfoOutVoList = tabInfoDao.selectColList(inVo);
            result.put("isSuccess", true);
            result.put("tabInfoOutVoList", tabInfoOutVoList);
        } catch (Exception e){
            result.put("isSuccess", false);
            result.put("errUsrMsg", "시스템 장애가 발생하였습니다");
            result.put("errSysMsg", e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /*
     * 테이블 건수 갱신
     */
    public Map<String, Object> updateTabRows(List<TabInfo> inVo){
        // mybatis에서 foreach사용시 sqlite의 jdbctype 처리를 못해주므로 단건으로 갱신한다

        //log.info("updateTabRows");
        //System.out.println("inVo.size()="+inVo.size());

        Map<String, Object> result = new HashMap<String, Object>();
        try{
            for (int i = 0; i < inVo.size(); i++) {
                //System.out.println("inVo.get(i)="+inVo.get(i).getTabNm());
                //System.out.println("inVo.get(i)="+inVo.get(i).getTabRows());
                //System.out.println("inVo.get(i)="+inVo.get(i).getJdbcNm());
                //System.out.println("inVo.get(i)="+inVo.get(i).getOwner());

                tabInfoDao.updateTabRows(inVo.get(i));
            }
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
