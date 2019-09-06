package net.pmosoft.pony.dams.table;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TabInfoDao {

    /**********************************************************************************
    *                              MetaTabInfo 조회후 임시테이블 저장
    **********************************************************************************/
    List<TabInfo> selectMetaTabInfoList(TabInfo inVo); // 범용
    void deleteMetaTabInfo(TabInfo inVo);
    void insertMetaTabInfo(TabInfo inVo);
    void insertMetaTabInfoBulk(List<TabInfo> inVo);

    /**********************************************************************************
    *                                       비교
    **********************************************************************************/
    List<TabInfo> selectCmpTabInfoList(TabInfo inVo);

    /**********************************************************************************
    *                                       저장
    **********************************************************************************/
    void insertCmpTabInfo(TabInfo inVo);

    /**********************************************************************************
    *                                       수정
    **********************************************************************************/
    void updateCommon(TabInfo inVo);
        
    /**********************************************************************************
    *                                       삭제
    **********************************************************************************/
    void deleteTabInfo(TabInfo inVo);

    /**********************************************************************************
    *                                       조회
    **********************************************************************************/
    List<TabInfo> selectTabInfoList(TabInfo inVo);
    List<TabInfo> selectTabList(TabInfo inVo);
    List<TabInfo> selectColList(TabInfo inVo);

    /**********************************************************************************
    *                                       유틸
    **********************************************************************************/
    //List<TabInfo> selectCreateScript(TabInfo inVo);

    List<Map<String, Object>> selectCommonQryList(TabInfo inVo);
    //List<Map<String, Object>> selectTabQryList(TabInfo inVo);
    //List<Map<String, Object>> selectInsStat(TabInfo inVo);
    int selectDataCnt(TabInfo inVo);

    //
//    int selectMetaTabCnt(Map<String,Object> params);
//
//    /**********************************************************************************
//     *
//     *                                     TabCol
//     *
//     **********************************************************************************/
//    List<Map<String, Object>> selectTabColList(Map<String,String> params);
//    int selectTabColCnt(Map<String,String> params);
//
//	/**********************************************************************************
//	 *
//	 *                                  Tab
//	 *
//	 **********************************************************************************/
//	List<Map<String, Object>> selectTabList(Map<String,String> params);
//	int selectTabCnt(Map<String,String> params);

}

