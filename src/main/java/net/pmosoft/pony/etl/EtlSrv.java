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

package net.pmosoft.pony.etl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.pmosoft.pony.dams.table.TabInfo;
import net.pmosoft.pony.dams.table.TabInfoDynSrv;
import net.pmosoft.pony.etl.extract.ExtractTab;
import net.pmosoft.pony.etl.load.LoadTab;

@Service
public class EtlSrv {

    private static Logger logger = LoggerFactory.getLogger(EtlSrv.class);

   
    @Autowired
    private TabInfoDynSrv tabInfoDynSrv;
    
    /*
     * 테이블 건수 갱신
     */
    public Map<String, Object> executeDbToDb(List<TabInfo> inVo){

        Map<String, Object> result = new HashMap<String, Object>();
        
        TabInfo srcTabInfo = new TabInfo();
        TabInfo tarTabInfo = new TabInfo();
        List<TabInfo> tarTabInfoList = new ArrayList<TabInfo>();
        
        try{
            for (int i = 0; i < inVo.size(); i++) {
                if(inVo.get(i).isChk()){
                    // 추출 테이블정보
                    srcTabInfo = inVo.get(i);
                    srcTabInfo.setJdbcInfo(tabInfoDynSrv.getJdbcInfo(inVo.get(i).getJdbcNm()));
                    
                    // 로드 테이블정보
                    tarTabInfo.setJdbcInfo(tabInfoDynSrv.getJdbcInfo(inVo.get(i).getTarJdbcNm()));
                    tarTabInfo.setJdbcNm(srcTabInfo.getTarJdbcNm());
                    tarTabInfo.setOwner(srcTabInfo.getOwner());
                    tarTabInfo.setTabNm(srcTabInfo.getTabNm());
                    tarTabInfo.setChk(true);tarTabInfoList.add(tarTabInfo);
                    if(srcTabInfo.isChkExtract()) new ExtractTab(srcTabInfo,tarTabInfo).executeTab();
                    if(srcTabInfo.isChkLoad()) { 
                        new LoadTab(tarTabInfo).executeInsertFileToDb();
                        tabInfoDynSrv.updateTabRowsUpdateScript(tarTabInfoList);                        
                    }
                } 
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

//    // 다수이관 - 소스 테이블명과 타켓 테이블명 동일
//    public void executeDbToDbBatch(ArrayList<String> tabNmList) {
//        for (int i = 0; i < tabNmList.size(); i++) {
//            srcTabInfo.setTabNm(tabNmList.get(i)); new ExtractTab(srcTabInfo).executeTab();
//            tarTabInfo.setTabNm(tabNmList.get(i)); new LoadTab(tarTabInfo).executeInsertFileToDb();        
//        }
//    }
    
    
 
}
