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

    /**********************************************************************************
    *                                       비교
    **********************************************************************************/
    List<TabInfo> selectCmpTabInfoList(TabInfo inVo);

    /**********************************************************************************
    *                                       저장
    **********************************************************************************/
    void insertCmpTabInfo(TabInfo inVo);

    /**********************************************************************************
    *                                       조회
    **********************************************************************************/
    List<TabInfo> selectTabInfoList(TabInfo inVo);

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

